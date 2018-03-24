package eon.web.controller;

import eon.domain.Employee;
import eon.domain.PotentialCustomer;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.CustomerQueryObject;
import eon.service.ICustomerService;
import eon.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @RequestMapping("/customer")
    public String index() {
        return "customer";
    }

    @RequestMapping("/customer_save")
    @ResponseBody
    public AjaxResult save(PotentialCustomer customer) {
        AjaxResult result;
        try {
            customerService.save(customer);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/customer_update")
    @ResponseBody
    public AjaxResult update(PotentialCustomer customer) {
        AjaxResult result;
        try {
            customerService.update(customer);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/customer_query")
    @ResponseBody
    public PageResult<PotentialCustomer> query(CustomerQueryObject qo, HttpSession session) {
        Employee user = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        qo.setUid(user.getId());
        return customerService.query(qo);
    }


    @RequestMapping("/customer_approve")
    @ResponseBody
    public AjaxResult approve(Long id, Long state) {
        try {
            customerService.approve(id, state);
            return new AjaxResult(true, "审核成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("异常，请联系管理员！");
        }
    }
    //移交功能
    @RequestMapping("/customer_deliver")
    @ResponseBody
    public AjaxResult deliver(PotentialCustomer customer) {
        try {
            customerService.deliver(customer);
            return new AjaxResult(true, "移交成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("异常，请联系管理员！");
        }
    }

}
