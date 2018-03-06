package eon.service.impl;


import eon.domain.Department;
import eon.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class DepartmentServiceImplTest {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void list() {
        List<Department> departments = departmentService.list();
        System.out.println(departments);
    }
}
