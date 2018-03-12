package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Alias("Employee")
public class Employee {
    private Long id;
    private String username;
    private String realName;
    private String password;
    private String tel;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date inputTime;
    private Boolean state;//1:正常   0:离职
    private Boolean admin;//1:管理员  0:普通员工

    private Department dept;//dept_id
    private List<Role> roles = new ArrayList<>();//employee_role employee_id role_id

    public String getRolesName() {
        if (admin != null && admin) {
            return "[管理员]";
        }
        if (roles.size() == 0) {
            return "—";
        }
        StringBuilder sb = new StringBuilder(80);
        sb.append("[");
        for (Role r : roles) {
            sb.append(r.getName()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", inputTime=" + inputTime +
                ", state=" + state +
                ", admin=" + admin +
                '}';
    }
}
