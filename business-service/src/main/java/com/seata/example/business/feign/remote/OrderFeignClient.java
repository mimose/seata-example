package com.seata.example.business.feign.remote;

import com.seata.example.business.feign.fallback.OrderFeignClientFallbackFactory;
import com.seata.example.common.ResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/3
 */
@FeignClient(name = "order-service", path = "order", fallbackFactory = OrderFeignClientFallbackFactory.class)
public interface OrderFeignClient {

    /**
     * 创建订单
     * @param userId
     * @param code
     * @param count
     * @return
     */
    @RequestMapping(value = "/test/create", method = RequestMethod.GET)
    ResultInfo create(@RequestParam("userId") String userId, @RequestParam("code") String code, @RequestParam("count") Integer count);
}
