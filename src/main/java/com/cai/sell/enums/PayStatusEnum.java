package com.cai.sell.enums;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 23:18 2018/11/20
 * @Modified By:
 */
public enum PayStatusEnum {
    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
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
