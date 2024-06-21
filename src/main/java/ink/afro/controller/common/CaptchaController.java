package ink.afro.controller.common;

import ink.afro.constant.Constants;
import ink.afro.entity.responseData.AxiosData;
import ink.afro.utils.RedisUtils;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 验证码操作处理
 *
 * @author goodboy95-code
 * @since 2023-09-14 10:15:19
 */
@RestController
public class CaptchaController {

    private final RedisUtils<String> redisUtils;

    @Autowired
    public CaptchaController(RedisUtils<String> redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 生成验证码
     *
     * @return AxiosResult 结果集
     */
    @GetMapping("/getCaptchaImage")
    public ResponseEntity<AxiosData> getCode() {
        // 保存验证码信息
        String uuid = UUID.randomUUID().toString(true);
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        String result = captcha.getCode();
        BufferedImage image = (BufferedImage) captcha.createImage(result);

        redisUtils.setCacheObject(verifyKey, result, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            assert image != null : "image不能为null";
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        AxiosData axiosData = AxiosData.setData();
        axiosData.put("uuid", uuid);
        axiosData.put("captchaImage", Base64.encode(os.toByteArray()));
        return ResponseEntity.ok(axiosData);
    }
}
