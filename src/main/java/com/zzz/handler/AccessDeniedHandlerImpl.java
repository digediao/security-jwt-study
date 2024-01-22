package com.zzz.handler;

import com.alibaba.fastjson.JSON;
import com.zzz.common.R;
import com.zzz.common.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author digediao
 * @version 1.0
 * @description 权限不足处理类
 * @Date 2024/1/22 14:07
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 未授权时访问接口时，自定义的返回结果
        R result = R.error(HttpStatus.FORBIDDEN.value(), "没有权限，请联系管理员授权");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
