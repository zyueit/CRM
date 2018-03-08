package eon.web.controller;

import eon.domain.Employee;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.EmployeeQueryObject;
import eon.service.IEmployeeService;
import eon.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@RequiredPermission("员工管理")
@Controller
public class EmployeeController extends BaseController {
    @Autowired
    private IEmployeeService employeeService;

    @RequiredPermission("员工列表")
    @RequestMapping("/employee")
    public String index() {
        return "employeeList";
    }

    @RequestMapping("/employee_selectByCondition")
    @ResponseBody
    public PageResult<Employee> selectByCondition(EmployeeQueryObject qo) {
        return employeeService.selectByCondition(qo);
    }

    @RequestMapping("/listForDept")
    @ResponseBody
    public List<Employee> listForDept() {
        return employeeService.listForDept();
    }


    @RequiredPermission("员工新增")
    @RequestMapping("/employee_save")
    @ResponseBody
    public AjaxResult save(Employee employee) {
        AjaxResult result;
        employee.setPassword("666666");//初始密码666666
        employee.setInputTime(new Date());
        employee.setState(true);
        employee.setAdmin(false);
        try {
            employeeService.save(employee);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("员工编辑")
    @RequestMapping("/employee_update")
    @ResponseBody
    public AjaxResult update(Employee employee) {
        AjaxResult result;
        try {
            employeeService.update(employee);
            result = new AjaxResult(true, "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("员工离职")
    @RequestMapping("employee_dimission")
    @ResponseBody
    public AjaxResult dimission(Long id) {
        AjaxResult result;
        try {
            employeeService.dimission(id);
            result = new AjaxResult(true, "离职成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("离职异常，请联系管理员！");
        }
        return result;
    }
}
