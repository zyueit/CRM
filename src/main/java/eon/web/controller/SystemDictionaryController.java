package eon.web.controller;

import eon.domain.SystemDictionary;
import eon.domain.SystemDictionaryItem;
import eon.page.AjaxResult;
import eon.service.ISystemDictionaryService;
import eon.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredPermission("数据字典管理")
@Controller
public class SystemDictionaryController extends BaseController {
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequiredPermission("数据字典列表")
    @RequestMapping("/systemDictionary")
    public String index() {
        return "systemDictionary";
    }

    @RequestMapping("/systemDictionary_list")
    @ResponseBody
    public List<SystemDictionary> list() {
        return systemDictionaryService.list();
    }

    @RequestMapping("/systemDictionary_listItem")
    @ResponseBody
    public List<SystemDictionaryItem> listItem(Long id) {
        return systemDictionaryService.listItem(id);
    }

    @RequiredPermission("数据字典明细新增")
    @RequestMapping("/systemDictionary_saveItem")
    @ResponseBody
    public AjaxResult saveItem(SystemDictionaryItem item) {
        AjaxResult result;
        try {
            systemDictionaryService.saveItem(item);
            result = new AjaxResult(true, "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员！");
        }
        return result;
    }

    @RequiredPermission("数据字典明细编辑")
    @RequestMapping("/systemDictionary_updateItem")
    @ResponseBody
    public AjaxResult updateItem(SystemDictionaryItem item) {
        AjaxResult result;
        try {
            systemDictionaryService.updateItem(item);
            result = new AjaxResult(true, "更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新异常，请联系管理员！");
        }
        return result;
    }
}
