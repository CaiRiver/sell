package com.cai.sell.controller;

import com.cai.sell.VO.ProductInfoVO;
import com.cai.sell.VO.ProductVO;
import com.cai.sell.VO.ResultVO;
import com.cai.sell.dataobject.ProductCategory;
import com.cai.sell.dataobject.ProductInfo;
import com.cai.sell.service.CategoryService;
import com.cai.sell.service.ProductService;
import com.cai.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 21:57 2018/10/30
 * @Modified By:
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1、查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2、查询类目(一次性查询)
        List<Integer> categoryTypeList = new ArrayList<>();
       //2-1 传统的for循环从productInfoList循环搜索
//        for (ProductInfo productInfo: productInfoList
//             ) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        // 2-2 精简方法(java8, lambda)
        categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3、数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList
             ) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList
                 ) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
