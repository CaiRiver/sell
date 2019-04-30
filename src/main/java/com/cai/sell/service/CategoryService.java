package com.cai.sell.service;

import com.cai.sell.dataobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 类目服务
 * @Author:caicai
 * @Description:
 * @Date: Created in 21:24 2018/10/29
 * @Modified By:
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
    ProductCategory save(ProductCategory productCategory);
}
