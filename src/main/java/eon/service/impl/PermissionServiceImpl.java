package eon.service.impl;

import eon.domain.Permission;
import eon.mapper.PermissionMapper;
import eon.page.PageResult;
import eon.query.QueryObject;
import eon.service.IPermissionService;
import eon.util.RequiredPermission;
import eon.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.*;

@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageResult<Permission> selectByCondition(QueryObject qo) {
        int totalCount = permissionMapper.selectCountByCondition().intValue();
        if (totalCount == 0) {
            return PageResult.empty();
        }
        List<Permission> result = permissionMapper.selectPageByCondition(qo);
        return new PageResult<>(totalCount, result);
    }

    @Override
    public void load() throws Exception {
        Permission permission = new Permission();
        String expression;
        //查询所有权限
        List<Permission> list = permissionMapper.list();
        //已加载的权限表达式集合
        Set<String> set = new HashSet<>();
        for (Permission p : list) {
            set.add(p.getResource());
        }
        //获取BaseController的所有子类
        Map<String, BaseController> beans = ctx.getBeansOfType(BaseController.class);
        System.out.println(beans);
        //迭代每一个子类
        for (BaseController bean : beans.values()) {
            expression = bean.getClass().getName() + ":all";
            //当数据库没有该权限是插入该权限
            if (!set.contains(expression)) {
                //添加一个包含所有操作权限的权限，如eon.web.controller.PermissionController:all
                RequiredPermission rp = bean.getClass().getAnnotation(RequiredPermission.class);
                if (rp != null) {
                    permission.setName(rp.value());
                    permission.setResource(expression);
                    permissionMapper.save(permission);
                }
            }
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            //获取子类的方法描述器
            MethodDescriptor[] mds = beanInfo.getMethodDescriptors();
            for (MethodDescriptor md : mds) {
                Method method = md.getMethod();
                expression = bean.getClass().getName() + ":" + method.getName();
                //当数据库没有该权限才插入该权限否则跳过
                if (set.contains(expression)) {
                    continue;
                }
                //添加一个访问方法的权限，如eon.web.controller.PermissionController:load
                RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
                if (rp != null) {
                    permission.setName(rp.value());
                    permission.setResource(expression);
                    permissionMapper.save(permission);
                }
            }
        }
    }

    @Override
    public void delete(Long id) {
        permissionMapper.delete(id);
    }

    @Override
    public Permission queryByExp(String expression) {
        return permissionMapper.queryByExp(expression);
    }

    @Override
    public List<Permission> queryByEmp(Long id) {
        return permissionMapper.queryByEmp(id);
    }
}
