package com.anthotel.admin.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Author: Devhui
 * @Date: 2020/3/14 20:25
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("login")
    String login(@RequestBody String param) {
        System.out.println("param = " + param);
        return "error";
    }

    @GetMapping("info")
    String info() {
        return "success";
    }
}
