package ink.afro.controller;

import ink.afro.annotation.Log;
import ink.afro.annotation.RateLimiter;
import ink.afro.entity.SysMenu;
import ink.afro.entity.SysUser;
import ink.afro.entity.vo.Meta;
import ink.afro.entity.vo.Navigation;
import ink.afro.entity.vo.Route;
import ink.afro.enums.LimitType;
import ink.afro.enums.OperateType;
import ink.afro.service.SysMenuService;
import ink.afro.utils.MenuUtils;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    /**
     * 服务对象
     */
    private final SysMenuService sysMenuService;

    @Autowired
    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody SysMenu sysMenu) {
        return ResponseEntity.ok(this.sysMenuService.insert(sysMenu));
    }

    /**
     * 删除数据
     *
     * @param menuId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Integer menuId) {
        return ResponseEntity.ok(this.sysMenuService.deleteById(menuId));
    }

    /**
     * 编辑数据
     *
     * @param sysMenu 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<SysMenu> edit(@RequestBody SysMenu sysMenu) {
        return ResponseEntity.ok(this.sysMenuService.update(sysMenu));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param menuId 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<SysMenu> queryById(Integer menuId) {
        return ResponseEntity.ok(this.sysMenuService.queryById(menuId));
    }

    /**
     * 分页查询
     *
     * @param menuName   菜单名称
     * @param status     菜单状态
     * @param pageNumber 当前页码
     * @param pageSize   每一页条数
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<SysMenu>> queryByPage(
            @RequestParam(required = false)
            String menuName,
            String status,
            int pageNumber,
            int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuName(menuName);
        sysMenu.setStatus(status);
        return ResponseEntity.ok(this.sysMenuService.queryByPage(sysMenu, pageRequest));
    }

    /**
     * 获取菜单
     *
     * @return AxiosResult 菜单结果集
     */
    @Log(title = "queryMenuTree", operateType = OperateType.SELECT)
    @GetMapping("queryMenuTree")
    public ResponseEntity<List<SysMenu>> queryMenuTree() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysMenu> menus;
        if (!ObjectUtils.isEmpty(user)) {
            menus = sysMenuService.queryMenuTree();
            if (!ObjectUtils.isEmpty(menus)) {
                return ResponseEntity.ok(menus);
            } else {
                System.out.println("获取菜单错误");
            }
        } else {
            System.out.println("用户不存在");
        }
        return null;
    }

    /**
     * 获取菜单
     *
     * @return AxiosResult 菜单结果集
     */
    @Log(title = "queryMenuTreeBDM", operateType = OperateType.SELECT)
    @GetMapping("queryMenuTreeBDM")
    public ResponseEntity<SysMenu> queryMenuTreeBDM() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        SysMenu menus;
        if (!ObjectUtils.isEmpty(user)) {
            menus = sysMenuService.queryMenuTreeBDM();
            if (!ObjectUtils.isEmpty(menus)) {
                return ResponseEntity.ok(menus);
            } else {
                System.out.println("获取菜单错误");
            }
        } else {
            System.out.println("用户不存在");
        }
        return null;
    }

    /**
     * 通过角色Id获取路由
     *
     * @param roleId 角色ID
     * @return AxiosResult 结果集
     */
    @Log(title = "queryMenuTreeBDMByRoleId", operateType = OperateType.SELECT)
    @GetMapping("queryMenuTreeBDMByRoleId")
    @RateLimiter(limitType = LimitType.IP)
    public ResponseEntity<SysMenu> queryMenuTreeBDMByRoleId(Integer roleId) {
        SysMenu menus;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user)) {
            menus = sysMenuService.queryMenuTreeBDMByRoleId(roleId);
        } else {
            System.out.println("menu不存在");
            menus = new SysMenu();
        }
        return ResponseEntity.ok(menus);
    }

    /**
     * 通过角色Id获取路由
     *
     * @param roleId 角色ID
     * @return AxiosResult 结果集
     */
    @Log(title = "queryRoutesByRoleId", operateType = OperateType.SELECT)
    @GetMapping("queryRoutesByRoleId")
    @RateLimiter(limitType = LimitType.IP)
    public ResponseEntity<List<Route>> queryRoutesByRoleId(Integer roleId) {
        List<SysMenu> menus;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user)) {
            menus = sysMenuService.queryMenuTreeByRoleId((user.getUserId()), roleId);
        } else {
            System.out.println("menu不存在");
            menus = new ArrayList<>();
        }
        return ResponseEntity.ok(MenuUtils.buildRouteInfo(menus));
    }

    /**
     * 获取导航信息
     *
     * @param roleId 角色ID
     * @return AxiosResult 结果集
     */
    @GetMapping("queryNavigations")
    public ResponseEntity<List<Navigation>> queryNavigations(Integer roleId) {
        List<SysMenu> menus;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user)) {
            menus = sysMenuService.querySysMenusByUserIdAndRoleId(user.getUserId(), roleId);
        } else {
            System.out.println("menu不存在");
            menus = null;
        }
        List<Navigation> navigations = new LinkedList<>();
        if (menus != null) {
            for (SysMenu menu : menus) {
                Navigation navigation = new Navigation();
                navigation.setMenuId(menu.getMenuId());
                navigation.setName(menu.getMenuName());
                navigation.setPath(menu.getPath());
                navigation.setComponent(MenuUtils.getComponent(menu));
                navigation.setMeta(new Meta(menu));
                navigations.add(navigation);
            }
        }
        return ResponseEntity.ok(navigations);
    }
}

