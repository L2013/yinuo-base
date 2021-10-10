package com.yinuo.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liang
 */
@Data
@AllArgsConstructor
public class ResultCode {
    private String code;
    private String msg;
}
