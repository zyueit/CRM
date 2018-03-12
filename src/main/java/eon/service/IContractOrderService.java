package eon.service;

import eon.domain.ContractOrder;
import eon.page.PageResult;
import eon.query.ContractOrderQueryObejct;

import java.util.List;

public interface IContractOrderService {

    void save(ContractOrder contractOrder);

    void delete(Long id);

    void update(ContractOrder contractOrder);

    ContractOrder getOne(Long id);

    List<ContractOrder> getAll();

    PageResult<ContractOrder> query(ContractOrderQueryObejct qo);

    void approve(ContractOrder contractOrder);

    void approveByDept(ContractOrder contractOrder);
}
