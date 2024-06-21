package ink.afro.utils;

import ink.afro.conf.SystemConfig;
import ink.afro.constant.Constants;
import ink.afro.entity.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * 文件上传工具类
 *
 * @author goodboy95-code
 */
public class FileUploadUtils {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = SystemConfig.getAvatarPath();

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    /**
     * 头像上传
     *
     * @param uploadDir 上传基目录
     * @param file      上传的头像文件
     * @return 文件实际存储的相对路径
     */
    public static String upload(String uploadDir, MultipartFile file) throws IOException {
        deleteAvatars(uploadDir, SecurityUtils.getLoginUser().getUser().getUserId());
        SysUser user = SecurityUtils.getLoginUser().getUser();
        String fileName = "sysUser-" + user.getUserId() + "-" + LocalDateTime.now() + ".png";
        String filePath = uploadDir + File.separator + fileName;
        file.transferTo(Paths.get(filePath));
        return getPathFileName(fileName);
    }

    public static String getPathFileName(String fileName) {
        return Constants.RESOURCE_PREFIX + "/" + fileName;
    }

    /**
     * 删除更新后不需要的头像
     *
     * @param uploadDir 上传基目录
     * @param userId 用户ID
     */
    public static void deleteAvatars(String uploadDir, Long userId) {
        Path directory = Paths.get(uploadDir);
        try (Stream<Path> paths = Files.walk(directory)) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().startsWith("sysUser-" + userId))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.err.println("Failed to delete file: " + path);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取头像
     *
     * @param uploadDir 上传基目录
     * @param userId 用户ID
     */
    public static Path[] getAvatars(String uploadDir, Long userId) {
        try (Stream<Path> paths = Files.walk(Paths.get(uploadDir))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().startsWith("sysUser-" + userId))
                    .toArray(Path[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
