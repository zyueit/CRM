package eon.mapper;

import eon.query.PotentialChartQueryObejct;
import eon.vo.PotentialVO;

import java.util.List;

public interface PotentialChartMapper {
    List<PotentialVO> query(PotentialChartQueryObejct qo);
}
