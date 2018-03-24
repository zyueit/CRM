package eon.web.controller;

import eon.domain.SystemLog;
import eon.page.PageResult;
import eon.query.SystemLogQueryObject;
import eon.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemLogController {
    @Autowired
    private ISystemLogService systemLogService;

    @RequestMapping("/systemLog")
    public String index() {
        return "systemLog";
    }

    @RequestMapping("/systemLog_query")
    @ResponseBody
    public PageResult<SystemLog> query(SystemLogQueryObject qo) {
        return systemLogService.query(qo);
    }
}
