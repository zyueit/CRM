package eon.service;

import eon.query.PotentialChartQueryObejct;
import eon.vo.PotentialVO;

import java.util.List;

public interface IPotentialChartService {
    List<PotentialVO> query(PotentialChartQueryObejct qo);
}
