package ink.afro.utils;

import ink.afro.constant.UserConstants;
import ink.afro.entity.SysMenu;
import ink.afro.entity.vo.Meta;
import ink.afro.entity.vo.Route;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 菜单工具
 *
 * @author goodboy95-code
 */
public class MenuUtils {
    /**
     * 获取组件名
     *
     * @param menu 菜单信息
     * @return 组件名
     */
    public static String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.hasText(menu.getComponent()) && UserConstants.TYPE_MENU.equals(menu.getMenuType())) {
            component = menu.getComponent();
        } else if (UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
            component = "";
        }
        return component;
    }

    /**
     * 构建前端路由所需要的路由树
     *
     * @param menus 菜单集合
     * @return 路由集合
     */
    public static List<Route> buildRouteInfo(List<SysMenu> menus) {
        List<Route> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            Route route = new Route();
            route.setMenuId(menu.getMenuId());
            route.setName(menu.getMenuName());
            route.setPath(menu.getPath());
            route.setComponent(getComponent(menu));
            route.setMeta(new Meta(menu));
            List<SysMenu> cMenus = menu.getChildren();
            if (!CollectionUtils.isEmpty(cMenus) && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                route.setChildren(buildRouteInfo(cMenus));
            }
            routers.add(route);
        }
        return routers;
    }

    public static SysMenu getChildMenusBDM(List<SysMenu> allList, int parentId) {
        SysMenu menuTree = new SysMenu();
        for (SysMenu cMenu : allList) {
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (ObjectUtils.nullUnSafeEquals(cMenu.getParentId(), parentId)) {
                recursionCollectionBDM(allList, cMenu);
                menuTree = cMenu;
            }
        }
        return menuTree;
    }

    /**
     * 递归集合
     *
     * @param allList 查询到的menu集合
     * @param cMenu   子节点
     */
    private static void recursionCollectionBDM(List<SysMenu> allList, SysMenu cMenu) {
        // 得到子节点集合
        List<SysMenu> childTree;
        childTree = allList.stream()
                .filter(menu -> ObjectUtils.nullUnSafeEquals(menu.getParentId(), cMenu.getMenuId()))
                .toList();
        if (!CollectionUtils.isEmpty(childTree)) {
            cMenu.setChildren(childTree);
        }
        for (SysMenu child : childTree) {
            recursionCollectionBDM(allList, child);
        }
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param allList  查询到的menu集合
     * @param parentId 传入的父节点ID
     * @return List<SysMenu>
     */
    public static List<SysMenu> getChildMenus(List<SysMenu> allList, int parentId) {
        List<SysMenu> menuTree = new ArrayList<>();
        for (SysMenu cMenu : allList) {
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (ObjectUtils.nullUnSafeEquals(cMenu.getParentId(), parentId) && isNotButton(cMenu)) {
                recursionCollection(allList, cMenu);
                menuTree.add(cMenu);
            }
        }
        return menuTree;
    }

    /**
     * 递归集合
     *
     * @param allList 查询到的menu集合
     * @param cMenu   子节点
     */
    private static void recursionCollection(List<SysMenu> allList, SysMenu cMenu) {
        // 得到子节点集合
        List<SysMenu> childTree;
        childTree = allList.stream()
                .filter(menu -> ObjectUtils.nullUnSafeEquals(menu.getParentId(), cMenu.getMenuId()) && isNotButton(menu))
                .toList();
        if (!CollectionUtils.isEmpty(childTree)) {
            cMenu.setChildren(childTree);
        }
        for (SysMenu child : childTree) {
            recursionCollection(allList, child);
        }
    }

    /**
     * 是否为一级菜单类型（目录），并且是非外链
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private static boolean isDirFrame(SysMenu menu) {
        return ObjectUtils.nullSafeEquals(menu.getParentId(), 0) && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame());
    }

    /**
     * 是否为一级菜单类型（M），并且是非外链
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public static boolean isMenuFrame(SysMenu menu) {
        return ObjectUtils.nullSafeEquals(menu.getParentId(), 0) && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame());
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public static boolean isInnerLink(SysMenu menu) {
        return UserConstants.NO_FRAME.equals(menu.getIsFrame()) && StringUtils.startsWithIgnoreCase(menu.getPath(), "http");
    }

    /**
     * 是否为权限按钮类型
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public static boolean isNotButton(SysMenu menu) {
        return !UserConstants.TYPE_BUTTON.equals(menu.getMenuType().trim());
    }
}
