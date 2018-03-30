package eon.web.controller;

import com.alibaba.fastjson.JSON;
import eon.domain.Attence;
import eon.domain.Employee;
import eon.page.PageResult;
import eon.query.AttenceQueryObject;
import eon.service.IAttenceService;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.WritableWorkbookImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

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
        return query;
    }


    @RequestMapping("/attence_down")
    public void download(HttpServletRequest request, HttpServletResponse response, String data) throws Exception {
        attenceService.down(request, response, data);
    }
}
