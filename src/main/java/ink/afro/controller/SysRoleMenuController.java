package ink.afro.controller;

import ink.afro.entity.SysRoleMenu;
import ink.afro.entity.SysUser;
import ink.afro.service.SysRoleMenuService;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表控制层
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:32
 */
@RestController
@RequestMapping("sysRoleMenu")
public class SysRoleMenuController {
    /**
     * 服务对象
     */
    private final SysRoleMenuService sysRoleMenuService;

    @Autowired
    public SysRoleMenuController(SysRoleMenuService sysRoleMenuService) {
        this.sysRoleMenuService = sysRoleMenuService;
    }

    public boolean allPropertiesNotNull(List<SysRoleMenu> sysRoleMenuList) {
        return sysRoleMenuList.stream()
                .allMatch(sysRoleMenu ->
                        sysRoleMenu.getRoleId() != null && sysRoleMenu.getMenuId() != null
                );
    }

    /**
     * 批量新增数据
     *
     * @param sysRoleMenuList 实例对象集合
     * @return 添加成功行数
     */
    @PostMapping("/addBatch")
    public ResponseEntity<String> addBatch(List<SysRoleMenu> sysRoleMenuList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysRoleMenuList) && allPropertiesNotNull(sysRoleMenuList)) {
            records = sysRoleMenuService.insertBatch(sysRoleMenuList);
            return ResponseEntity.ok("添加成功行数：" + records);
        } else {
            return ResponseEntity.ok("添加失败");
        }
    }

    /**
     * 批量删除数据
     *
     * @param sysRoleMenuList 实例对象集合
     * @return 删除成功行数
     */
    @DeleteMapping("/deleteBatch")
    public ResponseEntity<String> deleteBatch(List<SysRoleMenu> sysRoleMenuList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysRoleMenuList) && allPropertiesNotNull(sysRoleMenuList)) {
            records = sysRoleMenuService.deleteBatch(sysRoleMenuList);
            return ResponseEntity.ok("删除成功行数：" + records);
        } else {
            return ResponseEntity.ok("删除失败");
        }
    }

    /**
     * 批量添加或删除数据
     *
     * @param sysRoleMenu 实例对象集合
     * @return 删除成功行数
     */
    @PostMapping("/insertOrDeleteRoleMenu")
    private ResponseEntity<String> insertOrDeleteRoleMenu(@RequestBody List<SysRoleMenu> sysRoleMenu) {
        // 判断是否为同一角色
        if (!CollectionUtils.isEmpty(sysRoleMenu)) {
            boolean isSameRoleId = sysRoleMenu.stream()
                    .map(SysRoleMenu::getRoleId)
                    .distinct()
                    .count() <= 1;
            if (isSameRoleId) {
                int d = 0;
                int i = 0;
                List<SysRoleMenu> ownedSysRoleMenu = this.sysRoleMenuService.queryByRoleId(sysRoleMenu.get(0).getRoleId());
                List<SysRoleMenu> deleteList = ownedSysRoleMenu.stream()
                        .filter(item -> !sysRoleMenu.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(deleteList) && allPropertiesNotNull(deleteList)) {
                    d = this.sysRoleMenuService.deleteBatch(deleteList);
                }
                List<SysRoleMenu> insertList = sysRoleMenu.stream()
                        .filter(item -> !ownedSysRoleMenu.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(insertList) && allPropertiesNotNull(insertList)) {
                    i = this.sysRoleMenuService.insertBatch(insertList);
                }
                return ResponseEntity.ok("删除" + d + "个菜单，添加" + i + "个菜单");
            }
            return ResponseEntity.ok("角色不匹配");
        } else {
            return ResponseEntity.ok("无操作");
        }
    }

    /**
     * 通过角色id查询数据
     *
     * @param roleId 角色ID
     * @return 集合数据
     */
    @GetMapping("/queryByRoleId")
    public ResponseEntity<List<SysRoleMenu>> queryByRoleId(Integer roleId) {
        return ResponseEntity.ok(this.sysRoleMenuService.queryByRoleId(roleId));
    }
}
