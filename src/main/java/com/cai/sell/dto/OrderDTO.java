package com.cai.sell.dto;

import com.cai.sell.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:36 2018/12/5
 * @Modified By:
 */
@Data
public class OrderDTO {
    private String orderId;
    /*买家名称*/
    private String buyerName;
    /*买家手机*/
    private String buyerPhone;
    /*买家地址*/
    private String buyerAddress;
    /*买家微信openid*/
    private String buyerOpenid;
    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态, 默认为新下单*/
    private Integer orderStatus;
    /*支付状态*/
    private Integer payStatus;
    /*创建时间*/
    private Date createTime;
    /*修改时间*/
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
