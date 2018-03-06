package eon.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Alias("Role")
public class Role {
    private Long id;
    private String sn;//角色编号
    private String name;
    private Boolean state;//0：禁用 1：正常
    private List<Permission> permissions = new ArrayList<>();//role_permission role_id permission_id

    public String getPermissionsName() {
        if (permissions.size() == 0) {
            return "—";
        }
        StringBuilder sb = new StringBuilder(80);
        sb.append("[");
        for (Permission p : permissions) {
            sb.append(p.getName()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }
}
