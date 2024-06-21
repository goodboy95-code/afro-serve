package ink.afro.service.impl;

import ink.afro.entity.SysRole;
import ink.afro.mapper.SysRoleMapper;
import ink.afro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:49
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public boolean insert(SysRole sysRole) {
        return this.sysRoleMapper.insert(sysRole) > 0;
    }

    @Override
    public boolean deleteById(Integer roleId) {
        return this.sysRoleMapper.deleteById(roleId) > 0;
    }

    @Override
    public SysRole update(SysRole sysRole) {
        this.sysRoleMapper.update(sysRole);
        return this.queryById(sysRole.getRoleId());
    }

    @Override
    public SysRole queryById(Integer roleId) {
        return this.sysRoleMapper.queryById(roleId);
    }

    @Override
    public SysRole queryByName(String roleName) {
        return this.sysRoleMapper.queryByName(roleName);
    }

    @Override
    public Page<SysRole> queryByPage(SysRole sysRole, PageRequest pageRequest) {
        long total = this.sysRoleMapper.count(sysRole);
        return new PageImpl<>(this.sysRoleMapper.queryAllByLimit(sysRole, pageRequest), pageRequest, total);
    }

    @Override
    public List<SysRole> queryByUserId(Long userId) {
        List<SysRole> roles;
        if (!ObjectUtils.isEmpty(userId)) {
            roles = sysRoleMapper.queryByUserId((userId));
        } else {
            roles = new ArrayList<>();
        }
        return roles;
    }

    @Override
    public List<SysRole> queryAll() {
        return sysRoleMapper.queryAll();
    }

    @Override
    public List<SysRole> queryRoleByUserId(Long userId) {
        return sysRoleMapper.queryByUserId((userId));
    }
}
