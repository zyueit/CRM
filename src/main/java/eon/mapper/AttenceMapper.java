package eon.mapper;

import eon.domain.Attence;
import eon.query.AttenceQueryObject;

import java.util.List;

public interface AttenceMapper {
    void save(Attence attence);

    Attence get(Long id);

    List<Attence> list(AttenceQueryObject qo);

    void delete(Long id);

    void update(Attence attence);

    Long count(AttenceQueryObject qo);
}
