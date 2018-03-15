package eon.mapper;

import eon.domain.ContractOrder;
import eon.domain.Guarantee;
import eon.domain.GuaranteeItem;
import eon.query.GuaranteeQueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class ContractOrderMapperTest {
    @Autowired
    private GuaranteeMapper contractOrderMapper;
    @Autowired
    private GuaranteeItemMapper guaranteeItemMapper;

    @Test
    public void save() {
        List<GuaranteeItem> guaranteeItems = guaranteeItemMapper.selectByGuaranteeId((long) 60);
        System.out.println(guaranteeItems);
    }



}