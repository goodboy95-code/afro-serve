package ink.afro.utils;

import ink.afro.entity.SysDepartment;
import ink.afro.entity.vo.DepartmentInfo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DepartmentUtils {

    /**
     * 构建前端路由所需要的路由树
     *
     * @param sysDepartments 部门集合
     * @return 前端需要的部门集合
     */
    public static List<DepartmentInfo> buildDepartmentInfo(List<SysDepartment> sysDepartments) {
        List<DepartmentInfo> departmentInfos = new LinkedList<>();
        for (SysDepartment department : sysDepartments) {
            DepartmentInfo departmentInfo = new DepartmentInfo();
            departmentInfo.setId(department.getDepartmentId());
            departmentInfo.setLabel(department.getDepartmentName());
            departmentInfo.setValue(department.getDepartmentId());
            List<SysDepartment> children = department.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                departmentInfo.setChildren(buildDepartmentInfo(children));
            }else {
                departmentInfo.setLeaf(true);
            }
            departmentInfos.add(departmentInfo);
        }
        return departmentInfos;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param allDepartment  查询到的department集合
     * @param parentId 传入的父节点ID
     * @return List<SysDepartment>
     */
    public static List<SysDepartment> getChildDepartments(List<SysDepartment> allDepartment, Long parentId) {
        List<SysDepartment> departmentTree = new ArrayList<>();
        for (SysDepartment cDepartment : allDepartment) {
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (ObjectUtils.nullUnSafeEquals(cDepartment.getParentId(), parentId)) {
                recursionCollection(allDepartment, cDepartment);
                departmentTree.add(cDepartment);
            }
        }
        return departmentTree;
    }

    /**
     * 递归集合
     *
     * @param allList 查询到的department集合
     * @param cDepartment   子节点
     */
    private static void recursionCollection(List<SysDepartment> allList, SysDepartment cDepartment) {
        // 得到子节点集合
        List<SysDepartment> childTree = new ArrayList<>();
        for (SysDepartment n : allList) {
            if (ObjectUtils.nullUnSafeEquals(n.getParentId(), cDepartment.getDepartmentId())) {
                childTree.add(n);
            }
        }
        cDepartment.setChildren(childTree);
        for (SysDepartment child : childTree) {
            // 判断是否有子节点
            List<SysDepartment> departmentList = allList.stream()
                    .filter(department -> ObjectUtils.nullUnSafeEquals(department.getParentId(), cDepartment.getDepartmentId()))
                    .toList();
            if (!CollectionUtils.isEmpty(departmentList)) {
                recursionCollection(allList, child);
            }
        }
    }
}
