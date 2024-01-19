package com.zzz.filter;

import com.zzz.common.JwtUtil;
import com.zzz.domain.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 16:12
 */


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 在UsernamePasswordAuthenticationFilter之前执行，对token进行解析，获取用户信息，放入SecurityContextHolder中
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*获取token*/
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            /*放行*/
            filterChain.doFilter(request,response);
            return;
        }

        String userid = null;
        /*解析token*/
        try{
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        /*从redis中获取用户信息*/
        String redisKey = "userid:"+userid;
        LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        /*SecurityContextHolder*/
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        /*放行*/
        filterChain.doFilter(request,response);
    }
}
