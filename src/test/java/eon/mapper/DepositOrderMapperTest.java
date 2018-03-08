package eon.mapper;

import eon.domain.DepositOrder;
import eon.domain.Employee;
import eon.query.DepositOrderQueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class DepositOrderMapperTest {

    @Autowired
    private DepositOrderMapper depositOrderMapper;


    @Test
    public void save() {
        DepositOrder depositOrder = new DepositOrder();
        depositOrder.setSignTime(new Date());
        Employee e = new Employee();
        e.setId(14l);
        depositOrder.setSeller(e);
        depositOrderMapper.save(depositOrder);
    }

    @Test
    public void delete() {
        DepositOrderQueryObject depositOrderQueryObject = new DepositOrderQueryObject();
        depositOrderQueryObject.setPage(2);
        depositOrderQueryObject.setRows(1);
        List<DepositOrder> query = depositOrderMapper.query(depositOrderQueryObject);
        System.out.println(query);
    }

    @Test
    public void get() {
//        DepositOrder depositOrder = depositOrderMapper.get(2l);
//        System.out.println(depositOrder);
//        System.err.println(depositOrder.getSeller().getRealName());}
        DepositOrderQueryObject qo = new DepositOrderQueryObject();
//        qo.setKeyword("吴凯宇");
//        qo.setBeginTime(new Date());
        List<DepositOrder> query = depositOrderMapper.query(qo);
        System.out.println(query);
    }
}