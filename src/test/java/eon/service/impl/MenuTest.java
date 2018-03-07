package eon.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import eon.domain.Menu;
import eon.mapper.MenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class MenuTest {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testQuery() throws Exception {
        List<Menu> menus = menuMapper.queryRootMenu();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(menus);
        System.out.println(s);
    }
}
