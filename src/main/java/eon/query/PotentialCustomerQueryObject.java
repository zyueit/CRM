package eon.query;

import eon.domain.Employee;
import eon.domain.Role;
import eon.util.StringUtil;
import eon.util.UserContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Getter
@Setter
public class PotentialCustomerQueryObject extends QueryObject {
    private String keyword;
    private Integer state = -5;
    private Long uid;

    public String getKeyword() {
        return StringUtil.isbank(keyword) ? null : keyword;
    }

    public Long getUid() {
        HttpSession session = UserContext.getRequest().getSession();
        Employee e = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        if (e.getAdmin() == true) {
            return -5l;
        }
        List<Role> roles = (List<Role>) session.getAttribute(UserContext.ROLES_IN_SESSION);
        for (Role role : roles) {
            if ("营销主管".equals(role.getName())) {
                return -5l;
            }
        }
        return this.uid;
    }
}
