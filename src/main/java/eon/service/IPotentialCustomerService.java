package eon.service;

import eon.domain.PotentialCustomer;
import eon.page.PageResult;
import eon.query.PotentialCustomerQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPotentialCustomerService {

    void save(PotentialCustomer potentialCustomer);

    PotentialCustomer get(Long id);



    void update(PotentialCustomer potentialCustomer);
    void deliver(PotentialCustomer potentialCustomer);


    PageResult<PotentialCustomer> query(PotentialCustomerQueryObject qo);

    void approve(Long id, Long state);
}
