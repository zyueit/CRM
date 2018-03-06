package eon.mapper;

import eon.domain.Employee;
import eon.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    void save(Employee employee);

    Long selectCountByCondition(EmployeeQueryObject qo);

    List<Employee> selectPageByCondition(EmployeeQueryObject qo);

    void update(Employee employee);

    void dimission(Long id);

    Employee loginCheck(@Param("username") String username, @Param("password") String password);

    void handleRelationToSave(@Param("eid") Long eid, @Param("rid") Long rid);

    void handleRelationToDelete(Long eid);

    List<Employee> listForDept();

    void saveLoginIdForEmp(@Param("id") Long id, @Param("loginId") String loginId);

    String queryLoginId(Long id);
}
