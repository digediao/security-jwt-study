package com.zzz.common;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    public static R success(){
        return new R<>(200, "success", null);
    }
    public static R success(Object data){
        return new R<>(200, "success", data);
    }
    public static R success(String msg, Object data){
        return new R<>(200, msg, data);
    }

    // error
        public static R error(){
        return new R<>(500, "error", null);
    }

    public static R error(Object data){
        return new R<>(500, "error", data);
    }

    public static R error(int code, String msg){
        return new R<>(code, msg, null);
    }

    public static R error(String msg, Object data){

        return new R<>(500,msg,data);
    }
}
