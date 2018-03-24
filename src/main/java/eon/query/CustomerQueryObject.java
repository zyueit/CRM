package eon.query;

import eon.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerQueryObject extends QueryObject {
    private String keyword;

    private Integer state = -6;

    private Long uid;

    public String getKeyword() {
        return StringUtil.isbank(keyword) ? null : keyword;
    }
}
