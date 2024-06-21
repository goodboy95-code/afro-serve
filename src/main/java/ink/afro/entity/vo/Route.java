package ink.afro.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 路由配置信息（属性名不可修改）
 *
 * @author goodboy95-code
 * @since 2023-09-15 14:31:58
 */
@Data
public class Route {
    //路由所对应的菜单ID
    private Integer menuId;
    //路由名字
    private String name;
    //路由地址
    private String path;
    //组件地址
    private String component;
    //子路由
    private List<Route> children;
    //其他元素
    private Meta meta;
}
