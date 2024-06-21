package ink.afro.controller;

import ink.afro.conf.RedisPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发布消息
 */
@RestController
public class MessageController {
    private final RedisPublisher redisPublisher;

    public MessageController(RedisPublisher redisPublisher) {
        this.redisPublisher = redisPublisher;
    }

    @GetMapping("/publish")
    public String publishMessage(@RequestParam String message, @RequestParam String channel) {
        redisPublisher.publish(channel,message);
        return "Message published";
    }
}
