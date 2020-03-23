package com.cb.separation.service;

import com.cb.separation.annotation.Master;
import com.cb.separation.dao.OrderExpMapper;
import com.cb.separation.model.OrderExp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author cb
 * @title: ProcessOrderService
 * @projectName separation
 * @description: TODO
 * @date 2020/3/23 10:14
 */
@Service
@Slf4j
public class ProcessOrderService {
    public final static short UNPAY = 0;//未支付
    public final static short PAYED = 1;//已支付
    public final static short EXPIRED = -1;//已过期

    @Resource
    private OrderExpMapper orderExpMapper;

    /**
     * 接收前端页面参数，生成订单
     * @param orderNumber 订单个数
     */

    @Autowired
    private TransactionTemplate transactionTemplate;
    public void insertOrders(final int orderNumber){

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Random r = new Random();
                OrderExp orderExp ;
                int num = orderNumber;
                try {
                    for (int i = 0; i < num; i++) {
                        long expireTime = r.nextInt(20) + 5;//订单的超时时长，单位秒5~25
                        orderExp = new OrderExp();
                        String orderNo = "DD00_" + expireTime + "S";//订单的编号
                        orderExp.setOrderNo(orderNo);
                        orderExp.setOrderNote("海王5排" + expireTime + "号,过期时长:" + orderNo);
                        orderExp.setOrderStatus(UNPAY);//订单的状态，目前为未支付
                        orderExpMapper.insertDelayOrder(orderExp, expireTime);
                        log.info("保存订单到DB:" + orderNo);
                        if (i == 3) {
                            throw new RuntimeException("ceshi");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionStatus.setRollbackOnly();
                }

            }
        });

        //查询
        this.findOrders();

    }

    public void findOrders(){
        List<OrderExp> orderList= orderExpMapper.selectUnPayOrders();
        log.info("发现了表中还有["+orderList.size()
                +"]个未到期未支付的订单！");
        for(OrderExp order:orderList)
        {
            log.info(order.toString());
        }
    }

    @Master
    public void findOrdersMaster(){
        List<OrderExp> orderList= orderExpMapper.selectUnPayOrders();
        log.info("发现了表中还有["+orderList.size()
                +"]个未到期未支付的订单！");
        for(OrderExp order:orderList)
        {
            log.info(order.toString());
        }
    }
}
