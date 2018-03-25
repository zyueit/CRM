package eon.web.controller;

import eon.domain.CustomerTransfer;
import eon.domain.Employee;
import eon.domain.PotentialCustomer;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.PotentialCustomerQueryObject;
import eon.service.ICustomerTransferService;
import eon.service.IPotentialCustomerService;
import eon.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class PotentialCustomerController {
    @Autowired
    private IPotentialCustomerService potentialCustomerService;
    @Autowired
    private ICustomerTransferService customerTransferService;

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
    public AjaxResult deliver(PotentialCustomer potentialCustomer, HttpSession session) {
        try {
            CustomerTransfer customerTransfer = new CustomerTransfer();
            customerTransfer.setCustomer(potentialCustomer);//客户
            customerTransfer.setTransTime(new Date());//移交时间
            customerTransfer.setTransUser((Employee) session.getAttribute(UserContext.USER_IN_SESSION));//移交人
            Employee inChargeUser = potentialCustomerService.get(potentialCustomer.getId()).getInChargeUser();
            customerTransfer.setOldSeller(inChargeUser);//老负责人
            customerTransfer.setNewSeller(potentialCustomer.getInChargeUser());//新负责人
            customerTransferService.save(customerTransfer);
            potentialCustomerService.deliver(potentialCustomer);
            return new AjaxResult(true, "移交成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("异常，请联系管理员！");
        }
    }

    //共享功能
    @RequestMapping("/potentialCustomer_share")
    @ResponseBody
    public AjaxResult share(PotentialCustomer customer) {
        try {
            potentialCustomerService.deliver(customer);
            return new AjaxResult(true, "共享成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("异常，请联系管理员！");
        }
    }

}
