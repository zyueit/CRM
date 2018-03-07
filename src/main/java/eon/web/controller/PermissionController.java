package eon.web.controller;

import eon.domain.Permission;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.QueryObject;
import eon.service.IPermissionService;
import eon.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredPermission("权限管理")
@Controller
public class PermissionController extends BaseController {
    @Autowired
    private IPermissionService permissionService;

    @RequiredPermission("权限列表")
    @RequestMapping("/permission")
    public String index() {
        return "permissionList";
    }

    @RequestMapping("/permission_selectByCondition")
    @ResponseBody
    public PageResult<Permission> selectByCondition(QueryObject qo) {
        return permissionService.selectByCondition(qo);
    }

    @RequiredPermission("权限加载")
    @RequestMapping("/permission_load")
    @ResponseBody
    public AjaxResult load() {
        AjaxResult result;
        try {
            permissionService.load();
            result = new AjaxResult(true, "权限加载成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("权限加载异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("权限删除")
    @RequestMapping("/permission_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result;
        try {
            permissionService.delete(id);
            result = new AjaxResult(true, "权限删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("权限删除异常，请联系管理员！");
        }
        return result;
    }
}
