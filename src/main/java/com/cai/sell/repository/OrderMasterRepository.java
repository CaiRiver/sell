package com.cai.sell.repository;

import com.cai.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:38 2018/11/20
 * @Modified By:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    /*根据openid 分页查询买家订单*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);


}
