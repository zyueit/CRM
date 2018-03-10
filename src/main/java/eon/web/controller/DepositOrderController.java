package eon.web.controller;

import eon.domain.DepositOrder;
import eon.domain.Employee;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.DepositOrderQueryObject;
import eon.service.IDepositOrderService;
import eon.util.FileUtil;
import eon.util.RequiredPermission;
import eon.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class DepositOrderController {
    @Autowired
    private IDepositOrderService depositOrderService;

    @RequiredPermission("订单列表")
    @RequestMapping("/depositOrder")
    public String index() {
        return "depositOrderList";
    }

    @RequestMapping("/depositOrder_query")
    @ResponseBody
    public PageResult<DepositOrder> query(DepositOrderQueryObject qo) {
        return depositOrderService.query(qo);
    }


    @RequestMapping("/depositOrder_update")
    @ResponseBody
    public AjaxResult update(DepositOrder depositOrder, HttpSession session, MultipartFile myfile) throws IOException {
        String fileName = myfile.getOriginalFilename();
        File file = FileUtil.storeFile(myfile, depositOrder, fileName);

        //先查询此订单是否已经有附件
        DepositOrder search = depositOrderService.get(depositOrder.getId());
        //有附件就把原来的附件删除
        if (search.getFile() != null && !"".equals(search.getFile().trim())) {
            File oldFile = new File(search.getFile());
            oldFile.delete();
        }

        try {
            depositOrder.setModifyUser((Employee) session.getAttribute(UserContext.USER_IN_SESSION));
            depositOrder.setModifyTime(new Date());
            depositOrderService.update(depositOrder);
            return new AjaxResult(true, "更新成功！");
        } catch (Exception e) {
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }


    @RequestMapping("/depositOrder_save")
    @ResponseBody
    public AjaxResult save(DepositOrder depositOrder, MultipartFile myfile, HttpServletRequest request) throws
            IOException {
        String fileName = myfile.getOriginalFilename();
        FileUtil.storeFile(myfile, depositOrder, fileName);


        HttpSession session = request.getSession();
        //当前客户端操作用户
        Employee user = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        //当前客户端时间
        Date date = new Date();
        depositOrder.setCreateTime(date);
        depositOrder.setModifyTime(date);
        depositOrder.setSeller(user);
        depositOrder.setModifyUser(user);
        try {
            depositOrderService.save(depositOrder);
            return new AjaxResult(true, "更新成功！");
        } catch (Exception e) {
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    //定金订单审批,销售人员审核的时候就代表客户确定签单的时候
    @RequestMapping("/depositOrder_approve")
    @ResponseBody
    public AjaxResult approve(DepositOrder depositOrder) {
        try {
            depositOrder.setSignTime(new Date());
            depositOrderService.approve(depositOrder);
            return new AjaxResult(true, "审批成功！");
        } catch (Exception e) {
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    //定金订单部门审批
    @RequestMapping("/depositOrder_approveByDept")
    @ResponseBody
    public AjaxResult approveByDept(Long id) {
        try {
            depositOrderService.approveByDept(id);
            return new AjaxResult(true, "审批成功！");
        } catch (Exception e) {
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    //定金订单财务审批
    @RequestMapping("/depositOrder_approveByFinance")
    @ResponseBody
    public AjaxResult approveByFinance(Long id) {
        try {
            depositOrderService.approveByFinance(id);
            return new AjaxResult(true, "审批成功！");
        } catch (Exception e) {
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

    //定金订单删除
    @RequestMapping("/depositOrder_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        //先查询此订单是否已经有附件
        DepositOrder search = depositOrderService.get(id);
        //有附件就把原来的附件删除
        if (search.getFile() != null && !"".equals(search.getFile().trim())) {
            File oldFile = new File(search.getFile());
            oldFile.delete();
        }
        try {
            depositOrderService.delete(id);
            return new AjaxResult(true, "删除成功！");
        } catch (Exception e) {
            return new AjaxResult("更新异常，请联系管理员！");
        }
    }

}
