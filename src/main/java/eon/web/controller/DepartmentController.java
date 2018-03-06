package eon.web.controller;

import eon.domain.Department;
import eon.domain.Employee;
import eon.page.AjaxResult;
import eon.service.IDepartmentService;
import eon.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@RequiredPermission("部门管理")
@Controller
public class DepartmentController extends BaseController {
    @Autowired
    private IDepartmentService departmentService;

    @RequiredPermission("部门列表")
    @RequestMapping("/department")
    public String index() {
        return "departmentList";
    }

    @RequestMapping("/listForEmp")
    @ResponseBody
    public List<Department> listForEmp() {
        return departmentService.listForEmp();
    }

    @RequestMapping("/department_list")
    @ResponseBody
    public List<Department> list() {
        return departmentService.list();
    }

    @RequiredPermission("部门新增")
    @RequestMapping("/department_save")
    @ResponseBody
    public AjaxResult save(Department department) {
        AjaxResult result;
        department.setState(true);
        try {
            departmentService.save(department);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("部门编辑")
    @RequestMapping("/department_update")
    @ResponseBody
    public AjaxResult update(Department department) {
        AjaxResult result;
        department.setState(true);
        try {
            departmentService.update(department);
            result = new AjaxResult(true, "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("部门停用")
    @RequestMapping("/department_disable")
    @ResponseBody
    public AjaxResult disable(Long id) {
        AjaxResult result;
        try {
            departmentService.disable(id);
            result = new AjaxResult(true, "停用成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("停用异常，请联系管理员！");
        }
        return result;
    }
}
