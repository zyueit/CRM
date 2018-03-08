package eon.service;

import eon.domain.SystemDictionary;
import eon.domain.SystemDictionaryItem;

import java.util.List;

public interface ISystemDictionaryService {
    List<SystemDictionary> list();

    List<SystemDictionaryItem> listItem(Long id);

    void saveItem(SystemDictionaryItem item);

    void updateItem(SystemDictionaryItem item);
}
