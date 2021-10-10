package com.yinuo.base.dto;

import com.yinuo.base.exception.ResultCode;
import com.yinuo.base.exception.ResultCodeEnum;
import lombok.Data;

/**
 * @author liang
 */
@Data
public class Response extends DTO {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private String errCode;

    private String msg;

    public static Response succeed() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public static Response failure(String errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setMsg(errMessage);
        return response;
    }

    public static Response failure(ResultCodeEnum rc) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(rc.getCode());
        response.setMsg(rc.getMsg());
        return response;
    }

    public static Response failure(ResultCode rc) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(rc.getCode());
        response.setMsg(rc.getMsg());
        return response;
    }

}
