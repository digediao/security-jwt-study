package com.zzz.common;

import javax.servlet.http.HttpServletResponse;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 9:17
 */


public class WebUtils {
    /**
     * 将字符串渲染到客户端
     */
    public static String renderString(HttpServletResponse response, String string) {
        try{
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

