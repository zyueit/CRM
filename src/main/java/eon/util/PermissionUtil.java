package eon.util;

import eon.domain.Employee;
import eon.domain.Menu;
import eon.domain.Permission;
import eon.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
public class PermissionUtil {
    //静态成员变量不能直接使用Autowitred注入，采用贴属性方法注入
    private static IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        PermissionUtil.permissionService = permissionService;
    }

    public static boolean checkPermission(String expression) {
        HttpSession session = UserContext.getRequest().getSession();
        Employee user = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        //如果是管理员admin，表示有权限
        if (user.getAdmin() != null && user.getAdmin()) {
            return true;
        }
        //访问是否要权限
        Permission permission = permissionService.queryByExp(expression);
        if (permission == null) {
            //不需要权限
            return true;
        }
        //用户是否有该方法的权限或者有该实体的所有操作权限
        Set<String> set = (Set<String>) session.getAttribute(UserContext.PERMISSION_IN_SESSION);
        return set.contains(expression) || set.contains(expression.split(":")[0] + ":all");
    }

    public static void checkPermissionFORMenu(List<Menu> menus) {
        for (Iterator<Menu> it = menus.iterator(); it.hasNext(); ) {
            Menu menu = it.next();
            if (!checkPermission(menu.getResource())) {
                it.remove();
            } else {
                checkPermissionFORMenu(menu.getChildren());
            }
        }
    }
}
