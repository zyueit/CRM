package eon.service.impl;

import eon.domain.Employee;
import eon.domain.Role;
import eon.mapper.EmployeeMapper;
import eon.page.PageResult;
import eon.query.EmployeeQueryObject;
import eon.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee queryByLogin(String username, String password) {
        return employeeMapper.loginCheck(username, password);
    }

    @Override
    public List<Employee> listForDept() {
        return employeeMapper.listForDept();
    }

    @Override
    public void saveLoginIdForEmp(Long id, String loginId) {
        employeeMapper.saveLoginIdForEmp(id, loginId);
    }

    @Override
    public String queryLoginId(Long id) {
        return employeeMapper.queryLoginId(id);
    }

    @Override
    public void save(Employee employee) {
        employeeMapper.save(employee);
        //处理和角色的关系
        for (Role role : employee.getRoles()) {
            employeeMapper.handleRelationToSave(employee.getId(), role.getId());
        }
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.update(employee);
        //先删除原先的角色
        employeeMapper.handleRelationToDelete(employee.getId());
        //再插入新的角色
        for (Role role : employee.getRoles()) {
            employeeMapper.handleRelationToSave(employee.getId(), role.getId());
        }
    }

    @Override
    public void dimission(Long id) {
        employeeMapper.dimission(id);
    }

    @Override
    public PageResult<Employee> selectByCondition(EmployeeQueryObject qo) {
        int totalCount = employeeMapper.selectCountByCondition(qo).intValue();
        if (totalCount == 0) {
            return PageResult.empty();
        }
        List<Employee> result = employeeMapper.selectPageByCondition(qo);
        return new PageResult<>(totalCount, result);
    }
}
