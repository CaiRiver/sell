package com.cai.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:30 2018/12/11
 * @Modified By:
 */
@Data
public class CartDTO {
    /*商品Id*/
    private String productId;
    /*数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
