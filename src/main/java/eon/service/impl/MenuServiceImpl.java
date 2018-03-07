package eon.service.impl;

import eon.domain.Menu;
import eon.mapper.MenuMapper;
import eon.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryRootMenus() {
        return menuMapper.queryRootMenu();
    }
}
