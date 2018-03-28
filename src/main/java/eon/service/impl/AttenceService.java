package eon.service.impl;

import eon.domain.Attence;
import eon.domain.PotentialCustomer;
import eon.mapper.AttenceMapper;
import eon.page.PageResult;
import eon.query.AttenceQueryObject;
import eon.service.IAttenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttenceService implements IAttenceService {

    @Autowired
    private AttenceMapper attenceMapper;

    @Override
    public void save(Attence attence) {
        attenceMapper.save(attence);
    }

    @Override
    public Attence get(Long id) {
        return attenceMapper.get(id);
    }

    @Override
    public PageResult<Attence> query(AttenceQueryObject qo) {
        int totalCount = attenceMapper.count(qo).intValue();
        if (totalCount == 0) {
            return PageResult.empty();
        }
        List<Attence> result = attenceMapper.list(qo);
        return new PageResult<Attence>(totalCount, result);
    }

    @Override
    public void delete(Long id) {
        attenceMapper.delete(id);
    }

    @Override
    public void update(Attence attence) {
        attenceMapper.update(attence);
    }
}
