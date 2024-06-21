package ink.afro.service.impl;

import ink.afro.entity.SysRoleDepartment;
import ink.afro.mapper.SysRoleDepartmentMapper;
import ink.afro.service.SysRoleDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色和部门关联表(SysRoleDepartment)表服务实现类
 *
 * @author goodboy95-code
 * @since 2023-12-30 03:56:32
 */
@Service("sysRoleDepartmentService")
public class SysRoleDepartmentServiceImpl implements SysRoleDepartmentService {
    private final SysRoleDepartmentMapper sysRoleDepartmentMapper;

    @Autowired
    public SysRoleDepartmentServiceImpl(SysRoleDepartmentMapper sysRoleDepartmentMapper) {
        this.sysRoleDepartmentMapper = sysRoleDepartmentMapper;
    }

    @Override
    public int insertBatch(List<SysRoleDepartment> sysRoleDepartment) {
        return this.sysRoleDepartmentMapper.insertBatch(sysRoleDepartment);
    }

    @Override
    public int deleteBatch(List<SysRoleDepartment> sysRoleDepartment) {
        return this.sysRoleDepartmentMapper.deleteBatch(sysRoleDepartment);
    }

    @Override
    public void truncateData() {
        this.sysRoleDepartmentMapper.truncateData();
    }

    @Override
    public List<SysRoleDepartment> queryByRoleId(Integer roleId) {
        return this.sysRoleDepartmentMapper.queryByRoleId(roleId);
    }
}
