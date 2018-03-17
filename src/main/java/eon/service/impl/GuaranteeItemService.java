package eon.service.impl;

import eon.domain.GuaranteeItem;
import eon.mapper.GuaranteeItemMapper;
import eon.service.IGuaranteeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GuaranteeItemService implements IGuaranteeItemService {

    @Autowired
    private GuaranteeItemMapper guaranteeItemMapper;

    @Override
    public List<GuaranteeItem> selectByGuaranteeId(Long id) {
        return guaranteeItemMapper.selectByGuaranteeId(id);
    }

    @Override
    public void update(GuaranteeItem guaranteeItem) {
        guaranteeItemMapper.update(guaranteeItem);
    }

    @Override
    public void save(GuaranteeItem guaranteeItem) {
        guaranteeItem.setGuaranteeTime(new Date());
        guaranteeItemMapper.save(guaranteeItem);
    }

    @Override
    public void delete(Long id) {
        guaranteeItemMapper.delete(id);
    }
}
