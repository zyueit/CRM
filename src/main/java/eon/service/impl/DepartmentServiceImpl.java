package eon.service.impl;

import eon.domain.Department;
import eon.mapper.DepartmentMapper;
import eon.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> listForEmp() {
        return departmentMapper.listForEmp();
    }

    @Override
    public void save(Department department) {
        departmentMapper.save(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public void disable(Long id) {
        departmentMapper.disable(id);
        //处理部门里员工--删除关系
        departmentMapper.handleRelationToDelete(id);
    }

    @Override
    public List<Department> list() {
        return departmentMapper.list();
    }
}