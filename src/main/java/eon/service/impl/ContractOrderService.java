package eon.service.impl;

import eon.domain.ContractOrder;
import eon.domain.Guarantee;
import eon.mapper.ContractOrderMapper;
import eon.mapper.GuaranteeMapper;
import eon.page.PageResult;
import eon.query.ContractOrderQueryObejct;
import eon.service.IContractOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContractOrderService implements IContractOrderService {
    @Autowired
    private ContractOrderMapper contractOrderMapper;
    @Autowired
    private GuaranteeMapper guaranteeMapper;

    @Override
    public void save(ContractOrder contractOrder) {
        contractOrderMapper.save(contractOrder);
    }

    @Override
    public void delete(Long id) {
        //先查询此订单是否已经有附件
        ContractOrder search = contractOrderMapper.getOne(id);
        //有附件就把原来的附件删除
        if (search.getFile() != null && !"".equals(search.getFile().trim())) {
            File oldFile = new File(search.getFile());
            oldFile.delete();
        }
        guaranteeMapper.delete(id);
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

    @Override
    public void approve(ContractOrder contractOrder) {
        contractOrderMapper.approve(contractOrder);
    }

    @Override
    public void approveByDept(ContractOrder contractOrder) {
        Guarantee guarantee = new Guarantee();
        guarantee.setId(contractOrder.getId());
        guarantee.setSn(UUID.randomUUID().toString().substring(0, 13));
        guarantee.setDueTime(getTime());
        guaranteeMapper.save(guarantee);
        contractOrderMapper.approveByDept(contractOrder);
    }

    public Date getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date time = calendar.getTime();
        return time;
    }

}
