package eon.service.impl;

import eon.domain.SystemDictionary;
import eon.domain.SystemDictionaryItem;
import eon.mapper.SystemDictionaryItemMapper;
import eon.mapper.SystemDictionaryMapper;
import eon.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemDictionaryService")
public class SystemDictionaryImpl implements ISystemDictionaryService {
    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;
    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    @Override
    public List<SystemDictionary> list() {
        return systemDictionaryMapper.list();
    }

    @Override
    public List<SystemDictionaryItem> listItem(Long id) {
        return systemDictionaryItemMapper.queryByPid(id);
    }

    @Override
    public void saveItem(SystemDictionaryItem item) {
        systemDictionaryItemMapper.save(item);
    }

    @Override
    public void updateItem(SystemDictionaryItem item) {
        systemDictionaryItemMapper.update(item);
    }
}
