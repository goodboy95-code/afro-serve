package ink.afro.entity.vo;


import ink.afro.entity.SysMenu;
import ink.afro.utils.MenuUtils;
import lombok.Data;

/**
 * 路由额外信息
 *
 * @author goodboy95-code
 * @since 2023-10-09 18:09:41
 */
@Data
public class Meta {
    //设置该路由在侧边栏和面包屑中展示的名字
    private String title;
    //设置该路由的图标，对应路径src/assets/icons/svg
    private String icon;
    //设置为false，则不会被 <keep-alive>缓存
    private boolean cache;
    //内链地址（http(s)://开头）
    private String link;
    //是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
    private boolean hidden;
    //路由参数：如 {"id": 1, "name": "lemon"}
    private String query;

    public Meta(SysMenu menu) {
        this.title = menu.getMenuName();
        this.icon = menu.getIcon();
        this.cache = "0".equals(menu.getIsCache());
        this.hidden = "1".equals(menu.getVisible());
        this.query = menu.getQuery();
        if (MenuUtils.isInnerLink(menu)) {
            this.link = menu.getPath();
        }
    }
}
