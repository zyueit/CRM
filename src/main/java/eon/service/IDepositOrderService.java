package eon.service;

import eon.domain.DepositOrder;
import eon.page.PageResult;
import eon.query.DepositOrderQueryObject;

import java.util.List;

public interface IDepositOrderService {


    int save(DepositOrder depositOrder);

    int update(DepositOrder depositOrder);

    int delete(Long id);

    DepositOrder get(long id);

    PageResult<DepositOrder> query(DepositOrderQueryObject qo);

    List<DepositOrder> list();

    void approveByDept(Long id);

    void approveByFinance(Long id);
    void approve(DepositOrder id);
}
