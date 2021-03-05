package com.seata.example.order.service;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param userId 用户ID
     * @param code   商品code
     * @param count  数量
     */
    void createOrder(String userId, String code, Integer count);

}
