package eon.web.controller;

import eon.domain.Employee;
import eon.domain.PotentialCustomer;
import eon.domain.SystemDictionaryItem;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.PotentialCustomerQueryObject;
import eon.service.IPotentialCustomerService;
import eon.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class PotentialCustomerController {
    @Autowired
    private IPotentialCustomerService potentialCustomerService;

    @RequestMapping("/potentialCustomer")
    public String index() {
        return "potentialCustomer";
    }

    @RequestMapping("/potentialCustomer_save")
    @ResponseBody
    public AjaxResult save(PotentialCustomer potentialCustomer) {
        AjaxResult result;
        try {
            potentialCustomerService.save(potentialCustomer);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/potentialCustomer_update")
    @ResponseBody
    public AjaxResult update(PotentialCustomer potentialCustomer) {
        AjaxResult result;
        try {
            potentialCustomerService.update(potentialCustomer);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequestMapping("/potentialCustomer_query")
    @ResponseBody
    public PageResult<PotentialCustomer> query(PotentialCustomerQueryObject qo, HttpSession session) {
        Employee user = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        qo.setUid(user.getId());
        return potentialCustomerService.query(qo);
    }


    @RequestMapping("/potentialCustomer_approve")
    @ResponseBody
    public AjaxResult approve(Long id, Long state) {
        try {
            potentialCustomerService.approve(id, state);
            return new AjaxResult(true, "审核成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("异常，请联系管理员！");
        }
    }
    //移交功能
    @RequestMapping("/potentialCustomer_deliver")
    @ResponseBody
    public AjaxResult deliver(PotentialCustomer potentialCustomer) {
        try {
            potentialCustomerService.deliver(potentialCustomer);
            return new AjaxResult(true, "移交成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("异常，请联系管理员！");
        }
    }

}
