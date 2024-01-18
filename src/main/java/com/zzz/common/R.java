package com.zzz.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/18 17:55
 */

@Data
@NoArgsConstructor
@Builder
public class R<T> {
    int code;
    String msg;
    T data;

    public R(){
        this.code = 200;
        this.msg = "success";
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // success
    public R<T> success(){
        this.code = 200;
        this.msg = "success";
        return new R<>(code,msg,null);
    }
    public R<T> success(T data){
        this.code = 200;
        this.msg = "success";
        return new R<>(code,msg,data);
    }
    public R<T> success(String msg, T data){
        this.code = 200;
        this.msg = msg;
        this.data = data;
        return new R<>(code,msg,data);
    }

    // error
    public R<T> error(){
        this.code = 500;
        this.msg = "error";
        return new R<>(code,msg,null);
    }

    public R<T> error(T data){
        this.code = 500;
        this.msg = "error";
        return new R<>(code,msg,data);
    }

    public R<T> error(int code, String msg){
        this.code = code;
        this.msg = msg;
        return new R<>(code,msg,null);
    }

    public R<T> error(String msg, T data){
        this.code = 500;
        this.msg = msg;
        this.data = data;
        return new R<>(code,msg,data);
    }
}
