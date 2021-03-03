package com.seata.example.storage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.seata.example.common.TccResultHolder;
import com.seata.example.storage.entity.Storage;
import com.seata.example.storage.mapper.StorageMapper;
import com.seata.example.storage.service.StorageService;
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
public class StorageServiceImpl implements StorageService {

    private final StorageMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void minus(String code, int count) {
        String xid = RootContext.getXID();
        Storage storage = this.mapper.selectOne(new LambdaQueryWrapper<Storage>().eq(Storage::getCode, code));
        storage.setCount(storage.getCount() - count);
        this.mapper.updateById(storage);
        // 构建人为异常2
        if (count < 0 || storage.getCount() < 0) {
            throw new RuntimeException("库存中心人为异常..");
        }
        TccResultHolder.setStorageTccMap(xid, storage);
        log.info("扣减库存[{}],[{}],[{}]成功", code, count, xid);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Object storageTccMap = TccResultHolder.getStorageTccMap(xid);
        System.out.println("TCC提交：事务上下文数据=" + storageTccMap);
        System.out.println("TCC提交, xid:" + xid + ", code:" + actionContext.getActionContext("code") + ", count:" + actionContext.getActionContext("code"));
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Storage storage = (Storage) TccResultHolder.getStorageTccMap(xid);
        System.out.println("TCC回滚：事务上下文数据=" + storage);
        System.out.println("TCC回滚业务数据，回库库存数据, xid:" + xid + ", code:" + actionContext.getActionContext("code") + ", count:" + actionContext.getActionContext("code"));
        if (Objects.nonNull(storage)) {
            String count = String.valueOf(actionContext.getActionContext().get("count"));
            storage.setCount(storage.getCount() + Integer.parseInt(count));
            mapper.updateById(storage);
        }
        return true;
    }
}