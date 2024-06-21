package ink.afro.controller;

import ink.afro.entity.SysRole;
import ink.afro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息表(SysRole)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    private final SysRoleService sysRoleService;

    @Autowired
    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody SysRole sysRole) {
        return ResponseEntity.ok(this.sysRoleService.insert(sysRole));
    }

    /**
     * 删除数据
     *
     * @param roleId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Integer roleId) {
        return ResponseEntity.ok(this.sysRoleService.deleteById(roleId));
    }

    /**
     * 编辑数据
     *
     * @param sysRole 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<SysRole> edit(@RequestBody SysRole sysRole) {
        return ResponseEntity.ok(this.sysRoleService.update(sysRole));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param roleId 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<SysRole> queryById(Integer roleId) {
        return ResponseEntity.ok(this.sysRoleService.queryById(roleId));
    }

    /**
     * 分页查询
     *
     * @param roleName   角色名
     * @param status     角色状态
     * @param pageNumber 当前页码
     * @param pageSize   每一页条数
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<SysRole>> queryByPage(
            String roleName,
            String status,
            int pageNumber,
            int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        sysRole.setStatus(status);
        return ResponseEntity.ok(this.sysRoleService.queryByPage(sysRole, pageRequest));
    }

    /**
     * 通过用户ID获取该用户所有角色
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    @GetMapping("/queryByUserId")
    public ResponseEntity<List<SysRole>> queryByUserId(Long userId) {
        return ResponseEntity.ok(this.sysRoleService.queryByUserId(userId));
    }

    /**
     * 获取所有角色
     *
     * @return 查询结果
     */
    @GetMapping("/queryAll")
    public ResponseEntity<List<SysRole>> queryAll() {
        return ResponseEntity.ok(this.sysRoleService.queryAll());
    }
}

