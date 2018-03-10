package eon.mapper;

import eon.domain.ContractOrder;
import eon.page.PageResult;
import eon.query.ContractOrderQueryObejct;

import java.util.List;

public interface ContractOrderMapper {
    void save(ContractOrder contractOrder);

    void delete(Long id);

    void update(ContractOrder contractOrder);

    ContractOrder getOne(Long id);

    List<ContractOrder> getAll();

    List<ContractOrder> query(ContractOrderQueryObejct qo);

    Long queryCount(ContractOrderQueryObejct qo);


}
