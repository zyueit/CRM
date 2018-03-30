package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@Alias("Attence")
public class Attence {
    private Long id;
    private Long IP;
    private Boolean state;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "CMT+8")
    private Date addSignTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "CMT+8")
    private Date signInTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "CMT+8")
    private Date signOutTime;
    private Employee AddSignUser;
    private Employee User;

    @Override
    public String toString() {
        return "Attence{" +
                "id=" + id +
                ", IP=" + IP +
                ", state=" + state +
                ", signInTime=" + signInTime +
                ", signOutTime=" + signOutTime +
                ", AddSignTime=" + addSignTime +
                '}';
    }
}
