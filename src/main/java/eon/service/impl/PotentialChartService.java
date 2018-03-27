package eon.service.impl;

import eon.mapper.PotentialChartMapper;
import eon.query.PotentialChartQueryObejct;
import eon.service.IPotentialChartService;
import eon.vo.PotentialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotentialChartService implements IPotentialChartService {
    @Autowired
    private PotentialChartMapper potentialChartMapper;

    @Override
    public List<PotentialVO> query(PotentialChartQueryObejct qo) {
        return potentialChartMapper.query(qo);
    }

}
