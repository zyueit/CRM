package eon.service.impl;

import eon.domain.ContractOrder;
import eon.mapper.ContractOrderMapper;
import eon.page.PageResult;
import eon.query.ContractOrderQueryObejct;
import eon.service.IContractOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractOrderService implements IContractOrderService {
    @Autowired
    private ContractOrderMapper contractOrderMapper;

    @Override
    public void save(ContractOrder contractOrder) {
        contractOrderMapper.save(contractOrder);
    }

    @Override
    public void delete(Long id) {
        contractOrderMapper.delete(id);
    }

    @Override
    public void update(ContractOrder contractOrder) {
        contractOrderMapper.update(contractOrder);
    }

    @Override
    public ContractOrder getOne(Long id) {
        return contractOrderMapper.getOne(id);
    }

    @Override
    public List<ContractOrder> getAll() {
        return contractOrderMapper.getAll();
    }

    @Override
    public PageResult<ContractOrder> query(ContractOrderQueryObejct qo) {
        int count = contractOrderMapper.queryCount(qo).intValue();
        if (count == 0) {
            return PageResult.empty();
        }
        return new PageResult<ContractOrder>(count, contractOrderMapper.query(qo));
    }
}
