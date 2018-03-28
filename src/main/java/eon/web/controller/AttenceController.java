package eon.web.controller;

import eon.domain.Attence;
import eon.page.PageResult;
import eon.query.AttenceQueryObject;
import eon.service.IAttenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AttenceController {
    @Autowired
    private IAttenceService attenceService;

    @RequestMapping("/attence")
    public String index() {
        return "attence";
    }

    @RequestMapping("/attence_query")
    @ResponseBody
    public PageResult<Attence> query(AttenceQueryObject qo) {
        PageResult<Attence> query = attenceService.query(qo);
        System.err.println(query.getRows());
        return query;
    }
}
