package eon.query;

import eon.domain.Employee;
import eon.domain.Role;
import eon.util.StringUtil;
import eon.util.UserContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PotentialChartQueryObejct extends QueryObject {
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String groupBy = "月份";
    private Long uid;

    public String getKeyword() {
        return !StringUtil.isbank(keyword) ? keyword : null;
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
