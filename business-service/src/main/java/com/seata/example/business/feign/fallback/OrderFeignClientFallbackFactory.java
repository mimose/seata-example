package com.seata.example.business.feign.fallback;

import com.seata.example.business.feign.remote.OrderFeignClient;
import com.seata.example.common.ResultInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/3
 */
@Component
public class OrderFeignClientFallbackFactory implements FallbackFactory<OrderFeignClient> {

    @Override
    public OrderFeignClient create(Throwable throwable) {
        return (userId, code, count) -> new ResultInfo<>().setCode(-999).setCause(throwable).setMessage("fallback");
    }
}
