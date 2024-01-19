package com.zzz.controller;

import com.zzz.common.R;
import com.zzz.domain.User;
import com.zzz.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/18 17:53
 */

@RestController
@RequestMapping("/")
public class testController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('test')")
    public R hello() {
        return R.success("hello");
    }
}
