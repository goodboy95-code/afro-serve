package ink.afro.service.impl;

import ink.afro.entity.SysRoleMenu;
import ink.afro.mapper.SysRoleMenuMapper;
import ink.afro.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:32
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    public SysRoleMenuServiceImpl(SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    @Override
    public int insertBatch(List<SysRoleMenu> sysRoleMenu) {
        return this.sysRoleMenuMapper.insertBatch(sysRoleMenu);
    }

    @Override
    public int deleteBatch(List<SysRoleMenu> sysRoleMenu) {
        return this.sysRoleMenuMapper.deleteBatch(sysRoleMenu);
    }

    @Override
    public void truncateData() {
        this.sysRoleMenuMapper.truncateData();
    }

    @Override
    public List<SysRoleMenu> queryByRoleId(Integer roleId) {
        return this.sysRoleMenuMapper.queryByRoleId(roleId);
    }
}
