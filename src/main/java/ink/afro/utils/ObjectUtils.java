package ink.afro.utils;

import org.springframework.lang.Nullable;

public class ObjectUtils extends org.springframework.util.ObjectUtils {

    /**
     * 如果两个数组引用都为null，则认为它们不相等。（与nullSafeEquals区别）
     *
     * @param o1 第一个比较对象
     * @param o2 第二个比较对象
     * @return 给定对象是否相等
     */
    public static boolean nullUnSafeEquals(@Nullable Object o1, @Nullable Object o2) {
        if (o1 == null && o2 == null) {
            return false;
        }
        return org.springframework.util.ObjectUtils.nullSafeEquals(o1, o2);
    }
}














