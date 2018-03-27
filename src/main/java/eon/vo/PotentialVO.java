package eon.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Alias("PotentialVO")
public class PotentialVO {
    private String username;
    private String inputTime;
    private Long customerCount;
    private List<Long> data = new ArrayList<>();

    @Override
    public String toString() {
        return "PotentialVO{" +
                "username='" + username + '\'' +
                ", inputTime=" + inputTime +
                ", customerCount=" + customerCount +
                '}';
    }
}
