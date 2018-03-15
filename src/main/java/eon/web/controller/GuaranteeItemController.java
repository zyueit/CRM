package eon.web.controller;

import eon.domain.GuaranteeItem;
import eon.page.PageResult;
import eon.service.IGuaranteeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GuaranteeItemController {

    @Autowired
    private IGuaranteeItemService guaranteeItemService;

    @RequestMapping("/guaranteeItem_list")
    public String index() {
        return "guaranteeItem";
    }

    @RequestMapping("/guaranteeItem")
    @ResponseBody
    public List<GuaranteeItem> guaranteeItem(Long id) {
        return guaranteeItemService.selectByGuaranteeId(id);
    }
}
