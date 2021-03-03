package com.seata.example.business.service;

import com.seata.example.business.feign.remote.OrderFeignClient;
import com.seata.example.business.feign.remote.StorageFeignClient;
import com.seata.example.common.ResultInfo;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/3
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessService {

    private final OrderFeignClient orderFeignClient;

    private final StorageFeignClient storageFeignClient;

    @GlobalTransactional(name = "createOrder", timeoutMills = 60000, rollbackFor = Exception.class)
    public ResultInfo buy(String userId, String code, Integer count) {
        String xid = RootContext.getXID();
        log.info("用户购买商品[{}],[{}],[{}]", code, count, xid);
        orderFeignClient.create(userId, code, count);
        log.info("调用订单中心服务成功");
        storageFeignClient.minus(code, count);
        log.info("调用库存中心服务成功");
        return new ResultInfo<>().setCode(0).setMessage("success");
    }

    @GlobalTransactional(name = "createOrderFail", timeoutMills = 60000, rollbackFor = Exception.class)
    public ResultInfo buyFail(String userId, String code, Integer count) {
        String xid = RootContext.getXID();
        log.info("用户购买商品[{}],[{}],[{}]", code, count, xid);
        orderFeignClient.create(userId, code, count);
        log.info("调用订单中心服务成功");
        storageFeignClient.minus(code, count);
        log.info("调用库存中心服务成功");
        // 构建人为异常
        throw new RuntimeException("业务中心人为异常..");
    }
}
