package com.cai.sell.repository;

import com.cai.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 16:54 2018/10/28
 * @Modified By:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
