package com.zzz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzz.domain.LoginUser;
import com.zzz.domain.SysMenu;
import com.zzz.domain.User;
import com.zzz.mapper.SysMenuMapper;
import com.zzz.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 9:39
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*查询用户信息*/
        User user = userMapper.selectOne(new LambdaQueryWrapper<>(User.class)
                .eq(User::getUserName, username));

        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户账号或密码不正确");
        }

        /*查询权限*/
//        List<String> permissions = new ArrayList<>(Arrays.asList("test","user"));
        List<String> permissions = menuMapper.selectPermissionByUserId(user.getId());

        return new LoginUser(user, permissions);
    }
}
