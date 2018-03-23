package eon.mapper;

import eon.domain.PotentialCustomer;
import eon.domain.SystemDictionaryItem;
import eon.query.PotentialCustomerQueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)@ContextConfiguration("classpath:application.mvc.xml")
public class PotentialCustomerMapperTest {
@Autowired
private PotentialCustomerMapper potentialCustomerMapper;
    @Test
    public void save() {
        PotentialCustomer potentialCustomer = new PotentialCustomer();
        potentialCustomerMapper.save(potentialCustomer);
    }

    @Test
    public void get() {
        potentialCustomerMapper.get(1l);
    }

    @Test
    public void list() {
        PotentialCustomerQueryObject qo = new PotentialCustomerQueryObject();
//        List<PotentialCustomer> list = potentialCustomerMapper.list(qo);
//        System.out.println(list);
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
        PotentialCustomer potentialCustomer = new PotentialCustomer();
        SystemDictionaryItem systemDictionaryItem = new SystemDictionaryItem();
        systemDictionaryItem.setId(1l);
        potentialCustomer.setJob(systemDictionaryItem);
        potentialCustomerMapper.save(potentialCustomer);
    }
}