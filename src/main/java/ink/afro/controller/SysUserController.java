package ink.afro.controller;

import ink.afro.conf.SystemConfig;
import ink.afro.entity.SysUser;
import ink.afro.entity.other.LoginUser;
import ink.afro.security.service.TokenService;
import ink.afro.service.SysUserService;
import ink.afro.utils.FileUploadUtils;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    private final SysUserService sysUserService;

    private final TokenService tokenService;

    @Autowired
    public SysUserController(SysUserService sysUserService, TokenService tokenService) {
        this.sysUserService = sysUserService;
        this.tokenService = tokenService;
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体
     * @return 新增结果
     */
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('system:user:add')")
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody SysUser sysUser) {
        return ResponseEntity.ok(this.sysUserService.insert(sysUser));
    }

    /**
     * 删除数据
     *
     * @param userId 主键
     * @return 删除是否成功
     */
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('system:user:delete')")
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Long userId) {
        return ResponseEntity.ok(this.sysUserService.deleteById(userId));
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('system:user:edit')")
    @PutMapping("/edit")
    public ResponseEntity<Boolean> edit(@RequestBody SysUser sysUser) {
        return ResponseEntity.ok(this.sysUserService.update(sysUser));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param userId 主键
     * @return 单条数据
     */
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('system:user:query')")
    @GetMapping("/queryById")
    public ResponseEntity<SysUser> queryById(Long userId) {
        return ResponseEntity.ok(this.sysUserService.queryById(userId));
    }

    /**
     * 分页查询
     *
     * @param sysUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('system:user:query')")
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<SysUser>> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysUserService.queryByPage(sysUser, pageRequest));
    }

    /**
     * 通过部门ID分页查询
     *
     * @param departmentId      部门ID
     * @param userName    用户名
     * @param status      账号状态
     * @param phoneNumber 手机号码
     * @param pageNumber  当前页码
     * @param pageSize    每一页条数
     * @return 查询结果
     */
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('system:user:query')")
    @GetMapping("/queryByDepartmentId")
    public ResponseEntity<Page<SysUser>> queryByDepartmentId(
            Long departmentId,
            String userName,
            String status,
            String phoneNumber,
            int pageNumber,
            int pageSize
    ) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser.setStatus(status);
        sysUser.setPhoneNumber(phoneNumber);
        Page<SysUser> sysUsers = this.sysUserService.queryByDepartmentId(departmentId, sysUser, PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(sysUsers);
    }

    /**
     * 用户头像上传
     *
     * @param file 图片文件
     * @return ResponseEntity<MultipartFile>
     */
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('system:user:edit')")
    @PostMapping("/uploadAvatar")
    public ResponseEntity<String> avatar(@RequestParam("avatarFile") MultipartFile file) throws IOException {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (!file.isEmpty()) {
            String uploadPath = FileUploadUtils.upload(SystemConfig.getAvatarPath(), file);
            SysUser sysUser = new SysUser();
            sysUser.setUserId(loginUser.getUser().getUserId());
            sysUser.setAvatar(uploadPath);

            if (sysUserService.update(sysUser)) {
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(uploadPath);
                tokenService.setLoginUser(loginUser);

                return ResponseEntity.ok(uploadPath);
            }
        }
        return ResponseEntity.ok("上传图片异常，请联系管理员");
    }
}

