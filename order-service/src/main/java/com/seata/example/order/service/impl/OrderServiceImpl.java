package com.seata.example.order.service.impl;

import com.seata.example.common.TccResultHolder;
import com.seata.example.order.eneity.Order;
import com.seata.example.order.mapper.OrderMapper;
import com.seata.example.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    @Override
    public boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        Object orderTccMap = TccResultHolder.getOrderTccMap(xid);
        System.out.println("TCC提交：事务上下文数据=" + orderTccMap);
        System.out.println("TCC提交, xid:" + xid + ", code:" + actionContext.getActionContext("code") + ", count:" + actionContext.getActionContext("code"));
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Order order = (Order) TccResultHolder.getOrderTccMap(xid);
        System.out.println("TCC回滚：事务上下文数据=" + order);
        System.out.println("TCC回滚业务数据，删除订单, xid:" + xid + ", code:" + actionContext.getActionContext("code") + ", count:" + actionContext.getActionContext("code"));
        if (Objects.nonNull(order)) {
            mapper.deleteById(order.getId());
        }
        return true;
    }
}
