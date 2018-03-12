package eon.query;

import eon.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ContractOrderQueryObejct extends QueryObject {
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    /*默认的状态为全部,integer不初始化默认为0*/
    private Integer status = -1;

    public String getKeyword() {
        return !StringUtil.isbank(keyword) ? keyword : null;
    }
}
