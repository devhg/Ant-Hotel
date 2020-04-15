package com.anthotel.admin.controller;

import com.anthotel.admin.dto.ReserveRecord;
import com.anthotel.admin.service.OrderService;
import com.anthotel.common.base.ResultKit;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:41
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@RestController
@RequestMapping("order")
@Api(tags = "订单管理相关接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("live")
    public ResultKit getLiveOrderList() {

        ResultKit<Object> resultKit = new ResultKit<>();
        List<ReserveRecord> reserveRecords = orderService.fetchLiveOrderList();
        System.out.println("reserveRecords = " + reserveRecords);
        resultKit.setData(reserveRecords);
        resultKit.setMessage("请求成功");
        resultKit.setCode(2111);
        return resultKit;
    }

    @GetMapping("food")
    public ResultKit getFoodOrderList() {

        ResultKit<Object> resultKit = new ResultKit<>();
        List<ReserveRecord> reserveRecords = orderService.fetchLiveOrderList();
        System.out.println("reserveRecords = " + reserveRecords);
        resultKit.setData(reserveRecords);
        resultKit.setMessage("请求成功");
        resultKit.setCode(2111);
        return resultKit;
    }

}
