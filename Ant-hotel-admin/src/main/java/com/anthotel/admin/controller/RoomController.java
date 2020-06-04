package com.anthotel.admin.controller;

import com.anthotel.admin.dto.RoomInfo;
import com.anthotel.admin.service.RoomService;
import com.anthotel.common.base.ResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
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
    @PostMapping("add")
    public ResultKit addRoom(@RequestParam("roomId") String roomId,
                             @RequestParam("roomStatus") Byte roomStatus,
                             @RequestParam("roomType") String roomType,
                             @RequestParam("things") String things) {
        RoomInfo roomInfo=new RoomInfo(roomId,roomStatus,roomType,things);
        int result = roomService.addRoom(roomInfo);
        if (result==1) {
            return ResultKit.newSuccessResult(roomInfo);
        }
        return ResultKit.newFailedResult("error");
    }
    @GetMapping("delete")
    public ResultKit deleteRoom(@RequestParam("roomId") String roomId) {
        int result = roomService.deleteRoom(roomId);
        if (result==1) {
            return ResultKit.newSuccessResult(result);
        }
        return ResultKit.newFailedResult("error");
    }
    @GetMapping("update")
    public ResultKit updateRoom(@RequestParam("roomId") String roomId,
                                @RequestParam("roomStatus") Byte roomStatus,
                                @RequestParam("roomType") String roomType,
                                @RequestParam("things") String things) {
        RoomInfo roomInfo = new RoomInfo(roomId, roomStatus, roomType, things);
        int result = roomService.updateRoom(roomInfo);
        if (result==1) {
            return ResultKit.newSuccessResult(result);
        }
        return ResultKit.newFailedResult("error");
    }
}
