package eon.web.controller;

import eon.domain.Role;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.QueryObject;
import eon.service.IRoleService;
import eon.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredPermission("角色管理")
@Controller
public class RoleController extends BaseController {
    @Autowired
    private IRoleService roleService;

    @RequiredPermission("角色列表")
    @RequestMapping("/role")
    public String index() {
        return "roleList";
    }

    @RequestMapping("/role_selectByCondition")
    @ResponseBody
    public PageResult<Role> selectByCondition(QueryObject qo) {
        return roleService.selectByCondition(qo);
    }

    @RequiredPermission("角色新增")
    @RequestMapping("/role_save")
    @ResponseBody
    public AjaxResult save(Role role) {
        AjaxResult result;
        role.setState(true);
        try {
            roleService.save(role);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("角色编辑")
    @RequestMapping("/role_update")
    @ResponseBody
    public AjaxResult update(Role role) {
        AjaxResult result;
        try {
            roleService.update(role);
            result = new AjaxResult(true, "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("角色禁用")
    @RequestMapping("role_disable")
    @ResponseBody
    public AjaxResult disable(Long id) {
        AjaxResult result;
        try {
            roleService.disable(id);
            result = new AjaxResult(true, "禁用成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("禁用异常，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/role_list")
    @ResponseBody
    public List<Role> list() {
        return roleService.list();
    }
}
