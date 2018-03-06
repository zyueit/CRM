package eon.mapper;

import eon.domain.Permission;
import eon.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    Long selectCountByCondition();

    List<Permission> selectPageByCondition(QueryObject qo);

    void save(Permission permission);

    List<Permission> list();

    void delete(Long id);

    Permission queryByExp(String expression);

    List<Permission> queryByEmp(Long eid);
}
