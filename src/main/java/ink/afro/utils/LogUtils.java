package ink.afro.utils;

/**
 * 处理并记录日志文件
 * 
 * @author goodboy95-code
 */
public class LogUtils
{

    public static String getBlock(Object msg)
    {
        return "[" + ObjectUtils.nullSafeToString(msg) + "]";
    }
}
