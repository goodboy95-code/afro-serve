package ink.afro.entity.params;

import ink.afro.entity.SysRole;
import ink.afro.entity.SysUser;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserInfo {
    private Set<String> permissions;
    private List<SysRole> roles;
    private SysUser user;
}
