package ink.afro.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门表(SysDepartment)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysDepartment {
    /**
     * 部门ID
     */
    private Long departmentId;
    /**
     * 父部门ID
     */
    private Long parentId;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门状态（0 正常，1 停用）
     */
    private String status;
    /**
     * 删除标志（0 代表存在，2 代表删除）
     */
    private String delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 缩写
     */
    private String abbreviation;
    /**
     * 子部门
     */
    private List<SysDepartment> children = new ArrayList<>();
}

