package eon.service.impl;

import eon.mapper.CustomerTransferMapper;
import eon.service.ICustomerTransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.mvc.xml")
public class CustomerTransferServiceTest {

    @Autowired
    private ICustomerTransferService customerTransferMapper;

    @Test
    public void save() {
        System.out.println(customerTransferMapper);
    }
}