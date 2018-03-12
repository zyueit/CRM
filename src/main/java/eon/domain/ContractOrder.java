package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("ContractOrder")
@Getter
@Setter
public class ContractOrder {
    private Long id;
    private String sn;//uuid
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signTime;
    private BigDecimal sum;
    private BigDecimal money;
    private Date payTime;
    private String intro;
    private Integer status;//0:初始录入.1:未通过部门审核,2:审核拒绝3所有审核已通过,出合同
    private String file;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /*合同客户站位符*/

    private Employee seller;
    private Employee modifyUser;

}
