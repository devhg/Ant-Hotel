package com.anthotel.mini.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anthotel.common.base.ResultKit;
import com.anthotel.mini.dto.Hotel;
import com.anthotel.mini.dto.HotelRoom;
import com.anthotel.mini.dto.ReserveOrder;
import com.anthotel.mini.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 20:18
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@RestController
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;


    @GetMapping("list")
    ResultKit getHotelList() {
        ResultKit<List> resultKit = new ResultKit<>();
        List<Hotel> hotels = hotelService.fetchHotelList();

        if (hotels != null) {
            resultKit.setCode(200);
            resultKit.setMessage("获取成功");
            resultKit.setData(hotels);
        } else {
            resultKit.setCode(-200);
            resultKit.setMessage("获取失败");
        }
        return resultKit;
    }

    @GetMapping("roomInfo/{hid}")
    ResultKit getHotelRoomInfo(@PathVariable("hid") String hid) {
        ResultKit<List> resultKit = new ResultKit<>();

        System.out.println("hid = " + hid);
        List<HotelRoom> hotelRoomInfo = hotelService.getHotelRoomInfo(hid);
        if (hotelRoomInfo != null) {
            resultKit.setCode(200);
            resultKit.setMessage("获取成功");
            resultKit.setData(hotelRoomInfo);
        } else {
            resultKit.setCode(-200);
            resultKit.setMessage("获取失败");
        }
        return resultKit;
    }

    @PostMapping("reserve")
    ResultKit reserve(@RequestBody ReserveOrder order) {
        ResultKit<List> resultKit = new ResultKit<>();
        System.out.println("order = " + order);
        return resultKit;
    }
}
