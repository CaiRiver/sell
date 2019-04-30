package com.cai.sell.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cai.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:44 2018/12/5
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    private final String OPENID = "asdfasdfasdf";
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<OrderMaster> byBuyerOpenid = repository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(0,byBuyerOpenid.getTotalElements());
        System.out.println(byBuyerOpenid.getTotalElements());
    }

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234566");
        orderMaster.setBuyerName("caiai");
        orderMaster.setBuyerPhone("21651651");
        orderMaster.setBuyerAddress("nanjing");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.3));
        OrderMaster orderMaster1 = repository.save(orderMaster);
        Assert.assertNotNull(orderMaster1);
    }
}