package eon.web.controller;

import eon.domain.CustomerTransfer;
import eon.domain.Employee;
import eon.page.PageResult;
import eon.query.EmployeeQueryObject;
import eon.query.QueryObject;
import eon.service.ICustomerTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerTransferController {

    @Autowired
    private ICustomerTransferService customerTransferService;

    @RequestMapping("/customerTransfer")
    public String index() {
        return "customerTransfer";
    }

    @RequestMapping("/customerTransfer_query")
    @ResponseBody
    public PageResult<CustomerTransfer> selectByCondition(QueryObject qo) {
        return customerTransferService.query(qo);
    }
}
