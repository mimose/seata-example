package com.seata.example.order.service.impl;

import com.seata.example.common.TccResultHolder;
import com.seata.example.order.eneity.Order;
import com.seata.example.order.mapper.OrderMapper;
import com.seata.example.order.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void createOrder(String userId, String code, Integer count) {
        String xid = RootContext.getXID();
        Order order = new Order();
        order.setUserId(userId).setCode(code).setCount(count);
        this.mapper.insert(order);
        // 构建人为异常
        if ("002".equals(code)) {
            throw new RuntimeException("订单中心人为异常..");
        }
        TccResultHolder.setOrderTccMap(xid, order);
        log.info("订单中心创建订单[{}],[{}],[{}],[{}]成功", userId, code, count, xid);
    }
}
