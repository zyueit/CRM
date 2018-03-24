package eon.service;

import eon.domain.PotentialCustomer;
import eon.page.PageResult;
import eon.query.CustomerQueryObject;

public interface ICustomerService {

    void save(PotentialCustomer potentialCustomer);

    PotentialCustomer get(Long id);



    void update(PotentialCustomer potentialCustomer);
    void deliver(PotentialCustomer potentialCustomer);


    PageResult<PotentialCustomer> query(CustomerQueryObject qo);

    void approve(Long id, Long state);
}
