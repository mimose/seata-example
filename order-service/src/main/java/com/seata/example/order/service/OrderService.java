package com.seata.example.order.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
@LocalTCC
public interface OrderService {

    /**
     * 创建订单
     *
     * @param userId 用户ID
     * @param code   商品code
     * @param count  数量
     */
    @TwoPhaseBusinessAction(name = "Tcc-Order", commitMethod = "commit", rollbackMethod = "rollback")
    void createOrder(@BusinessActionContextParameter(paramName = "userId") String userId,
                     @BusinessActionContextParameter(paramName = "code") String code,
                     @BusinessActionContextParameter(paramName = "count") Integer count);

    /**
     * Commit boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean rollback(BusinessActionContext actionContext);
}
