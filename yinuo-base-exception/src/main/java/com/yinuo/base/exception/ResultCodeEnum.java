package com.yinuo.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liangliang
 */

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * takes 5 digits to present result code
     */
    SUCCESS("20000", "success"),
    /**
     * 599段:定义一些未知的异常
     */
    UNKNOWN("59999", "unknown error"),
    FAIL("59901", "failed!"),
    EXCEPTION("50097", ""),
    /**
     * 500段:常见的系统级别异常
     */
    NULL("50000", "Null value not allowed!"),
    EMPTY("50002", "Empty value not allowed!"),

    /**
     * 510段:专用于接口类应用系统后台错误
     */
    WRONG_ARGUMENTS("51001", "wrong argument(s)"),
    INVALID_HTTP_REQUEST_BODY("51002", "invalid http request body!"),
    /**
     * 520段:应用系统定义的响应码，针对前后端应用的通用类错误
     */
    TOKEN_NOT_FOUND("52002", "token not found"),
    TOKEN_ILLEGAL("52004", "token is illegal"),
    TOKEN_EXPIRED("52006", "token expired"),
    OTHER_LOGIN("52008", "others was login"),
    NO_AUTH("52010", "not authorized to access"),
    SSO_EXCEPTION("52020", "sso exception");

    private String code;
    private String msg;
}
