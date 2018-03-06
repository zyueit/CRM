package eon.service.impl;

import eon.domain.Department;
import eon.domain.Employee;
import eon.page.PageResult;
import eon.query.EmployeeQueryObject;
import eon.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class EmployeeServiceImplTest {
    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void testSave() {
        Employee employee = new Employee();
        employee.setUsername("明");
        employee.setRealName("mingwen");
        employee.setPassword("1234");
        employee.setTel("15972352024");
        employee.setEmail("12345@qq.com");
        employee.setInputTime(new Date());
        employee.setState(true);
        employee.setAdmin(true);
        Department dept = new Department();
        dept.setId(1L);
        employee.setDept(dept);
        employeeService.save(employee);
    }

    @Test
    public void testPage() {
        PageResult<Employee> result = employeeService.selectByCondition(new EmployeeQueryObject());
        List<Employee> rows = result.getRows();
        for (Employee e : rows) {
            System.out.println(e.getDept());
        }
    }
}
