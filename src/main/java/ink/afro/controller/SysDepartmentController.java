package ink.afro.controller;

import ink.afro.annotation.Log;
import ink.afro.entity.SysDepartment;
import ink.afro.entity.SysUser;
import ink.afro.entity.vo.DepartmentInfo;
import ink.afro.enums.OperateType;
import ink.afro.service.SysDepartmentService;
import ink.afro.utils.DepartmentUtils;
import ink.afro.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表(SysDepartment)表控制层
 *
 * @author goodboy95-code
 */
@RestController
@RequestMapping("sysDepartment")
public class SysDepartmentController {
    /**
     * 服务对象
     */
    private final SysDepartmentService sysDepartmentService;

    @Autowired
    public SysDepartmentController(SysDepartmentService sysDepartmentService) {
        this.sysDepartmentService = sysDepartmentService;
    }

    /**
     * 新增数据
     *
     * @param sysDepartment 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody SysDepartment sysDepartment) {
        return ResponseEntity.ok(this.sysDepartmentService.insert(sysDepartment));
    }

    /**
     * 删除数据
     *
     * @param departmentId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Long departmentId) {
        return ResponseEntity.ok(this.sysDepartmentService.deleteById(departmentId));
    }

    /**
     * 编辑数据
     *
     * @param sysDepartment 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<SysDepartment> edit(@RequestBody SysDepartment sysDepartment) {
        return ResponseEntity.ok(this.sysDepartmentService.update(sysDepartment));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param departmentId 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<SysDepartment> queryById(Long departmentId) {
        return ResponseEntity.ok(this.sysDepartmentService.queryById(departmentId));
    }

    @GetMapping("/queryAll")
    public ResponseEntity<List<SysDepartment>> queryAll() {
        return ResponseEntity.ok(this.sysDepartmentService.queryAll());
    }
    /**
     * 分页查询
     *
     * @param departmentName   部门名
     * @param status     角色状态
     * @param pageNumber 当前页码
     * @param pageSize   每一页条数
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public ResponseEntity<Page<SysDepartment>> queryByPage(
            String departmentName,
            String status,
            int pageNumber,
            int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.setDepartmentName(departmentName);
        sysDepartment.setStatus(status);
        return ResponseEntity.ok(this.sysDepartmentService.queryByPage(sysDepartment, pageRequest));
    }

    /**
     * 获取部门树
     *
     * @return AxiosResult 部门树结果集
     */
    @Log(title = "queryDepartmentTree", operateType = OperateType.SELECT)
    @GetMapping("queryDepartmentTree")
    public ResponseEntity<List<DepartmentInfo>> getDepartmentTree() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysDepartment> sysDepartments;
        if (!ObjectUtils.isEmpty(user)) {
            sysDepartments = sysDepartmentService.queryDepartmentTree();
            if (!ObjectUtils.isEmpty(sysDepartments)) {
                return ResponseEntity.ok(DepartmentUtils.buildDepartmentInfo(sysDepartments));
            } else {
                System.out.println("获取部门失败");
            }
        } else {
            System.out.println("用户不存在");
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    /**
     * 通过角色Id获取部门树
     *
     * @param roleId 角色ID
     * @return AxiosResult 结果集
     */
    @Log(title = "queryDepartmentTreeByRoleId", operateType = OperateType.SELECT)
    @GetMapping("queryDepartmentTreeByRoleId")
    public ResponseEntity<List<DepartmentInfo>> getDepartmentTreeByRoleId(Integer roleId) {
        List<SysDepartment> sysDepartments;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (!ObjectUtils.isEmpty(user)) {
            sysDepartments = sysDepartmentService.queryDepartmentTreeByRoleId((user.getUserId()), roleId);
        } else {
            System.out.println("menu不存在");
            sysDepartments = new ArrayList<>();
        }
        return ResponseEntity.ok(DepartmentUtils.buildDepartmentInfo(sysDepartments));
    }
}

