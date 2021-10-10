package com.yinuo.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liang
 * @create 2020-05-13 18:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private String errCode;

    public BaseException(ResultCodeEnum rc) {
        super(rc.getMsg());
        this.errCode = rc.getCode();
    }

    public BaseException(ResultCodeEnum rc, String message) {
        super(String.format("%s:%s", rc.getMsg(), message));
        this.errCode = rc.getCode();
    }

    public BaseException(ResultCode rc) {
        super(rc.getMsg());
        this.errCode = rc.getCode();
    }

    public BaseException(ResultCode rc, String message) {
        super(String.format("%s:%s", rc.getMsg(), message));
        this.errCode = rc.getCode();
    }

    public static BaseException of(ResultCodeEnum rc) {
        return new BaseException(rc);
    }

    public static BaseException of(ResultCode rc) {
        return new BaseException(rc);
    }

    public static BaseException of(ResultCodeEnum rc, String message) {
        return new BaseException(rc, message);
    }

    public static BaseException of(ResultCode rc, String message) {
        return new BaseException(rc, message);
    }
}
