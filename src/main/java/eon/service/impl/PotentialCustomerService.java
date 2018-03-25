package eon.service.impl;

import eon.domain.CustomerTransfer;
import eon.domain.Employee;
import eon.domain.PotentialCustomer;
import eon.mapper.PotentialCustomerMapper;
import eon.page.PageResult;
import eon.query.PotentialCustomerQueryObject;
import eon.service.IPotentialCustomerService;
import eon.util.UserContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.Date;
import java.util.List;

@Service
public class PotentialCustomerService implements IPotentialCustomerService {
    @Autowired
    private PotentialCustomerMapper potentialCustomerMapper;

    @Override
    public void save(PotentialCustomer potentialCustomer) {
        Employee emp = (Employee) UserContext.getRequest().getSession().getAttribute(UserContext.USER_IN_SESSION);
        potentialCustomer.setInputTime(new Date());
        potentialCustomer.setState(0);
        potentialCustomer.setInputUser(emp);
        potentialCustomer.setInChargeUser(emp);
        potentialCustomerMapper.save(potentialCustomer);
    }

    @Override
    public PotentialCustomer get(Long id) {
        return potentialCustomerMapper.get(id);
    }


    @Override
    public void update(PotentialCustomer potentialCustomer) {
        potentialCustomerMapper.update(potentialCustomer);
    }

    @Override
    public void deliver(PotentialCustomer potentialCustomer) {
        potentialCustomerMapper.deliver(potentialCustomer);
    }

    @Override
    public PageResult<PotentialCustomer> query(PotentialCustomerQueryObject qo) {
        int totalCount = potentialCustomerMapper.count(qo).intValue();
        if (totalCount == 0) {
            return PageResult.empty();
        }
        List<PotentialCustomer> result = potentialCustomerMapper.list(qo);
        return new PageResult<>(totalCount, result);
    }

    @Override
    public void approve(Long id, Long state) {
        potentialCustomerMapper.approve(id, state);
    }

}
