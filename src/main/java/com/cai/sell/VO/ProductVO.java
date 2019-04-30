package com.cai.sell.VO;

import com.cai.sell.dataobject.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 * 说明 此类VO 分装的参数 只给前端所需要的， 业务上可以保护其他数据
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:09 2018/10/30
 * @Modified By:
 */
@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
