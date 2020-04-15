package com.anthotel.mini.controller;

import com.anthotel.common.base.ResultKit;
import com.anthotel.common.utils.DateUtil;
import com.anthotel.mini.dto.Hotel;
import com.anthotel.mini.dto.HotelRoom;
import com.anthotel.mini.dto.ReserveOrder;
import com.anthotel.mini.service.HotelService;
import com.anthotel.mini.service.OrderService;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 20:18
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("reserve")
    ResultKit reserve(@RequestBody ReserveOrder order) {
        Date date = new Date();
        int year = DateUtil.getYear(date);
        int month = DateUtil.getMonth(date);
        int day = DateUtil.getDay(date);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append(year).append(month).append(day).append(date.getTime());

        order.setOrderId(append.toString());
        order.setReserveTime(date);
        ResultKit<List> resultKit = new ResultKit<>();
        int i = orderService.insertOrder(order);
        System.out.println("i = " + i);

        if (i == 1) {
            resultKit.setMessage("预定成功");
            resultKit.setCode(200);
        } else {
            resultKit.setMessage("预定失败");
            resultKit.setCode(-200);
        }
        return resultKit;
    }

    @GetMapping("uid/{uid}")
    ResultKit getUserOrder(@PathVariable("uid") String openId) {
        ResultKit<List> resultKit = new ResultKit<>();
        System.out.println("openId = " + openId);

        List<Map> orders = orderService.getOrders(openId);
        System.out.println(orders);
        if (orders != null) {
            resultKit.setMessage("成功");
            resultKit.setCode(200);
            resultKit.setData(orders);
        } else {
            resultKit.setMessage("失败");
            resultKit.setCode(-200);
        }
        return resultKit;
    }

    public void test() {
        // 获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        // 获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();


        // 时间转字符串格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);

        // 字符串转时间
        String dateTimeStr = "2018-07-28 14:11:15";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeStr, df);
    }

    //将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }


    //将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
    public static Date localDateTimeConvertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

}
