package ink.afro.service.impl;

import ink.afro.entity.SysDepartment;
import ink.afro.entity.SysRole;
import ink.afro.mapper.SysDepartmentMapper;
import ink.afro.mapper.SysRoleMapper;
import ink.afro.service.SysDepartmentService;
import ink.afro.utils.DepartmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 部门表(SysDepartment)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:48
 */
@Service("sysDepartmentService")
public class SysDepartmentServiceImpl implements SysDepartmentService {
    private final SysDepartmentMapper sysDepartmentMapper;

    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public SysDepartmentServiceImpl(SysDepartmentMapper sysDepartmentMapper, SysRoleMapper sysRoleMapper) {
        this.sysDepartmentMapper = sysDepartmentMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public boolean insert(SysDepartment sysDepartment) {
        return this.sysDepartmentMapper.insert(sysDepartment)> 0;
    }

    @Override
    public boolean deleteById(Long departmentId) {
        return this.sysDepartmentMapper.deleteById(departmentId) > 0;
    }

    @Override
    public SysDepartment update(SysDepartment sysDepartment) {
        this.sysDepartmentMapper.update(sysDepartment);
        return this.queryById(sysDepartment.getDepartmentId());
    }

    @Override
    public SysDepartment queryById(Long departmentId) {
        return this.sysDepartmentMapper.queryById(departmentId);
    }

    @Override
    public List<SysDepartment> queryAll() {
        return this.sysDepartmentMapper.queryAll();
    }

    @Override
    public SysDepartment queryByName(String departmentName) {
        return this.sysDepartmentMapper.queryByName(departmentName);
    }

    @Override
    public Page<SysDepartment> queryByPage(SysDepartment sysDepartment, PageRequest pageRequest) {
        long total = this.sysDepartmentMapper.count(sysDepartment);
        return new PageImpl<>(this.sysDepartmentMapper.queryAllByLimit(sysDepartment, pageRequest), pageRequest, total);
    }

    @Override
    public List<SysDepartment> queryDepartmentTree() {
        List<SysDepartment> sysDepartments = sysDepartmentMapper.queryAll();
        if (!ObjectUtils.isEmpty(sysDepartments)) {
            return DepartmentUtils.getChildDepartments(sysDepartments, 0L);
        }
        return new ArrayList<>();
    }

    @Override
    public List<SysDepartment> queryDepartmentTreeByRoleId(Long userId, Integer roleId) {
        List<SysDepartment> sysDepartments = null;
        List<SysRole> sysRoles = sysRoleMapper.queryByUserId(userId);

        if (!ObjectUtils.isEmpty(sysRoles)) {
            if (!ObjectUtils.isEmpty(roleId)) {
                Optional<SysRole> sysRoleStream = sysRoles.stream().filter((sysRole) -> sysRole.getRoleId().equals(roleId)).findFirst();
                if (sysRoleStream.isPresent()) {
                    sysDepartments = sysDepartmentMapper.queryByRoleId(sysRoleStream.get().getRoleId());
                }
            } else {
                System.out.println("没有传入roleId");
            }
            if (!ObjectUtils.isEmpty(sysDepartments)) {
                return DepartmentUtils.getChildDepartments(sysDepartments, 0L);
            }
        }
        return new ArrayList<>();
    }
}
