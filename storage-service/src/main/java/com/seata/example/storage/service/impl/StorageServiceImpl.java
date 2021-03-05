package com.seata.example.storage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.seata.example.common.TccResultHolder;
import com.seata.example.storage.entity.Storage;
import com.seata.example.storage.mapper.StorageMapper;
import com.seata.example.storage.service.StorageService;
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

}