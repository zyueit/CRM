package eon.web.controller;

import eon.domain.Guarantee;
import eon.domain.GuaranteeItem;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.service.IGuaranteeItemService;
import org.aspectj.weaver.loadtime.Aj;
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

    @RequestMapping("/guaranteeItem_update")
    @ResponseBody
    public AjaxResult update(GuaranteeItem guaranteeItem) {
        try {
            guaranteeItemService.update(guaranteeItem);
            return new AjaxResult(true, "成功编辑!");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常,请联系管理员!");
        }
    }

    @RequestMapping("/guaranteeItem_save")
    @ResponseBody
    public AjaxResult save(GuaranteeItem guaranteeItem) {
        try {
            guaranteeItemService.save(guaranteeItem);
            return new AjaxResult(true, "成功保存!");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常,请联系管理员!");
        }
    }

    @RequestMapping("/guaranteeItem_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        try {
            guaranteeItemService.delete(id);
            return new AjaxResult(true, "成功删除!");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常,请联系管理员!");
        }
    }
}
