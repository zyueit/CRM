package eon.mapper;

import eon.domain.CustomerTransfer;
import eon.query.QueryObject;

import java.util.List;

public interface CustomerTransferMapper {
    void save(CustomerTransfer customerTransfer);

    List<CustomerTransfer> query(QueryObject qo);

    Long count(QueryObject qo);
}
