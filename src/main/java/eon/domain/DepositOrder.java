package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("DepositOrder")
@Getter
@Setter
public class DepositOrder {
    private static final Integer ONE = 1;//已审核,部门主管未审核
    private static final Integer TWO = 2;//已退款
    private static final Integer ZERO = 0;//初始录入
    private static final Integer FOUR = 3;//已出合同
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date signTime;
    private BigDecimal totalSum;
    private BigDecimal sum;
    private String intro;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date modifyTime;
    private Integer status;// 0为已审核,1为未审核
    private String File;
    private Date createTime;
    //定金状态
    private Integer statusOfFinance; // 0 无需显示 1 等待定金 2 定金已到账
    //客户的站位*******************************
    private Employee seller;
    private Employee modifyUser;
    @Override
    public String toString() {
        return "DepositOrder{" +
                "id=" + id +
                ", signTime=" + signTime +
                ", totalSum=" + totalSum +
                ", sum=" + sum +
                ", intro='" + intro + '\'' +
                ", modifyTime=" + modifyTime +
                ", status=" + status +
                '}';
    }
}
