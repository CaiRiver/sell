package com.cai.sell.service.impl;

import com.cai.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:00 2018/10/29
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Test
    public void findOne() {
        ProductInfo one = productServiceImpl.findOne("20181029224031");
        Assert.assertEquals("20181029224031", one.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productServiceImpl.findUpAll();
        Assert.assertNotEquals(0, upAll.size());
    }

    @Test
    public void findAll() {
        /*page第几页， size有几条*/
        PageRequest pageRequest = PageRequest.of(1, 2);
        Page<ProductInfo> all = productServiceImpl.findAll(pageRequest);
//        System.out.println(all.getTotalElements());
        Assert.assertNotEquals(0,all.getTotalElements());
    }


    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(1);
        productInfo.setProductId( new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        productInfo.setProductPrice(new BigDecimal(15));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("皮皮虾很好吃");
        productInfo.setProductStatus(0);
        productInfo.setProductName("皮皮虾粥");
        ProductInfo save = productServiceImpl.save(productInfo);
        Assert.assertNotNull(save);
    }
}