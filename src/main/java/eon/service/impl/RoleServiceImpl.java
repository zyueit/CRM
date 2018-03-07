package eon.service.impl;

import eon.domain.Permission;
import eon.domain.Role;
import eon.mapper.RoleMapper;
import eon.page.PageResult;
import eon.query.QueryObject;
import eon.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageResult<Role> selectByCondition(QueryObject qo) {
        int totalCount = roleMapper.selectCountByCondition().intValue();
        if (totalCount == 0) {
            return PageResult.empty();
        }
        List<Role> result = roleMapper.selectPageByCondition(qo);
        return new PageResult<>(totalCount, result);
    }

    @Override
    public void save(Role role) {
        //保存角色
        roleMapper.save(role);
        //处理角色和权限的关系
        for (Permission permission : role.getPermissions()) {
            roleMapper.handleRelationToSave(role.getId(), permission.getId());
        }
    }

    @Override
    public void update(Role role) {
        //更新角色
        roleMapper.update(role);
        //先删除该角色之前的权限
        roleMapper.handleRelationToDelete(role.getId());
        //处理角色和新分配权限的关系
        for (Permission permission : role.getPermissions()) {
            roleMapper.handleRelationToSave(role.getId(), permission.getId());
        }
    }

    @Override
    public void disable(Long id) {
        roleMapper.disable(id);
        roleMapper.handleRelationToDeleteForEmp(id);
    }

    @Override
    public List<Role> list() {
        return roleMapper.list();
    }
}
