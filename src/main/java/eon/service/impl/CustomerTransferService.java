package eon.service.impl;

import eon.domain.ContractOrder;
import eon.domain.CustomerTransfer;
import eon.mapper.CustomerTransferMapper;
import eon.page.PageResult;
import eon.query.QueryObject;
import eon.service.ICustomerTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerTransferService implements ICustomerTransferService {

    @Autowired
    private CustomerTransferMapper customerTransferMapper;

    @Override
    public void save(CustomerTransfer customerTransfer) {
        customerTransferMapper.save(customerTransfer);
    }

    @Override
    public PageResult<CustomerTransfer> query(QueryObject qo) {
        int count = customerTransferMapper.count(qo).intValue();
        if (count == 0) {
            return PageResult.empty();
        }
        return new PageResult<CustomerTransfer>(count, customerTransferMapper.query(qo));
    }
}
