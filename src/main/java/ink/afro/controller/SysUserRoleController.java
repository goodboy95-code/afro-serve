package ink.afro.controller;

import ink.afro.entity.SysUser;
import ink.afro.entity.SysUserRole;
import ink.afro.service.SysUserRoleService;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表控制层
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:32
 */
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController {
    /**
     * 服务对象
     */
    private final SysUserRoleService sysUserRoleService;

    @Autowired
    public SysUserRoleController(SysUserRoleService sysUserRoleService) {
        this.sysUserRoleService = sysUserRoleService;
    }

    public boolean allPropertiesNotNull(List<SysUserRole> sysUserRoleList) {
        return sysUserRoleList.stream()
                .allMatch(sysUserRole ->
                        sysUserRole.getUserId() != null &&
                                sysUserRole.getRoleId() != null
                );
    }

    /**
     * 批量新增数据
     *
     * @param sysUserRoleList 用户角色条件参数
     * @return 添加成功行数
     */
    @PostMapping("/addBatch")
    public ResponseEntity<String> addBatch(@RequestBody List<SysUserRole> sysUserRoleList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysUserRoleList) && allPropertiesNotNull(sysUserRoleList)) {
            records = sysUserRoleService.insertBatch(sysUserRoleList);
            return ResponseEntity.ok("添加成功行数：" + records);
        } else {
            return ResponseEntity.ok("添加失败");
        }
    }


    /**
     * 批量删除数据
     *
     * @param sysUserRoleList 用户角色条件参数
     * @return 删除成功行数
     */
    @DeleteMapping("/deleteBatch")
    public ResponseEntity<String> deleteBatch(@RequestBody List<SysUserRole> sysUserRoleList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysUserRoleList) && allPropertiesNotNull(sysUserRoleList)) {
            records = sysUserRoleService.deleteBatch(sysUserRoleList);
            return ResponseEntity.ok("删除成功行数：" + records);
        } else {
            return ResponseEntity.ok("删除失败");
        }
    }

    /**
     * 批量添加或删除数据
     *
     * @param sysUserRoleList 实例对象集合
     * @return 删除成功行数
     */
    @PostMapping("/insertOrDeleteUserRole")
    private ResponseEntity<String> insertOrDeleteUserRole(@RequestBody List<SysUserRole> sysUserRoleList) {
        // 判断是否为同一用户
        if (!CollectionUtils.isEmpty(sysUserRoleList)) {
            boolean isSameUserId = sysUserRoleList.stream()
                    .map(SysUserRole::getUserId)
                    .distinct()
                    .count() <= 1;
            if (isSameUserId) {
                int d = 0;
                int i = 0;
                List<SysUserRole> ownedSysUserRole = this.sysUserRoleService.queryByUserId(SecurityUtils.getLoginUser().getUser().getUserId());
                List<SysUserRole> deleteList = ownedSysUserRole.stream()
                        .filter(item -> !sysUserRoleList.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(deleteList) && allPropertiesNotNull(deleteList)) {
                    d = this.sysUserRoleService.deleteBatch(deleteList);
                }
                List<SysUserRole> insertList = sysUserRoleList.stream()
                        .filter(item -> !ownedSysUserRole.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(insertList) && allPropertiesNotNull(insertList)) {
                    i = this.sysUserRoleService.insertBatch(insertList);
                }
                return ResponseEntity.ok("删除" + d + "个角色，添加" + i + "个角色");
            }
            return ResponseEntity.ok("用户不匹配");
        } else if (sysUserRoleList != null) {
            sysUserRoleService.truncateData();
            return ResponseEntity.ok("数据已经清空");
        } else {
            return ResponseEntity.ok("无操作");
        }
    }


    /**
     * 通过用户id查询数据
     *
     * @param userId 用户id
     * @return 数据集合
     */
    @GetMapping("/queryByUserId")
    public ResponseEntity<List<SysUserRole>> queryByUserId(Long userId) {
        return ResponseEntity.ok(sysUserRoleService.queryByUserId(userId));
    }
}

