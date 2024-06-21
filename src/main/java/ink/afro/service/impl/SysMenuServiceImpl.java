package ink.afro.service.impl;

import ink.afro.entity.SysMenu;
import ink.afro.entity.SysRole;
import ink.afro.mapper.SysMenuMapper;
import ink.afro.mapper.SysRoleMapper;
import ink.afro.service.SysMenuService;
import ink.afro.utils.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author goodboy95-code
 * @since 2024-01-01 21:43:48
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper, SysRoleMapper sysRoleMapper) {
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public boolean insert(SysMenu sysMenu) {
        return this.sysMenuMapper.insert(sysMenu) > 0;
    }

    @Override
    public boolean deleteById(Integer menuId) {
        return this.sysMenuMapper.deleteById(menuId) > 0;
    }

    @Override
    public SysMenu update(SysMenu sysMenu) {
        this.sysMenuMapper.update(sysMenu);
        return this.queryById(sysMenu.getMenuId());
    }

    @Override
    public SysMenu queryById(Integer menuId) {
        return this.sysMenuMapper.queryById(menuId);
    }

    @Override
    public SysMenu queryByName(String menuName) {
        return this.sysMenuMapper.queryByName(menuName);
    }

    @Override
    public Page<SysMenu> queryByPage(SysMenu sysMenu, PageRequest pageRequest) {
        long total = this.sysMenuMapper.count(sysMenu);
        return new PageImpl<>(this.sysMenuMapper.queryAllByLimit(sysMenu, pageRequest), pageRequest, total);
    }

    @Override
    public List<SysMenu> queryMenuTree() {
        List<SysMenu> sysMenus = sysMenuMapper.queryAll();
        if (!ObjectUtils.isEmpty(sysMenus)) {
            return MenuUtils.getChildMenus(sysMenus, 0);
        }
        return new ArrayList<>();
    }

    @Override
    public SysMenu queryMenuTreeBDM() {
        List<SysMenu> sysMenus = sysMenuMapper.queryAll();
        if (!ObjectUtils.isEmpty(sysMenus)) {
            return MenuUtils.getChildMenusBDM(sysMenus, 0);
        }
        return new SysMenu();
    }

    @Override
    public List<SysMenu> queryMenuTreeByRoleId(Long userId, Integer roleId) {
        List<SysMenu> menus = null;
        List<SysRole> sysRoles = sysRoleMapper.queryByUserId(userId);

        if (!ObjectUtils.isEmpty(sysRoles)) {
            if (!ObjectUtils.isEmpty(roleId)) {
                Optional<SysRole> sysRoleStream = sysRoles.stream().filter((sysRole) -> sysRole.getRoleId().equals(roleId)).findFirst();
                if (sysRoleStream.isPresent()) {
                    menus = sysMenuMapper.queryByRoleId(sysRoleStream.get().getRoleId());
                }
            } else {
                System.out.println("没有传入roleId");
            }
            if (!ObjectUtils.isEmpty(menus)) {
                return MenuUtils.getChildMenus(menus, 0);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public SysMenu queryMenuTreeBDMByRoleId(Integer roleId) {
        List<SysMenu> menus = null;
        menus = sysMenuMapper.queryBDMByRoleId(roleId);
        if (!ObjectUtils.isEmpty(menus)) {
            return MenuUtils.getChildMenusBDM(menus, 0);
        }
        return new SysMenu();
    }

    @Override
    public List<String> querySysMenuPermsByUserId(Long userId) {
        return sysMenuMapper.queryPermsByUserId(userId);
    }

    @Override
    public List<String> querySysMenuPermsByRoleId(Integer roleId) {
        return sysMenuMapper.queryPermsByRoleId(roleId);
    }

    @Override
    public List<SysMenu> querySysMenusByUserIdAndRoleId(Long userId, Integer roleId) {
        return sysMenuMapper.queryByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public Set<Integer> querySysMenuIdsByPaths(Set<String> routePath) {
        List<SysMenu> userList = sysMenuMapper.queryByPaths(routePath);
        Set<Integer> menuIds = new HashSet<>();
        for (SysMenu sysMenu : userList) {
            menuIds.add(sysMenu.getMenuId());
        }
        return menuIds;
    }
}
