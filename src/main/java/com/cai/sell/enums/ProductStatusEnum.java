package com.cai.sell.enums;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:53 2018/10/29
 * @Modified By:
 */
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架")
    ;
    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
