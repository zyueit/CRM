package eon.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Alias("Menu")
public class Menu {
    private Long id;
    private String text;//菜单名称
    private String state;//open或close
    private Boolean checked;
    private String attributes;
    private String resource;
    private List<Menu> children = new ArrayList<>();//parent_id

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                ", checked=" + checked +
                ", attributes='" + attributes + '\'' +
                ", resource='" + resource + '\'' +
                ", children=" + children +
                '}';
    }
}
