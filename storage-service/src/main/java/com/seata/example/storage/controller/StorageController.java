package com.seata.example.storage.controller;

import com.seata.example.common.ResultInfo;
import com.seata.example.storage.service.StorageService;
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
public class StorageController {

    private final StorageService service;

    /**
     * 创建库存信息
     * @param code
     * @param count
     * @return
     */
    @RequestMapping(value = "minus", method = RequestMethod.GET)
    public ResultInfo minus(@RequestParam String code, @RequestParam Integer count) {
        service.minus(code, count);
        return new ResultInfo().setCode(0);
    }
}
