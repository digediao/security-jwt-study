package com.zzz.expression;

import com.zzz.domain.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/22 15:09
 */

@Component("se")
public class SecurityExpressionRoot {
    public boolean hasAuthority(String permission){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser.getPermissions().contains(permission);
    }
}
