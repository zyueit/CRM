package eon.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("SystemDictionary")
public class SystemDictionary {
    private Long id;
    private String sn;
    private String name;
    private String intro;//简介
}
