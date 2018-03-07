package eon.mapper;

import eon.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> list();

    List<Department> listForEmp();

    void save(Department department);

    void update(Department department);

    void disable(Long id);

    void handleRelationToDelete(Long id);
}
