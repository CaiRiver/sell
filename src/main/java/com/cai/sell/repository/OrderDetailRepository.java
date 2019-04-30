package com.cai.sell.repository;

import com.cai.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:43 2018/11/20
 * @Modified By:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    /*根据orderid查询订单详情*/
    List<OrderDetail> findByOrderId(String orderId);
}
