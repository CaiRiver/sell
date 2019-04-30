package com.cai.sell.repository;

import com.cai.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:21 2018/12/5
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("213235646");
        orderDetail.setOrderId("54651246");
        orderDetail.setProductId("23165");
        orderDetail.setProductIcon("23");
        orderDetail.setProductName("pidanzhssou");
        orderDetail.setProductPrice(new BigDecimal(20));
        orderDetail.setProductQuantity(156);
        OrderDetail orderDetail1 = repository.save(orderDetail);
        Assert.assertNotNull(orderDetail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> byOrderId = repository.findByOrderId("54651246");
        Assert.assertNotEquals(0,byOrderId.size());

    }
}