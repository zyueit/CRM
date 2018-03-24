package eon.mapper;

import eon.domain.SystemLog;
import eon.query.SystemLogQueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class SystemLogMapperTest {
    @Autowired
    private SystemLogMapper systemLogMapper;

    @Test
    public void query() {
        SystemLogQueryObject qo = new SystemLogQueryObject();
        List<SystemLog> query = systemLogMapper.query(qo);
        for (int i = 0; i < query.size(); i++) {
            System.out.println(query.get(i).getOpUser());
        }
    }

    @Test
    public void count() {
        System.out.println(systemLogMapper.count());
    }
}