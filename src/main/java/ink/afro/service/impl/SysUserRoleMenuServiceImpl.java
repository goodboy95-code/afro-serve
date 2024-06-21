package ink.afro.service.impl;

import ink.afro.entity.SysUserRoleMenu;
import ink.afro.mapper.SysUserRoleMenuMapper;
import ink.afro.service.SysUserRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户、角色和快捷导航（菜单）关联表(SysUserRoleMenu)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-01-03 04:32:10
 */
@Service("sysUserRoleMenuService")
public class SysUserRoleMenuServiceImpl implements SysUserRoleMenuService {
    private final SysUserRoleMenuMapper sysUserRoleMenuMapper;

    @Autowired
    public SysUserRoleMenuServiceImpl(SysUserRoleMenuMapper sysUserRoleMenuMapper) {
        this.sysUserRoleMenuMapper = sysUserRoleMenuMapper;
    }

    @Override
    public int insertBatch(List<SysUserRoleMenu> sysUserRoleMenu) {
        return this.sysUserRoleMenuMapper.insertBatch(sysUserRoleMenu);
    }

    @Override
    public int deleteBatch(List<SysUserRoleMenu> sysUserRoleMenu) {
        return this.sysUserRoleMenuMapper.deleteBatch(sysUserRoleMenu);
    }

    @Override
    public void truncateData() {
        this.sysUserRoleMenuMapper.truncateData();
    }

    @Override
    public List<SysUserRoleMenu> queryByUserIdAndRoleId(Long userId, Integer roleId) {
        return this.sysUserRoleMenuMapper.queryByUserIdAndRoleId(userId, roleId);
    }
}
