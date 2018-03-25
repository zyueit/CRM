package eon.service;

import eon.domain.CustomerTransfer;
import eon.page.PageResult;
import eon.query.QueryObject;

public interface ICustomerTransferService {
    void save(CustomerTransfer customerTransfer);

    PageResult<CustomerTransfer> query(QueryObject qo);
}
