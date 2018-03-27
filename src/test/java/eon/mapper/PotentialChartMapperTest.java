package eon.mapper;

import eon.query.PotentialChartQueryObejct;
import eon.vo.PotentialVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class PotentialChartMapperTest {

    @Autowired
    private PotentialChartMapper potentialChartMapper;

    @Test
    public void query() {
        PotentialChartQueryObejct qo = new PotentialChartQueryObejct();
        qo.setGroupBy("月份");
        List<PotentialVO> query = potentialChartMapper.query(qo);
    }
}