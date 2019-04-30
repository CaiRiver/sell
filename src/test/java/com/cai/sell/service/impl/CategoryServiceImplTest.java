package com.cai.sell.service.impl;

import com.cai.sell.dataobject.ProductCategory;
import com.cai.sell.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 21:36 2018/10/29
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryServiceImpl.findOne(1);
        Assert.assertEquals(new Integer(3),productCategory.getCategoryType());
    }

    @Test
    public void findAll() {
        List<ProductCategory> categoryServiceImplAll = categoryServiceImpl.findAll();
        Assert.assertEquals(new Integer(2), new Integer(categoryServiceImplAll.size()));
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> byCategoryTypeIn = categoryServiceImpl.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(5);
        productCategory.setCategoryName("女时装创");
        ProductCategory save = categoryServiceImpl.save(productCategory);
        Assert.assertNotNull(save);
    }
}