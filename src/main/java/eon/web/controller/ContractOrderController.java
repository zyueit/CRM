package eon.web.controller;

import eon.domain.ContractOrder;
import eon.domain.DepositOrder;
import eon.domain.Employee;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.ContractOrderQueryObejct;
import eon.service.IContractOrderService;
import eon.util.FileUtil;
import eon.util.StringUtil;
import eon.util.UserContext;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ContractOrderController {
    @Autowired
    private IContractOrderService contractOrderService;

    @RequestMapping("/contractOrder")
    public String index() {
        return "contractOrder";
    }

    @RequestMapping("/contractOrder_query")
    @ResponseBody
    public PageResult<ContractOrder> query(ContractOrderQueryObejct qo) {
        return contractOrderService.query(qo);
    }

    @RequestMapping("/contractOrder_save")
    @ResponseBody
    public AjaxResult save(MultipartFile myfile, ContractOrder contractOrder, HttpServletRequest req) throws Exception {
        Employee user = (Employee) req.getSession().getAttribute(UserContext.USER_IN_SESSION);
        String uuid = UUID.randomUUID().toString().substring(0, 13);
        contractOrder.setSeller(user);
        contractOrder.setSn(uuid);
        contractOrder.setModifyTime(new Date());
        contractOrder.setModifyUser(user);
        String fileName = myfile.getOriginalFilename();
        String path = FileUtil.storeFile(myfile, fileName);
        if (!StringUtil.isbank(path)) {
            contractOrder.setFile(path);
        }
        try {
            contractOrderService.save(contractOrder);
            return new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/contractOrder_update")
    public AjaxResult update(MultipartFile myfile, ContractOrder contractOrder, HttpServletRequest req) throws Exception {
        Date date = new Date();
        Employee user = (Employee) req.getSession().getAttribute(UserContext.USER_IN_SESSION);
        String fileName = myfile.getOriginalFilename();
        if (!StringUtil.isbank(fileName)) {
            String path = FileUtil.storeFile(myfile, fileName);
            if (!StringUtil.isbank(path)) {
                contractOrder.setFile(path);
            }
            //先查询此订单是否已经有附件
            ContractOrder search = contractOrderService.getOne(contractOrder.getId());
            //有附件就把原来的附件删除
            if (!StringUtil.isbank(search.getFile())) {
                File oldFile = new File(search.getFile());
                oldFile.delete();
            }
        } else {
            //先查询此订单是否已经有附件
            ContractOrder search = contractOrderService.getOne(contractOrder.getId());
            String file = search.getFile();
            if (!StringUtil.isbank(file)) {
                contractOrder.setFile(file);
            }
        }
        contractOrder.setModifyUser(user);
        contractOrder.setModifyTime(date);
        try {
            contractOrderService.update(contractOrder);
            return new AjaxResult(true, "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/contractOrder_delete")
    public AjaxResult delete(Long id) {
        //先查询此订单是否已经有附件
        ContractOrder search = contractOrderService.getOne(id);
        //有附件就把原来的附件删除
        if (search.getFile() != null && !"".equals(search.getFile().trim())) {
            File oldFile = new File(search.getFile());
            oldFile.delete();
        }
        try {
            contractOrderService.delete(id);
            return new AjaxResult(true, "删除成功！");
        } catch (Exception e) {
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/contractOrder_approve")
    public AjaxResult approve(ContractOrder contractOrder) {
        try {
            contractOrder.setSignTime(new Date());
            contractOrderService.approve(contractOrder);
            return new AjaxResult(true, "审核成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/contractOrder_approveByDept")
    public AjaxResult approveByDept(ContractOrder contractOrder) {
        try {
            contractOrderService.approveByDept(contractOrder);
            return new AjaxResult(true, "审核成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

}
