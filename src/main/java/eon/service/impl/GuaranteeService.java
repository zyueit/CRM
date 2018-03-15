package eon.service.impl;

import com.sun.tools.corba.se.idl.ParameterGen;
import eon.domain.DepositOrder;
import eon.domain.Guarantee;
import eon.mapper.GuaranteeMapper;
import eon.page.PageResult;
import eon.query.GuaranteeQueryObject;
import eon.service.IGuaranteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuaranteeService implements IGuaranteeService {

    @Autowired
    private GuaranteeMapper guaranteeMapper;

    @Override
    public void save(Guarantee guarantee) {
        guaranteeMapper.save(guarantee);
    }

    @Override
    public Long queryCount(GuaranteeQueryObject qo) {
        return null;
    }

    @Override
    public PageResult<Guarantee> query(GuaranteeQueryObject qo) {
        int count = guaranteeMapper.queryCount(qo).intValue();
        System.out.println(count);
        if (count == 0) {
            return PageResult.empty();
        }
        return new PageResult<Guarantee>(count, guaranteeMapper.query(qo));
    }

    @Override
    public void delete(Long id) {
        guaranteeMapper.delete(id);
    }
}
