package eon.service.impl;

import eon.domain.GuaranteeItem;
import eon.mapper.GuaranteeItemMapper;
import eon.service.IGuaranteeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuaranteeItemService implements IGuaranteeItemService {

    @Autowired
    private GuaranteeItemMapper guaranteeItemMapper;

    @Override
    public List<GuaranteeItem> selectByGuaranteeId(Long id) {
        return guaranteeItemMapper.selectByGuaranteeId(id);
    }
}
