package ink.afro.security.service;

import ink.afro.constant.Constants;
import ink.afro.entity.other.LoginUser;
import ink.afro.utils.LocationUtils;
import ink.afro.utils.RedisUtils;
import ink.afro.utils.ServletUtils;
import cn.hutool.core.lang.UUID;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token生成校验
 *
 * @author goodboy95-code
 */
@Component("tokenService")
public class TokenService {
    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认60分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    // 是否开启同一用户多端同时在线
    @Value("${system.user.online.meantime}")
    private boolean meantime;

    // 同一用户多端同时在线人数
    @Value("${system.user.online.total}")
    private Integer total;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    private final RedisUtils<LoginUser> redisUtils;

    @Autowired
    public TokenService(RedisUtils<LoginUser> redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        String tokenId = UUID.randomUUID().toString(true);
        loginUser.setTokenId(tokenId);
        setUserAgent(loginUser);
        createOrRefreshRedis(loginUser,true);

        Map<String, Object> payload = new HashMap<>();
        payload.put(Constants.LOGIN_USER_KEY, tokenId);

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", "HS512");
        header.put("typ", "JWT");
        return JWTUtil.createToken(header, payload, secret.getBytes());
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser) {
        HttpServletRequest request = ServletUtils.getRequest();
        String userAgentString = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(userAgentString);
        loginUser.setBrowserType(ua.getBrowser().getName());
        loginUser.setOperatingSystem(ua.getOs().getName());
        loginUser.setIpAddress(request.getRemoteAddr());
        loginUser.setLoginLocation(LocationUtils.getLocation(request.getRemoteAddr()).getCity());
    }

    /**
     * 刷新redis中用户信息有效期
     *
     * @param loginUser 登录信息
     */
    public void createOrRefreshRedis(LoginUser loginUser,Boolean isCreateToken) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = Constants.LOGIN_TOKEN_KEY + loginUser.getTokenId();
        redisUtils.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
        // 同一用户多端同时在线
        if (meantime&&isCreateToken) {
            String userIdKey = Constants.LOGIN_USERID_KEY + loginUser.getUser().getUserId();
            Long keys = redisUtils.getCacheListSize(userIdKey);
            if (keys >= total) {
                for (Object tokenIntactId : redisUtils.getCacheListRange(userIdKey, keys - total)) {
                    redisUtils.deleteCacheObject(tokenIntactId.toString());
                }
                redisUtils.deleteCacheListLeftPop(userIdKey, keys - total + 1);
            }
            redisUtils.setCacheListRightPush(userIdKey, userKey);
        }
    }

    public void verifyRedisExpireTime(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            createOrRefreshRedis(loginUser,false);
        }
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        JWTPayload jwtPayload;
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token)) {
            if (token.startsWith(Constants.TOKEN_PREFIX)) {
                token = token.replace(Constants.TOKEN_PREFIX, "");
            }
            JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.hs512(secret.getBytes()));
            jwtPayload = JWTUtil.parseToken(token).getPayload();
            String uuid = null;
            if (jwtPayload != null) {
                uuid = (String) jwtPayload.getClaim(Constants.LOGIN_USER_KEY);
            }
            return redisUtils.getCacheObject(Constants.LOGIN_TOKEN_KEY + uuid);
        }
        return null;
    }

    /**
     * 删除用户身份信息
     *
     * @param tokenKey 用户身份标识Id
     */
    public boolean delLoginUser(String tokenKey) {
        if (StringUtils.hasText(tokenKey)) {
            return redisUtils.deleteCacheObject(Constants.LOGIN_TOKEN_KEY + tokenKey);
        } else {
            return false;
        }
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (!ObjectUtils.isEmpty(loginUser) && StringUtils.hasText(loginUser.getTokenId())) {
            createOrRefreshRedis(loginUser,false);
        }
    }
}
