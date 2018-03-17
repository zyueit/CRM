package eon.web.controller;

import eon.domain.Guarantee;
import eon.page.AjaxResult;
import eon.page.PageResult;
import eon.query.GuaranteeQueryObject;
import eon.service.IGuaranteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuaranteeController {

    @Autowired
    private IGuaranteeService guaranteeService;

    @RequestMapping("/guarantee")
    public String index() {
        return "guarantee";
    }

    @RequestMapping("/guarantee_list")
    @ResponseBody
    public PageResult<Guarantee> query(GuaranteeQueryObject qo) {
        PageResult<Guarantee> query = guaranteeService.query(qo);
        return query;
    }

    @RequestMapping("/guarantee_update")
    @ResponseBody
    public AjaxResult update(Guarantee guarantee) {
        try {
            guaranteeService.update(guarantee);
            return new AjaxResult(true, "成功编辑!");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("更新异常,请联系管理员!");
        }
    }


//    @RequestMapping("/guarantee_delete")
//    @ResponseBody
//    public AjaxResult delete(Long id) {
//        try {
//            guaranteeService.delete(id);
//            return new AjaxResult(true, "删除成功!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new AjaxResult("更新异常,请联系管理员!");
//        }
//    }
}
