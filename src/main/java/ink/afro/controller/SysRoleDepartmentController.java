package ink.afro.controller;

import ink.afro.entity.SysRoleDepartment;
import ink.afro.entity.SysUser;
import ink.afro.service.SysRoleDepartmentService;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色和部门关联表(SysRoleDepartment)表控制层
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:32
 */
@RestController
@RequestMapping("sysRoleDepartment")
public class SysRoleDepartmentController {
    /**
     * 服务对象
     */
    private final SysRoleDepartmentService sysRoleDepartmentService;

    @Autowired
    public SysRoleDepartmentController(SysRoleDepartmentService sysRoleDepartmentService) {
        this.sysRoleDepartmentService = sysRoleDepartmentService;
    }

    public boolean allPropertiesNotNull(List<SysRoleDepartment> sysRoleDepartmentList) {
        return sysRoleDepartmentList.stream()
                .allMatch(sysRoleDepartment ->
                        sysRoleDepartment.getRoleId() != null &&
                                sysRoleDepartment.getDepartmentId() != null
                );
    }

    /**
     * 批量新增数据
     *
     * @param sysRoleDepartmentList 实例对象集合
     * @return 添加成功行数
     */
    @PostMapping("/addBatch")
    public ResponseEntity<String> addBatch(List<SysRoleDepartment> sysRoleDepartmentList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysRoleDepartmentList) && allPropertiesNotNull(sysRoleDepartmentList)) {
            records = sysRoleDepartmentService.insertBatch(sysRoleDepartmentList);
            return ResponseEntity.ok("添加成功行数：" + records);
        } else {
            return ResponseEntity.ok("添加失败");
        }
    }

    /**
     * 批量删除数据
     *
     * @param sysRoleDepartmentList 实例对象集合
     * @return 删除成功行数
     */
    @DeleteMapping("/deleteBatch")
    public ResponseEntity<String> deleteBatch(List<SysRoleDepartment> sysRoleDepartmentList) {
        int records;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user) && !CollectionUtils.isEmpty(sysRoleDepartmentList) && allPropertiesNotNull(sysRoleDepartmentList)) {
            records = sysRoleDepartmentService.deleteBatch(sysRoleDepartmentList);
            return ResponseEntity.ok("删除成功行数：" + records);
        } else {
            return ResponseEntity.ok("删除失败");
        }
    }

    /**
     * 批量添加或删除数据
     *
     * @param sysRoleDepartment 实例对象集合
     * @return 删除成功行数
     */
    @PostMapping("/insertOrDeleteRoleDepartment")
    private ResponseEntity<String> insertOrDeleteRoleDepartment(@RequestBody List<SysRoleDepartment> sysRoleDepartment) {
        // 判断是否为同一角色
        if (!CollectionUtils.isEmpty(sysRoleDepartment)) {
            boolean isSameRoleId = sysRoleDepartment.stream()
                    .map(SysRoleDepartment::getRoleId)
                    .distinct()
                    .count() <= 1;
            if (isSameRoleId) {
                int d = 0;
                int i = 0;
                List<SysRoleDepartment> ownedSysRoleDepartment = this.sysRoleDepartmentService.queryByRoleId(sysRoleDepartment.get(0).getRoleId());
                List<SysRoleDepartment> deleteList = ownedSysRoleDepartment.stream()
                        .filter(item -> !sysRoleDepartment.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(deleteList) && allPropertiesNotNull(deleteList)) {
                    d = this.sysRoleDepartmentService.deleteBatch(deleteList);
                }
                List<SysRoleDepartment> insertList = sysRoleDepartment.stream()
                        .filter(item -> !ownedSysRoleDepartment.contains(item))
                        .toList();
                if (!CollectionUtils.isEmpty(insertList) && allPropertiesNotNull(insertList)) {
                    i = this.sysRoleDepartmentService.insertBatch(insertList);
                }
                return ResponseEntity.ok("删除" + d + "个部门，添加" + i + "个部门");
            }
            return ResponseEntity.ok("角色不匹配");
        } else {
            return ResponseEntity.ok("无操作");
        }
    }

    /**
     * 通过角色ID查询数据
     * @param roleId 角色ID
     * @return 集合数据
     */
    @GetMapping("/queryByRoleId")
    public ResponseEntity<List<SysRoleDepartment>> queryByRoleId(Integer roleId) {
        return ResponseEntity.ok(this.sysRoleDepartmentService.queryByRoleId(roleId));
    }
}

