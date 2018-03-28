package eon.service;

import eon.domain.Attence;
import eon.page.PageResult;
import eon.query.AttenceQueryObject;

public interface IAttenceService {
    void save(Attence attence);

    Attence get(Long id);

    PageResult<Attence> query(AttenceQueryObject qo);

    void delete(Long id);

    void update(Attence attence);
}
