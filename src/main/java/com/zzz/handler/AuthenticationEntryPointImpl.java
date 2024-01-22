package com.zzz.handler;

import com.alibaba.fastjson.JSON;
import com.zzz.common.R;
import com.zzz.common.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author digediao
 * @version 1.0
 * @description 认证失败处理类
 * @Date 2024/1/22 13:55
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 未登录或token失效时访问接口时，自定义的返回结果
        R result = R.error(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请先登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
