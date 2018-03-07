package eon.mapper;

import eon.domain.Role;
import eon.page.PageResult;
import eon.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    Long selectCountByCondition();

    List<Role> selectPageByCondition(QueryObject qo);

    void handleRelationToSave(@Param("rid") Long rid, @Param("pid") Long pid);

    void save(Role role);

    void update(Role role);

    void handleRelationToDelete(Long rid);

    void disable(Long rid);

    void handleRelationToDeleteForEmp(Long rid);

    List<Role> list();
}
