package eon.service;

import eon.domain.GuaranteeItem;

import java.util.List;

public interface IGuaranteeItemService {
    List<GuaranteeItem> selectByGuaranteeId(Long id);
}
