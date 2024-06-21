package ink.afro.controller;

import ink.afro.entity.SysUser;
import ink.afro.entity.SysUserRoleMenu;
import ink.afro.service.SysUserRoleMenuService;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户、角色和快捷导航（菜单）关联表(SysUserRoleMenu)表控制层
 *
 * @author goodboy95-code
 * @since 2024-01-03 04:32:10
 */
@RestController
@RequestMapping("sysUserRoleMenu")
public class SysUserRoleMenuController {
    /**
     * 服务对象
     */
    private final SysUserRoleMenuService sysUserRoleMenuService;

    @Autowired
    public SysUserRoleMenuController(SysUserRoleMenuService sysUserRoleMenuService) {
        this.sysUserRoleMenuService = sysUserRoleMenuService;
    }

    public boolean allPropertiesNotNull(List<SysUserRoleMenu> sysUserRoleMenuList) {
        return sysUserRoleMenuList.stream()
                .allMatch(sysUserRoleMenu ->
                        sysUserRoleMenu.getUserId() != null &&
                                sysUserRoleMenu.getRoleId() != null &&
                                sysUserRoleMenu.getMenuId() != null
                );
    }

    /**
     * 批量新增数据
     *
     * @param sysUserRoleMenuList 需要添加的数据
     * @return 添加成功行数
     */
    @PostMapping("/addBatch")
    public ResponseEntity<String> addBatch(@RequestBody List<SysUserRoleMenu> sysUserRoleMenuList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysUserRoleMenuList) && allPropertiesNotNull(sysUserRoleMenuList)) {
            records = sysUserRoleMenuService.insertBatch(sysUserRoleMenuList);
            return ResponseEntity.ok("添加成功行数：" + records);
        } else {
            return ResponseEntity.ok("添加失败");
        }
    }

    /**
     * 批量删除数据
     *
     * @param sysUserRoleMenuList 需要删除的数据
     * @return 删除成功行数
     */
    @DeleteMapping("/deleteBatch")
    public ResponseEntity<String> deleteBatch(@RequestBody List<SysUserRoleMenu> sysUserRoleMenuList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysUserRoleMenuList) && allPropertiesNotNull(sysUserRoleMenuList)) {
            records = sysUserRoleMenuService.deleteBatch(sysUserRoleMenuList);
            return ResponseEntity.ok("删除成功行数：" + records);
        } else {
            return ResponseEntity.ok("删除失败");
        }
    }

    /**
     * 批量添加或删除数据
     *
     * @param sysUserRoleMenuList 实例对象集合
     * @return 删除成功行数
     */
    @PostMapping("/insertOrDeleteUserRoleMenu")
    private ResponseEntity<String> insertOrDeleteUserRoleMenu(@RequestBody List<SysUserRoleMenu> sysUserRoleMenuList) {
        if (!CollectionUtils.isEmpty(sysUserRoleMenuList)) {
            boolean isSameUserId = sysUserRoleMenuList.stream()
                    .map(SysUserRoleMenu::getUserId)
                    .distinct()
                    .count() <= 1;
            boolean isSameRoleId = sysUserRoleMenuList.stream()
                    .map(SysUserRoleMenu::getRoleId)
                    .distinct()
                    .count() <= 1;
            if (isSameRoleId && isSameUserId) {
                int d = 0;
                int i = 0;
                List<SysUserRoleMenu> ownedSysUserRoleMenu = this.sysUserRoleMenuService.queryByUserIdAndRoleId(SecurityUtils.getLoginUser().getUser().getUserId(), sysUserRoleMenuList.get(0).getRoleId());
                List<SysUserRoleMenu> deleteList = ownedSysUserRoleMenu.stream()
                        .filter(item -> !sysUserRoleMenuList.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(deleteList) && allPropertiesNotNull(deleteList)) {
                    d = this.sysUserRoleMenuService.deleteBatch(deleteList);
                }
                List<SysUserRoleMenu> insertList = sysUserRoleMenuList.stream()
                        .filter(item -> !ownedSysUserRoleMenu.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(insertList) && allPropertiesNotNull(insertList)) {
                    i = this.sysUserRoleMenuService.insertBatch(insertList);
                }
                return ResponseEntity.ok("删除" + d + "个部门，添加" + i + "个部门");
            }
            return ResponseEntity.ok("角色不匹配");
        } else if (sysUserRoleMenuList != null) {
            sysUserRoleMenuService.truncateData();
            return ResponseEntity.ok("数据已经清空");
        } else {
            return ResponseEntity.ok("无操作");
        }
    }

    /**
     * 查询数据集合
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 数据集合
     */
    @GetMapping("/queryByUserIdAndRoleId")
    public ResponseEntity<List<SysUserRoleMenu>> queryByUserIdAndRoleId(Long userId, Integer roleId) {
        return ResponseEntity.ok(sysUserRoleMenuService.queryByUserIdAndRoleId(userId, roleId));
    }
}

