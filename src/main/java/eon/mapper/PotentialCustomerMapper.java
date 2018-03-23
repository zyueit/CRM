package eon.mapper;

import eon.domain.PotentialCustomer;
import eon.query.PotentialCustomerQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PotentialCustomerMapper {

    void save(PotentialCustomer potentialCustomer);

    PotentialCustomer get(Long id);

    List<PotentialCustomer> list(PotentialCustomerQueryObject qo);

    Long count(PotentialCustomerQueryObject qo);


    void update(PotentialCustomer potentialCustomer);

    void deliver(PotentialCustomer potentialCustomer);

    void approve(@Param("id") Long id, @Param("state") Long state);
}
