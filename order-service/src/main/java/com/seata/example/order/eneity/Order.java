package com.seata.example.order.eneity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
@Data
@Accessors(chain = true)
@TableName("test_order")
public class Order {

    private Long id;

    private String userId;

    private String code;

    private Integer count;

    private Integer money;

    private Integer status;
}
