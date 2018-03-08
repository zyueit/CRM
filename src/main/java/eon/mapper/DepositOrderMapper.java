package eon.mapper;

import eon.domain.DepositOrder;
import eon.query.DepositOrderQueryObject;

import java.util.List;

public interface DepositOrderMapper {
    int save(DepositOrder depositOrder);

    int update(DepositOrder depositOrder);

    int delete(Long id);

    DepositOrder get(long id);

    List<DepositOrder> query(DepositOrderQueryObject qo);

    Long queryCount(DepositOrderQueryObject qo);

    List<DepositOrder> list();

    void approveByDept(Long id);

    /**
     * 审核定金到账
     */
    void approveByFinance(Long id);

    void approve(DepositOrder DepositOrder);
}
