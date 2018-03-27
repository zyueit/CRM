package eon.util;

import javax.servlet.http.HttpServletRequest;

public class UserContext {
    public static final String USER_IN_SESSION = "UserInSession";
    public static final String PERMISSION_IN_SESSION = "PermissionInSession";
    public static final String LOGINID_IN_SESSION = "LoginIdInSession";
    public static final String MENUS_IN_SESSION = "MenusInSession";
    public static final String ROLES_IN_SESSION = "RolesInSession";

    //当前线程绑定的请求对象
    public static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        threadLocal.set(request);
    }

    public static HttpServletRequest getRequest() {
        return threadLocal.get();
    }
}
