package ink.afro.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表(SysMenu)实体类
 *
 * @author goodboy95-code
 */
@Data
public class SysMenu {
    /**
     * 菜单ID
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父菜单ID
     */
    private Integer parentId;
    /**
     * 显示顺序
     */
    private Object orderNum;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 路由参数
     */
    private String query;
    /**
     * 是否为外链（0 是，1 否）
     */
    private String isFrame;
    /**
     * 是否缓存（0 缓存，1 不缓存）
     */
    private String isCache;
    /**
     * 菜单类型（M 目录，C 菜单， B 按钮）
     */
    private String menuType;
    /**
     * 菜单显示状态（0 显示，1 隐藏）
     */
    private String visible;
    /**
     * 菜单使用状态（0 正常，1 停用）
     */
    private String status;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
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
     * 子菜单
     */
    private List<SysMenu> children = new ArrayList<>();
    /**
     * 备注
     */
    private String remark;
}

