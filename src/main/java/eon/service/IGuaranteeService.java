package eon.service;

import eon.domain.Guarantee;
import eon.page.PageResult;
import eon.query.GuaranteeQueryObject;

import java.util.List;

public interface IGuaranteeService {
    void save(Guarantee guarantee);

    Long queryCount(GuaranteeQueryObject qo);

    PageResult<Guarantee> query(GuaranteeQueryObject qo);

    void delete(Long id);

}
