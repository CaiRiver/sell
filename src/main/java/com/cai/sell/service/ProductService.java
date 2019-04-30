package com.cai.sell.service;

import com.cai.sell.dataobject.ProductInfo;
import com.cai.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:44 2018/10/29
 * @Modified By:
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // TODO 加库存
    void increaseStock(List<CartDTO> cartDTOList);
    // TODO 减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
