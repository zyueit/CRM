package eon.mapper;

import eon.domain.GuaranteeItem;

import java.util.List;

public interface GuaranteeItemMapper {
    List<GuaranteeItem> selectByGuaranteeId(Long id);
}
