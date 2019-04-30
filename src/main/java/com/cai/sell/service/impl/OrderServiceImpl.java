package com.cai.sell.service.impl;

import com.cai.sell.converter.OrderMaster2OrderDTOConverter;
import com.cai.sell.dataobject.OrderDetail;
import com.cai.sell.dataobject.OrderMaster;
import com.cai.sell.dataobject.ProductInfo;
import com.cai.sell.dto.CartDTO;
import com.cai.sell.dto.OrderDTO;
import com.cai.sell.enums.OrderStatusEnum;
import com.cai.sell.enums.PayStatusEnum;
import com.cai.sell.enums.ResultEnum;
import com.cai.sell.exception.SellException;
import com.cai.sell.repository.OrderDetailRepository;
import com.cai.sell.repository.OrderMasterRepository;
import com.cai.sell.service.OrderService;
import com.cai.sell.service.ProductService;
import com.cai.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.nio.channels.SeekableByteChannel;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:43 2018/12/5
 * @Modified By:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String sOrderId = KeyUtil.genUniqueKey();
        BigDecimal bOrderAmount = BigDecimal.ZERO;
//        List<CartDTO> cartDTOList = new ArrayList<>();


        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            //1、查询商品的数量 价格
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2、计算总价
            bOrderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(bOrderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(sOrderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }

        //3、写入订单数据库 ordermaster和 orderdetail
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(sOrderId);
        orderMaster.setOrderAmount(bOrderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4、扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
            new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        //TODO 此处需要使用redis的锁来控制  超卖 现象
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
//        Optional<OrderMaster> optional = orderMasterRepository.findById(orderId);
        Optional<OrderMaster> optional = orderMasterRepository.findById(orderId);
        if (!optional.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderMaster orderMaster = (OrderMaster)optional.get();
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }


    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //1、判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单]订单状态不正确，orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2、修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        if (null == updateOrderMaster) {
            log.error("[订单取消]更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3、返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //4、若已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        /*列表返回 查询不到 反空 也没事*/
        Page<OrderMaster> orderMastersPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMastersPage.getContent());
        return new PageImpl<>(orderDTOList, pageable, orderMastersPage.getTotalElements());
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
