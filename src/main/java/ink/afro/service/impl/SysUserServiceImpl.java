package ink.afro.service.impl;

import ink.afro.entity.SysUser;
import ink.afro.mapper.SysUserMapper;
import ink.afro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:49
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper sysUserMapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public boolean insert(SysUser sysUser) {
        return this.sysUserMapper.insert(sysUser) > 0;
    }

    @Override
    public boolean deleteById(Long userId) {
        return this.sysUserMapper.deleteById(userId) > 0;
    }

    @Override
    public boolean update(SysUser sysUser) {
        return this.sysUserMapper.update(sysUser) > 0;
    }

    @Override
    public SysUser queryById(Long userId) {
        return this.sysUserMapper.queryById(userId);
    }

    @Override
    public SysUser queryByName(String userName) {
        return this.sysUserMapper.queryByName(userName);
    }

    @Override
    public Page<SysUser> queryByPage(SysUser sysUser, PageRequest pageRequest) {
        long total = this.sysUserMapper.count(sysUser);
        return new PageImpl<>(this.sysUserMapper.queryAllByLimit(sysUser, pageRequest), pageRequest, total);
    }

    @Override
    public Page<SysUser> queryByDepartmentId(Long departmentId, SysUser sysUser, PageRequest pageRequest) {
        long total = this.sysUserMapper.queryCountByDepartmentId(departmentId, sysUser);
        return new PageImpl<>(this.sysUserMapper.queryByDepartmentIdByLimit(departmentId, sysUser, pageRequest), pageRequest, total);
    }

    @Override
    public boolean isUniqueUsername(SysUser user) {
        long userId = ObjectUtils.isEmpty(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = sysUserMapper.queryByName(user.getUserName());
        return ObjectUtils.isEmpty(info) || info.getUserId() == userId;
    }

    @Override
    public boolean isUniquePhoneNumber(SysUser user) {
        long userId = ObjectUtils.isEmpty(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = sysUserMapper.queryByPhoneNumber(user.getPhoneNumber());
        return ObjectUtils.isEmpty(info) && info.getUserId() == userId;
    }

    @Override
    public boolean isUniqueEmail(SysUser user) {
        long userId = ObjectUtils.isEmpty(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = sysUserMapper.queryByPhoneNumber(user.getPhoneNumber());
        return ObjectUtils.isEmpty(info) || info.getUserId() == userId;
    }
}
