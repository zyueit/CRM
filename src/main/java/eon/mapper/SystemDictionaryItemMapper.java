package eon.mapper;

import eon.domain.SystemDictionaryItem;

import java.util.List;

public interface SystemDictionaryItemMapper {
    List<SystemDictionaryItem> queryByPid(Long pid);

    void save(SystemDictionaryItem item);

    void update(SystemDictionaryItem item);
}
