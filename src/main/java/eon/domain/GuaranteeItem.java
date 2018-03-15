package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("GuaranteeItem")
@Getter
@Setter
public class GuaranteeItem {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date guaranteeTime;

//    private String content;   客户对象

    private Boolean status;
    private Guarantee guarantee;//保修单对象
}
