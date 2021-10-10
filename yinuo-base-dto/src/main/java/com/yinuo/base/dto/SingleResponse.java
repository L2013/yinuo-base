package com.yinuo.base.dto;


import lombok.Data;

/**
 * @author liang
 */
@Data
public class SingleResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private T data;

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}
