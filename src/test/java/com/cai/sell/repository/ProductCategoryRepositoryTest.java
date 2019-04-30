package com.cai.sell.repository;

import com.cai.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 16:57 2018/10/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        Boolean a  = repository.existsById(1);
        System.out.println( a);
    }

    @Test
    /*用于测试回滚*/
    @Transactional
    public void saveTest() {
//        Optional<ProductCategory> op = repository.findById(1);
//        if (op.isPresent()) {
//            ProductCategory productCategory = op.get();
//            productCategory.setCategoryName("a从v");
//            repository.save(productCategory);
//        }
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生做奥");
        productCategory.setCategoryType(8);
        ProductCategory result =  repository.save(productCategory);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> typeList = new ArrayList<>();
        typeList.add(6);
        typeList.add(7);
        List<ProductCategory> list =  repository.findByCategoryTypeIn(typeList);
        Assert.assertEquals(2, list.size());
    }

}