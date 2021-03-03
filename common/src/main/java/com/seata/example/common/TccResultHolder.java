package com.seata.example.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description 事务处理缓存器
 * @date 2021/3/2
 */
public class TccResultHolder {

    private static Map<String, Object> orderTccMap = new ConcurrentHashMap<>();

    private static Map<String, Object> storageTccMap = new ConcurrentHashMap<>();

    /**
     * Set Order TCC result.
     *
     * @param txId   the tx id
     * @param result the result
     */
    public static void setOrderTccMap(String txId, Object result){
        orderTccMap.put(txId, result);
    }

    /**
     * Get Order TCC result string.
     *
     * @param txId the tx id
     * @return the string
     */
    public static Object getOrderTccMap(String txId){
        return orderTccMap.get(txId);
    }

    /**
     * Set Storage TCC result.
     *
     * @param txId   the tx id
     * @param result the result
     */
    public static void setStorageTccMap(String txId, Object result){
        storageTccMap.put(txId, result);
    }

    /**
     * Get Storage TCC result string.
     *
     * @param txId the tx id
     * @return the string
     */
    public static Object getStorageTccMap(String txId){
        return storageTccMap.get(txId);
    }
}