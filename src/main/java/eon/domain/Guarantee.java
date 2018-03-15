package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Alias("Guarantee")
@Getter
@Setter
public class Guarantee {
    private Long id;
    private String sn;
    private String productName;
    /*保修客户的站位符*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dueTime;
    private String remark;

    private List<GuaranteeItem> guaranteeItems = new ArrayList<>();
}
