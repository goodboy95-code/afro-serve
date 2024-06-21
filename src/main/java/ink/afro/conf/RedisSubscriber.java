package ink.afro.conf;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

/**
 * Redis消息订阅者
 */
@Component
public class RedisSubscriber {

    private final RedisMessageListenerContainer redisMessageListenerContainer;

    public RedisSubscriber(RedisMessageListenerContainer redisMessageListenerContainer) {
        this.redisMessageListenerContainer = redisMessageListenerContainer;
    }

    /**
     * 订阅指定频道
     */
    @PostConstruct
    public void subscribe() {
        Topic topic = new ChannelTopic("channel1"); // 指定频道
        redisMessageListenerContainer.addMessageListener((message, bytes) -> {
            // 处理接收到的消息
        }, topic);
    }
}
