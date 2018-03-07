package eon.service;

import eon.domain.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> list();

    List<Department> listForEmp();

    void save(Department department);

    void update(Department department);

    void disable(Long id);
}
