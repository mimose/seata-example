package com.seata.example.storage.service;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
public interface StorageService {

    /**
     * Prepare boolean.
     *
     * @param code  商品code
     * @param count 数量
     * @return the boolean
     */
    void minus(String code, int count);

}
