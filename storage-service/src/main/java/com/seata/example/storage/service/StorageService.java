package com.seata.example.storage.service;

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
public interface StorageService {

    /**
     * Prepare boolean.
     *
     * @param code  商品code
     * @param count 数量
     * @return the boolean
     */
    @TwoPhaseBusinessAction(name = "TCC-Storage", commitMethod = "commit", rollbackMethod = "rollback")
    void minus(@BusinessActionContextParameter(paramName = "code") String code,
               @BusinessActionContextParameter(paramName = "count") int count);

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
