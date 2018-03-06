package eon.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("Department")
public class Department {
    private Long id;
    private String sn;
    private String name;
    private Boolean state;//1:正常  0:停用

    private Employee manager;//manager_id
    private Department parent;//parent_id
}
