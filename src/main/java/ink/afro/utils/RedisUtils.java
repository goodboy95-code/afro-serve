package ink.afro.utils;

import ink.afro.exception.type.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * spring redis 工具类
 *
 * @author goodboy95-code
 **/
@Component
public class RedisUtils<T> {
    public final RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public void setCacheString(final String key, final String value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public void setCacheListRightPush(final String key, final String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * @param key 缓存的键值
     */
    public void deleteCacheListLeftPop(final String key, long count) {
        redisTemplate.opsForList().leftPop(key, count);
    }

    /**
     * @param key 缓存的键值
     * @param end 结束（包含）
     * @return List<Object>
     */
    public List<Object> getCacheListRange(final String key, long end) {
        return redisTemplate.opsForList().range(key, 0, end);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public T getCacheObject(final String key) {
        ValueOperations<Object, Object> operation = redisTemplate.opsForValue();
        T result;
        try {
            result = (T) operation.get(key);
        } catch (ClassCastException e) {
            throw new ServiceException("类型强制转换错误");
        }
        return result;
    }

    /**
     * 获得缓存的数据数量
     *
     * @param key 缓存键值
     * @return 数据count
     */
    public Long getCacheListSize(final String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 删除单个对象
     *
     * @param key 删除单个对象
     * @return 是否删除
     */
    public boolean deleteCacheObject(final String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }


    /**
     * 以秒为单位获取密钥的生存时间。
     *
     * @param key 缓存键值
     * @return 剩余秒数，负数表示不存在或者已过期
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获得缓存pattern路径下的key列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Set<String> keys(final String pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        if (keys != null) {
            return keys.stream().map(Object::toString).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }


}
