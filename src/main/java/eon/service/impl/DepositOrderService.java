package eon.service.impl;

import eon.domain.DepositOrder;
import eon.mapper.DepositOrderMapper;
import eon.page.PageResult;
import eon.query.DepositOrderQueryObject;
import eon.service.IDepositOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositOrderService implements IDepositOrderService {

    @Autowired
    private DepositOrderMapper depositOrderMapper;

    @Override
    public int save(DepositOrder depositOrder) {
        return depositOrderMapper.save(depositOrder);
    }

    @Override
    public int update(DepositOrder depositOrder) {
        return depositOrderMapper.update(depositOrder);
    }

    @Override
    public int delete(Long id) {
        return depositOrderMapper.delete(id);
    }

    @Override
    public DepositOrder get(long id) {
        return depositOrderMapper.get(id);
    }

    @Override
    public PageResult<DepositOrder> query(DepositOrderQueryObject qo) {
        int count = depositOrderMapper.queryCount(qo).intValue();
        System.out.println(count);
        if (count == 0) {
            return PageResult.empty();
        }
        return new PageResult<DepositOrder>(count, depositOrderMapper.query(qo));
    }

    @Override
    public List<DepositOrder> list() {
        return depositOrderMapper.list();
    }

    @Override
    public void approveByDept(Long id) {
        depositOrderMapper.approveByDept(id);
    }

    @Override
    public void approveByFinance(Long id) {
        depositOrderMapper.approveByFinance(id);
    }

    @Override
    public void approve(DepositOrder id) {
        depositOrderMapper.approve(id);
    }

}
