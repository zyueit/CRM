package eon.service;

import eon.domain.Employee;
import eon.page.PageResult;
import eon.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
    void save(Employee employee);

    PageResult<Employee> selectByCondition(EmployeeQueryObject qo);

    void update(Employee employee);

    void dimission(Long id);

    Employee queryByLogin(String username, String password);

    List<Employee> listForDept();

    void saveLoginIdForEmp(Long id, String loginId);

    String queryLoginId(Long id);

}
