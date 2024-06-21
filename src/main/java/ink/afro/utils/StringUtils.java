package ink.afro.utils;

public class StringUtils {

    /**
     * 在字符串的指定位置插入另一个字符串
     *
     * @param original  原始字符串
     * @param position  插入位置
     * @param insertStr 要插入的字符串
     * @return 修改后的字符串
     */
    public static String insertString(String original, int position, String insertStr) {
        StringBuilder str = new StringBuilder(original);
        str.insert(position, insertStr);
        return str.toString();
    }

    /**
     * 截取字符串
     *
     * @param str   需要截取的字符串
     * @param start 开始索引
     * @return 截取后的字符串
     */
    public static String substring(String str, int start) {
        return substring(str, start, str.length());
    }

    /**
     * 截取字符串
     *
     * @param str   需要截取的字符串
     * @param start 开始索引
     * @param end   结束索引
     * @return 截取后的字符串
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return "";
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }
}
