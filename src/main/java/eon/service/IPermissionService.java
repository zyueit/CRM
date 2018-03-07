package eon.service;

import eon.domain.Permission;
import eon.page.PageResult;
import eon.query.QueryObject;

import java.beans.IntrospectionException;
import java.util.List;

public interface IPermissionService {
    PageResult<Permission> selectByCondition(QueryObject qo);

    void load() throws Exception;

    void delete(Long id);

    Permission queryByExp(String expression);

    List<Permission> queryByEmp(Long id);
}
