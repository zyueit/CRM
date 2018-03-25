package eon.service.impl;

import eon.domain.Employee;
import eon.domain.PotentialCustomer;
import eon.mapper.CustomerMapper;
import eon.page.PageResult;
import eon.query.CustomerQueryObject;
import eon.service.ICustomerService;
import eon.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public void save(PotentialCustomer potentialCustomer) {
        Employee emp = (Employee) UserContext.getRequest().getSession().getAttribute(UserContext.USER_IN_SESSION);
        potentialCustomer.setInputTime(new Date());
        potentialCustomer.setState(0);
        potentialCustomer.setInputUser(emp);
        potentialCustomer.setInChargeUser(emp);
        customerMapper.save(potentialCustomer);
    }

    @Override
    public PotentialCustomer get(Long id) {
        return customerMapper.get(id);
    }


    @Override
    public void update(PotentialCustomer potentialCustomer) {
        customerMapper.update(potentialCustomer);
    }

    @Override
    public void deliver(PotentialCustomer potentialCustomer) {
        customerMapper.deliver(potentialCustomer);
    }

    @Override
    public PageResult<PotentialCustomer> query(CustomerQueryObject qo) {

        int totalCount = customerMapper.count(qo).intValue();
        if (totalCount == 0) {
            return PageResult.empty();
        }
        List<PotentialCustomer> result = customerMapper.list(qo);
        return new PageResult<>(totalCount, result);
    }

    @Override
    public void approve(Long id, Long state) {
        customerMapper.approve(id, state);
    }

}
