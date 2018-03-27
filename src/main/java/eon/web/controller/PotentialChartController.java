package eon.web.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import eon.domain.Employee;
import eon.query.PotentialChartQueryObejct;
import eon.service.IPotentialChartService;
import eon.util.ChartUtil;
import eon.util.UserContext;
import eon.vo.PotentialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PotentialChartController {
    @Autowired
    private IPotentialChartService potentialChartService;

    @RequestMapping("/potentialChart")
    public String index() {
        return "potentialChart";
    }

    @RequestMapping("/potentialChart_query")
    @ResponseBody
    public List<PotentialVO> query(PotentialChartQueryObejct qo, HttpSession session) {
        Employee user = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        qo.setUid(user.getId());
        return potentialChartService.query(qo);
    }

    @RequestMapping("/potentialChart_parse")
    @ResponseBody
    public Map<String, Object> month(HttpServletRequest request, String rows, HttpServletResponse response) throws Exception {
        ObjectMapper ow = new ObjectMapper();
        List<PotentialVO> list = JSON.parseArray(rows, PotentialVO.class);
        Map<String, Object> dat = ChartUtil.dat(request, list);
        return dat;
    }

}
