package eon.web.interceptor;

import eon.domain.Employee;
import eon.service.IEmployeeService;
import eon.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //将当前请求绑定到线程
        UserContext.setRequest(request);
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        if (user != null) {
            //判断当前session中的loginId是否和数据库的匹配，不匹配则强制登出
            String loginIdInSession = (String) session.getAttribute(UserContext.LOGINID_IN_SESSION);
            String loginId = employeeService.queryLoginId(user.getId());
            if (loginId.equals(loginIdInSession)) {
                return true;
            }
            session.invalidate();
            response.sendRedirect("/forcedOffline.jsp");
            return false;
        }
        response.sendRedirect("/login.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
