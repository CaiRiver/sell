package com.cai.sell.service.impl;

import com.cai.sell.dataobject.ProductCategory;
import com.cai.sell.repository.ProductCategoryRepository;
import com.cai.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 21:29 2018/10/29
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        Optional<ProductCategory> byId = productCategoryRepository.findById(categoryId);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
        return productCategoryRepository.findByCategoryTypeIn(categoryType);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
