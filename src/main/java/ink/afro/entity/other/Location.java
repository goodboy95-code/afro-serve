package ink.afro.entity.other;

import lombok.Data;

/**
 * 登录地
 * @author goodboy95-code
 */
@Data
public class Location {
    /**
     * IP地址
     */
    private String ip;
    /**
     * 省份
     */
    private String pro;
    /**
     * 省份代码
     */
    private String proCode;
    /**
     * 城市
     */
    private String city;
    /**
     * 城市代码
     */
    private String cityCode;
    /**
     * 区域
     */
    private String region;
    /**
     * 区域代码
     */
    private String regionCode;
    /**
     * 地址
     */
    private String addr;
    /**
     * 区域名称
     */
    private String regionNames;
    /**
     * 错误
     */
    private String err;
}
