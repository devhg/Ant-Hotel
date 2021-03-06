package com.anthotel.admin.controller;

import com.anthotel.admin.dto.RoomInfo;
import com.anthotel.admin.service.RoomService;
import com.anthotel.common.base.ResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/4/19 19:41
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("list")
    public ResultKit getRoomList(@RequestParam("hotelId") String id) {
        List<RoomInfo> roomInfos = roomService.fetchRoomList(id);
        if (roomInfos != null) {
            return ResultKit.newSuccessResult(roomInfos);
        }
        return ResultKit.newFailedResult("error");
    }
}
