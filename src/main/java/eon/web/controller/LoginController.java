package eon.web.controller;

import eon.domain.Employee;
import eon.domain.Menu;
import eon.domain.Permission;
import eon.page.AjaxResult;
import eon.service.IEmployeeService;
import eon.service.IMenuService;
import eon.service.IPermissionService;
import eon.util.PermissionUtil;
import eon.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult DoLogin(HttpServletRequest request, String username, String password) {
        UserContext.setRequest(request);
        AjaxResult result;
        Employee user = employeeService.queryByLogin(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(UserContext.USER_IN_SESSION, user);
            //登陆生成的登陆ID，用来控制一个用户只能同时在一个地方登陆
            String loginId = UUID.randomUUID().toString();
            //分别将loginId保存到session和数据库中
            session.setAttribute(UserContext.LOGINID_IN_SESSION, loginId);
            employeeService.saveLoginIdForEmp(user.getId(), loginId);
            //查询用户的权限对象集合
            List<Permission> list = permissionService.queryByEmp(user.getId());
            //用户的权限表达式集合
            Set<String> set = new HashSet<>();
            for (Permission permission : list) {
                set.add(permission.getResource());
            }
            session.setAttribute(UserContext.PERMISSION_IN_SESSION, set);
            //将用户菜单加载到session中
            List<Menu> menus = menuService.queryRootMenus();
            //判断是否拥有子菜单权限---检查子菜单权限之后判断根菜单是否还有子菜单---没有子菜单时删除根菜单
            for (Menu menu : menus) {
                List<Menu> children = menu.getChildren();
                PermissionUtil.checkPermissionFORMenu(children);
                if (children.size() == 0) {
                    menus.remove(menu);
                }
            }
            session.setAttribute(UserContext.MENUS_IN_SESSION, menus);
            result = new AjaxResult(true, "登陆成功！");
        } else {
            result = new AjaxResult("账号或密码有误，请重新输入！");
        }
        return result;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
