package eon.service;

import eon.domain.Guarantee;
import eon.domain.GuaranteeItem;

import java.util.List;

public interface IGuaranteeItemService {
    List<GuaranteeItem> selectByGuaranteeId(Long id);

    void update(GuaranteeItem guaranteeItem);
    void save(GuaranteeItem guaranteeItem);
    void delete(Long id);
}
