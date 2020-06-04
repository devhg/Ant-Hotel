package com.anthotel.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anthotel.admin.dto.Admin;
import com.anthotel.admin.service.AdminService;
import com.anthotel.common.base.ResultKit;
import com.anthotel.common.utils.JwtUtils;
import com.anthotel.common.utils.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

/**
 * @Author: Devhui
 * @Date: 2020/3/14 20:25
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@RestController
@RequestMapping("auth")
@Api(tags = "酒店管理人员登陆授权")
public class AuthController {

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    ResultKit<Object> login(@RequestBody String param) {
        JSONObject json = JSON.parseObject(param);
        String username = (String) json.get("username");
        String password = (String) json.get("password");

        System.out.println(json);
        ResultKit<Object> resultKit = new ResultKit<>();
        Admin admin = adminService.loginCheck(username, password);
        System.out.println(admin);
        if (admin != null) {
            // 签发token
            String token = JwtUtils.createJWT(UUID.randomUUID().toString(), admin.getUsername(), 60 * 1000);
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("user", admin);

            resultKit.setCode(ResultCode.SUCCESS.code());
            resultKit.setMessage("登录成功");
            resultKit.setData(map);
        } else {
            resultKit.setCode(ResultCode.WRONG_UP.code());
            resultKit.setMessage("登录失败，账号或密码错误！");
        }
        return resultKit;
    }

    @GetMapping("info")
    String info() {
        return "success";
    }
}
