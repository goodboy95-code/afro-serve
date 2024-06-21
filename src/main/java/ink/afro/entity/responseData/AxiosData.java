package ink.afro.entity.responseData;

import java.io.Serial;
import java.util.HashMap;

/**
 * 响应体
 *
 * @author goodboy95-code
 */
@SuppressWarnings("unused")
public class AxiosData extends HashMap<String, Object> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 AxiosResult 对象，使其表示一个空消息。
     */
    public AxiosData() {
    }

    /**
     * 初始化一个新创建的 AxiosResult 对象
     *
     * @param key 键
     * @param value  值
     */
    public AxiosData(String key, Object value) {
        this.put(key, value);
    }

    /**
     * 设置多条数据
     *
     * @return AxiosData
     */
    public static AxiosData setData() {
        return new AxiosData();
    }

    /**
     * 设置一条数据
     *
     * @return AxiosData
     */
    public static AxiosData setData(String key,Object value) {
        return new AxiosData(key, value);
    }
}
