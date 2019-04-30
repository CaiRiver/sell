package com.cai.sell.repository;

import com.cai.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:19 2018/10/29
 * @Modified By:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    /*根据状态查询商品*/
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
