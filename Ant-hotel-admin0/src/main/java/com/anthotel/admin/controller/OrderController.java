package com.anthotel.admin.controller;

import com.anthotel.admin.dto.CanteenRecord;
import com.anthotel.admin.dto.ReserveRecord;
import com.anthotel.admin.service.OrderService;
import com.anthotel.common.base.ResultKit;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultKit getLiveOrder(@RequestParam(value = "orderId", required = false) String id) {
        if (id != null) {
            ReserveRecord canteenOrder = orderService.getLivenOrder(id);
            if (canteenOrder != null) {
                return ResultKit.newSuccessResult(canteenOrder);
            }
        } else {
            List<ReserveRecord> reserveRecords = orderService.fetchLiveOrderList();
            return ResultKit.newSuccessResult(reserveRecords);
        }
        return ResultKit.newFailedResult("error");
    }

    @GetMapping("food")
    public ResultKit getFoodOrder(@RequestParam(value = "orderId", required = false) String id) {
        System.out.println("id = " + id);
        if (id != null) {
            CanteenRecord canteenOrder = orderService.getCanteenOrder(id);
            if (canteenOrder != null) {
                return ResultKit.newSuccessResult(canteenOrder);
            }
        } else {
            List<CanteenRecord> canteenRecords = orderService.fetchCanteenOrderList();
            if (canteenRecords != null) {
                return ResultKit.newSuccessResult(canteenRecords);
            }
        }
        return ResultKit.newFailedResult("error");
    }

}
