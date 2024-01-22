package com.zzz.service;

import com.zzz.common.JwtUtil;
import com.zzz.common.R;
import com.zzz.common.constants.Constant;
import com.zzz.domain.LoginUser;
import com.zzz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 11:53
 */

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public R login(User user) {
        /*首先获取SecurityContextHolder中的用户信息，没有则进行认证*/
        /*封装Authentication对象*/
        /*调用AuthenticationManager进行认证*/
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        /*调用DaoAuthenticationProvider的authenticate方法进行认证*/
        /*底层自动调用UserDetailsService的loadUserByUsername方法查询用户及权限信息*/
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            return R.error("认证失败");
        }

        /*认证通过，生成一个用户的jwt，并将信息存入redis，userid作为key，jwt作为value*/
        /*将用户信息返回authenticationToken并封装到SecurityContextHolder中供其他过滤器通过getContext().getAuthentication()获取用户信息*/
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String,Object> map = new HashMap<>();
        map.put(Constant.authorize_token, jwt);
        redisTemplate.opsForValue().set("userid:"+userid, loginUser, 1, TimeUnit.HOURS);

        return R.success(map);
    }

    public R logout() {
        /*获取并删除SecurityContextHolder中的用户信息*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        SecurityContextHolder.clearContext();

        /*删除redis存储的用户信息*/
        redisTemplate.delete("userid:"+loginUser.getUser().getId().toString());

        return R.success("注销成功");
    }
}
