package com.seata.example.business.feign.remote;

import com.seata.example.business.feign.fallback.StorageFeignClientFallbackFactory;
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
@FeignClient(name = "storage-service", path = "storage", fallbackFactory = StorageFeignClientFallbackFactory.class)
public interface StorageFeignClient {

    /**
     * 创建库存信息
     * @param code
     * @param count
     * @return
     */
    @RequestMapping(value = "/test/minus", method = RequestMethod.GET)
    ResultInfo minus(@RequestParam String code, @RequestParam Integer count);
}
