package com.seata.example.storage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
@Data
@Accessors(chain = true)
@TableName("test_storage")
public class Storage {

    private Long id;

    private String code;

    private Integer count;

}
