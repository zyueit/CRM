package eon.mapper;

import eon.domain.ContractOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class ContractOrderMapperTest {
    @Autowired
    private ContractOrderMapper contractOrderMapper;

    @Test
    public void save() {
        ContractOrder contractOrder = new ContractOrder();
        contractOrder.setSn("eon");
        contractOrderMapper.save(contractOrder);
    }

}