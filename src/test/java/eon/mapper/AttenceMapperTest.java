package eon.mapper;

import eon.domain.Attence;
import eon.page.PageResult;
import eon.query.AttenceQueryObject;
import eon.service.IAttenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

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
}