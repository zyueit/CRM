package eon.service;

import eon.domain.Role;
import eon.page.PageResult;
import eon.query.QueryObject;

import java.util.List;

public interface IRoleService {
    PageResult<Role> selectByCondition(QueryObject qo);

    void save(Role role);

    void update(Role role);

    void disable(Long id);

    List<Role> list();
}
