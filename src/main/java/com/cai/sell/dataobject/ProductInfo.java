package com.cai.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:04 2018/10/29
 * @Modified By:
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    @Id
    /*产生随机字符，无需自增注解*/
    private String productId;
    /*商品名称*/
    private String productName;
    /*商品单价*/
    private BigDecimal productPrice;
    /*商品库存*/
    private Integer productStock;
    /*商品描述*/
    private String productDescription;
    /*商品图片,地址连接*/
    private String productIcon;
    /*商品状态,0-正常1-下架*/
    private Integer productStatus;
    /*商品类型*/
    private Integer categoryType;
}
