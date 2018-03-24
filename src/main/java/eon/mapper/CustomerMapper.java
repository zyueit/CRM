package eon.mapper;

import eon.domain.PotentialCustomer;
import eon.query.CustomerQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    void save(PotentialCustomer potentialCustomer);

    PotentialCustomer get(Long id);

    List<PotentialCustomer> list(CustomerQueryObject qo);

    Long count(CustomerQueryObject qo);


    void update(PotentialCustomer potentialCustomer);

    void deliver(PotentialCustomer potentialCustomer);

    void approve(@Param("id") Long id, @Param("state") Long state);
}
