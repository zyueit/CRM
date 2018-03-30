package eon.mapper;

import eon.domain.Attence;
import eon.page.PageResult;
import eon.query.AttenceQueryObject;
import eon.service.IAttenceService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class AttenceMapperTest {

    @Autowired
    private IAttenceService attenceMapper;

    @Test
    public void save() {
        PageResult<Attence> query = attenceMapper.query(new AttenceQueryObject());
        System.out.println(query);
//        System.err.println(list);
//        Attence attence = attenceMapper.get(1l);
    }

    @Test
    public void test() throws Exception{
        WritableWorkbook workbook = Workbook.createWorkbook(new File("E:\\CRM_BY_TEAM\\CRM\\src\\main\\webapp\\WEB-INF\\temp", "/"  + "1.xls"));
//        //生成页
        WritableSheet sheet = workbook.createSheet("第一页", 0);
        sheet.addCell(new Label(0, 0, "ddsad"));
        sheet.addCell(new Label(1, 0, "ddsaaaad"));
        workbook.write();
        workbook.close();
    }

}