package ink.afro.entity.vo;

import lombok.Data;

/**
 * 快捷导航信息
 *
 * @author goodboy95-code
 * @since 2023-10-09 18:09:41
 */
@Data
public class Navigation {
    // 路由名字
    private Integer menuId;
    // 路由名字
    private String name;
    //路由地址
    private String path;
    //其他元素
    private Meta meta;
    //组件地址
    private String component;
}
