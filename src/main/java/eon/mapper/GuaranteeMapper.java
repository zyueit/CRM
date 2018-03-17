package eon.mapper;

import eon.domain.Guarantee;
import eon.query.GuaranteeQueryObject;

import java.util.List;

public interface GuaranteeMapper {
    void save(Guarantee guarantee);

    Long queryCount(GuaranteeQueryObject qo);

    List<Guarantee> query(GuaranteeQueryObject qo);

    void delete(Long id);

    void update(Guarantee guarantee);;
}
