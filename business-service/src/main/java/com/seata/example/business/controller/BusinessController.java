package com.seata.example.business.controller;

import com.seata.example.business.service.BusinessService;
import com.seata.example.common.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/3
 */
@RestController
@RequestMapping("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessController {

    private final BusinessService businessService;

    /**
     * 成功
     * @return
     */
    @RequestMapping(value = "buy", method = RequestMethod.GET)
    public ResultInfo buy() {
        return businessService.buy("userId", "001", 1);
    }

    /**
     * 下订单时失败
     * @return
     */
    @RequestMapping(value = "buyOrderFail", method = RequestMethod.GET)
    public ResultInfo buyOrderFail() {
        return businessService.buy("userId", "002", 1);
    }

    /**
     * 最终失败
     * @return
     */
    @RequestMapping(value = "buyFail", method = RequestMethod.GET)
    public ResultInfo buyFail() {
        return businessService.buyFail("userId", "001", 1);
    }
}
