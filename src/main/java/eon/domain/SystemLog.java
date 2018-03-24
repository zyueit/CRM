package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Setter
@Getter
@Alias("SystemLog")
public class SystemLog {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date opTime;
    private String opIp;
    private String function;
    private String params;
    private Employee opUser;//opUser_id
}
