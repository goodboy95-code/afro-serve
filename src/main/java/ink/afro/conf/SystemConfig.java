package ink.afro.conf;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author goodboy95-code
 */
@Component
public class SystemConfig {
    /**
     * 头像路径
     */
    @Getter
    private static String avatarPath;

    @Value("${system.upload.avatar.path}")
    public void setAvatarPath(String path) {
        avatarPath = path;
    }
}
