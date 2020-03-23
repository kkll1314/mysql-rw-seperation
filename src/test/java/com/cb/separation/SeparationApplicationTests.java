package com.cb.separation;

import com.cb.separation.service.ProcessOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = SeparationApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SeparationApplicationTests {

    @Autowired
    private ProcessOrderService processOrderService;


    @Test
    public void insertOrders() {
        processOrderService.insertOrders(5);
    }
    @Test
    public void queryOrders() {
        processOrderService.findOrders();
        processOrderService.findOrdersMaster();
    }

}
