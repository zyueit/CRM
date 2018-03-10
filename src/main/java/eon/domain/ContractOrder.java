package eon.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("ContractOrder")@Getter@Setter
public class ContractOrder {
    private Long id;
    private String sn;//uuid
    private Date signTime;
    private BigDecimal sum;
    private BigDecimal money;
    private Date payTime;
    private String intro;
    private Integer status;//0:初始录入.1:审核通过,2:审核拒绝
    private String file;
    private Date modifyTime;

    /*合同客户站位符*/

    private Employee seller;
    private Employee modifyUser;
}
