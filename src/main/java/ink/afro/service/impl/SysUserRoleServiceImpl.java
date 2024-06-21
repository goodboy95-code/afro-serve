package ink.afro.service.impl;

import ink.afro.entity.SysUserRole;
import ink.afro.mapper.SysUserRoleMapper;
import ink.afro.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:33
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    private final SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    public SysUserRoleServiceImpl(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public int insertBatch(List<SysUserRole> sysUserRoleList) {
       return this.sysUserRoleMapper.insertBatch(sysUserRoleList);
    }

    @Override
    public int deleteBatch(List<SysUserRole> sysUserRoleList) {
        return this.sysUserRoleMapper.deleteBatch(sysUserRoleList);
    }

    @Override
    public void truncateData() {
        this.sysUserRoleMapper.truncateData();
    }

    @Override
    public List<SysUserRole> queryByUserId(Long userId) {
        return this.sysUserRoleMapper.queryByUserId(userId);
    }
}
