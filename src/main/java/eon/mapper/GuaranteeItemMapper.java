package eon.mapper;

import eon.domain.Guarantee;
import eon.domain.GuaranteeItem;

import java.util.List;

public interface GuaranteeItemMapper {
    List<GuaranteeItem> selectByGuaranteeId(Long id);

    void update(GuaranteeItem guaranteeItem);

    void save(GuaranteeItem guaranteeItem);

    void delete(Long id);
}
