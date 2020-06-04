package com.anthotel.admin.service.impl;

import com.anthotel.admin.dto.InOutRecord;
import com.anthotel.admin.dto.RoomInfo;
import com.anthotel.admin.dto.UserItem;
import com.anthotel.admin.mapper.RoomMapper;
import com.anthotel.admin.mapper.UserMapper;
import com.anthotel.admin.service.RoomService;
import com.anthotel.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:42
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<RoomInfo> fetchRoomList(String keyWords) {
        return roomMapper.fetchRoomList(keyWords);
    }

    @Override
    public int addRoom(RoomInfo roomInfo){
        return roomMapper.addRoom(roomInfo);
    }

    @Override
    public int deleteRoom(String roomId) {
        return roomMapper.deleteRoom(roomId);
    }

    @Override
    public int updateRoom(RoomInfo roomInfo) {
        return roomMapper.updateRoom(roomInfo);
    }
}
