package com.cai.sell.service.impl;

import com.cai.sell.dataobject.OrderDetail;
import com.cai.sell.dto.OrderDTO;
import com.cai.sell.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:01 2018/12/11
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    public static final  String BUYER_OPENID = "313213";

    public static  final String ORDER_ID = "1554901250947181019";
    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("caicia");
        orderDTO.setBuyerAddress("wuxi");
        orderDTO.setBuyerPhone("3541646516");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        
        //购物车
        ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<>();
        orderDTO.setOrderDetailList(orderDetailArrayList);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("23");
        orderDetail1.setProductQuantity(3);
        orderDetailArrayList.add(orderDetail1);

        OrderDTO orderDTO1 = orderService.create(orderDTO);
        log.info("创建订单resutl ={}", orderDTO1);
        Assert.assertNotNull(orderDTO1);
    }

    @Test
    public void findOne() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",one);
        Assert.assertEquals(ORDER_ID,one.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest of = PageRequest.of(0, 5);
        orderService.findList(BUYER_OPENID, of);
//        Assert.assertNotEquals(0, );
    }

    @Test
    public void cancel() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.cancel(one);
        log.info("【取消后的订单】result={}",cancel);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}