package com.cai.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author:caicai
 * @Description:
 * @Date: Created in 16:45 2018/10/28
 * @Modified By:
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /*类目id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    /*类目名字*/
    private String categoryName;
    /*类目编号*/
    private Integer categoryType;
    private Date updateTime;
}
