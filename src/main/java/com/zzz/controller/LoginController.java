package com.zzz.controller;

import com.zzz.common.R;
import com.zzz.domain.User;
import com.zzz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 11:52
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/logout")
    public R logout() {
        return loginService.logout();
    }
}
