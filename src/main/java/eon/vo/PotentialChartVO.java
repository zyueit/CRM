package eon.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PotentialChartVO {
    private String name;
    private List<Long> data = new ArrayList<>();

    public PotentialChartVO(String name, List data) {
        this.name = name;
        this.data = data;
    }
}
