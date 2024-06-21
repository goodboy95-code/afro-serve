package ink.afro.conf;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis消息发布者
 */
@Component
public class RedisPublisher {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisPublisher(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 发布消息
     * @param channel 要发布到的频道
     * @param message 要发布的内容
     */
    public void publish(String channel,String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
