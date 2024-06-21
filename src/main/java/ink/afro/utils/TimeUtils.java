package ink.afro.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *  时间工具类
 */
public class TimeUtils {
    /**
     * 毫秒转日期
     *
     * @param milliseconds 毫秒值
     * @return LocalDateTime
     */
    public static LocalDateTime milliToLocalDateTime(Long milliseconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }
}
