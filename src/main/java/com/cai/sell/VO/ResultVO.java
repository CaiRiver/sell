package com.cai.sell.VO;

import lombok.Data;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 21:59 2018/10/30
 * @Modified By:
 */
@Data
public class ResultVO<T> {
    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*具体内容*/
    private T data;
}
