package com.cai.sell.exception;

import com.cai.sell.enums.ResultEnum;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:14 2018/12/6
 * @Modified By:
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
