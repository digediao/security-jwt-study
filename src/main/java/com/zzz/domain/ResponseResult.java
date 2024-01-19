package com.zzz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 10:03
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code) {
        this.code = code;
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
