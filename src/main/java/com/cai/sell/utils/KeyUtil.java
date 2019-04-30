package com.cai.sell.utils;

import java.util.Random;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 22:15 2018/12/11
 * @Modified By:
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(90000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        System.out.println(KeyUtil.genUniqueKey());
    }
}
