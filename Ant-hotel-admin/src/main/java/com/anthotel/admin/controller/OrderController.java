package com.anthotel.admin.controller;

import com.anthotel.admin.dto.CanteenRecord;
import com.anthotel.admin.dto.ReserveRecord;
import com.anthotel.admin.dto.UserSearch;
import com.anthotel.admin.service.OrderService;
import com.anthotel.common.base.ResultKit;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("liveSearch")
    public ResultKit inoutSearch(@RequestParam Map<String,String> params) {
//        System.out.println(params);
        UserSearch userSearch = new UserSearch(params.get("roomId"), params.get("name"));
        List<ReserveRecord> reserveRecords = orderService.liveSearch(userSearch);
        if (reserveRecords != null) {
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
    @GetMapping("foodSearch")
    public ResultKit foodSearch(@RequestParam(value = "userId",required = false) String userId,
                                @RequestParam(value = "name",required = false) String name) {
//        System.out.println(params);
        UserSearch userSearch = new UserSearch(userId,name);
        List<CanteenRecord> canteenRecords = orderService.foodSearch(userSearch);
        if (canteenRecords != null) {
            return ResultKit.newSuccessResult(canteenRecords);
        }
        return ResultKit.newFailedResult("error");
    }

}
