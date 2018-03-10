package eon.web.controller;

import eon.domain.ContractOrder;
import eon.page.PageResult;
import eon.query.ContractOrderQueryObejct;
import eon.service.IContractOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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


}
