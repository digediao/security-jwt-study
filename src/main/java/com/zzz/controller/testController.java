package com.zzz.controller;

import com.zzz.common.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/18 17:53
 */

@RestController
@RequestMapping("/")
public class testController {

    @PostMapping("/login")
    public R login() {
        return new R().success();
    }
}
