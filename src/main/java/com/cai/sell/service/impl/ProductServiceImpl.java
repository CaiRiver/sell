package com.cai.sell.service.impl;

import com.cai.sell.dataobject.ProductInfo;
import com.cai.sell.dto.CartDTO;
import com.cai.sell.enums.ProductStatusEnum;
import com.cai.sell.enums.ResultEnum;
import com.cai.sell.exception.SellException;
import com.cai.sell.repository.ProductInfoRepository;
import com.cai.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:49 2018/10/29
 * @Modified By:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> one = productInfoRepository.findById(productId);
        if (one.isPresent()) {
            ProductInfo productInfo = one.get();
            return productInfoRepository.save(productInfo);
        }
        return null;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            if (productInfoOptional.isPresent()) {
                ProductInfo productInfo = productInfoOptional.get();
                Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
                productInfo.setProductStock(result);
                productInfoRepository.save(productInfo);
            } else {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            if (productInfoOptional.isPresent()) {
                ProductInfo productInfo = productInfoOptional.get();
                Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
                if (result < 0) {
                    throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
                }
                productInfo.setProductStock(result);
                productInfoRepository.save(productInfo);
            } else {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }
    }
}
