package eon.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("SystemDictionaryItem")
public class SystemDictionaryItem {
    private Long id;
    private String name;
    private String intro;
    private SystemDictionary parent;
}
