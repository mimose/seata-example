package com.seata.example.order.controller;

import com.seata.example.common.ResultInfo;
import com.seata.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
@RestController
@RequestMapping("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService service;

    /**
     * 创建订单
     * @param userId
     * @param code
     * @param count
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ResultInfo create(@RequestParam("userId") String userId, @RequestParam("code") String code, @RequestParam("count") Integer count) {
        service.createOrder(userId, code, count);
        return new ResultInfo().setCode(0);
    }
}
