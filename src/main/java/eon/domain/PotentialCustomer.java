package eon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@Alias("PotentialCustomer")
public class PotentialCustomer {
    private Long id;
    private String name;
    private Integer age;
    private Integer gender; //0男的 1女的
    private String tel;
    private String email;
    private String qq;
    private String wechat;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "CMT+8")
    private Date inputTime;
    private Integer state;  //客户状态-2:流失-1:开发失败0:潜在客户1:正式客户2:资源池客户


    private SystemDictionaryItem job;
    private SystemDictionaryItem salaryLevel;
    private SystemDictionaryItem customerSource;

    private Employee inChargeUser;
    private Employee inputUser;

    private Long uid;



    @Override
    public String toString() {
        return "PotentialCustomer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", inputDate=" + inputTime +
                ", state=" + state +
                '}';
    }
}
