package com.anthotel.admin.controller;

import com.anthotel.common.base.ResultKit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:41
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @GetMapping("live")
    public ResultKit getLiveOrderList() {

        ResultKit<Object> resultKit = new ResultKit<>();

        resultKit.setMessage("请求成功");
        resultKit.setCode(2111);
        return resultKit;
    }

}
