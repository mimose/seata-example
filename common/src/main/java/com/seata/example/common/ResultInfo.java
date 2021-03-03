package com.seata.example.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ccy 【chenchangyu@xiao100.com】
 * @description
 * @date 2021/3/2
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ResultInfo<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    private Throwable cause;
}
