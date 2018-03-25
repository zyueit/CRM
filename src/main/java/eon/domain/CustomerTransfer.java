package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("CustomerTransfer")
@Getter
@Setter
public class CustomerTransfer {
    private Long id;
    private PotentialCustomer customer;
    private Employee transUser;
    private Employee oldSeller;
    private Employee newSeller;
    @JsonFormat(pattern = "yyyy-MM-dd HH:dd",timezone = "CMT+8")
    private Date transTime;
    private String transReason;
}
