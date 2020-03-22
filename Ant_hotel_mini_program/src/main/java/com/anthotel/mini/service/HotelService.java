package com.anthotel.mini.service;

import com.anthotel.mini.dto.Hotel;
import com.anthotel.mini.dto.HotelRoom;
import com.anthotel.mini.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 20:19
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Service
public class HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    public List<Hotel> fetchHotelList() {
        return hotelMapper.fetchHotelList();
    }


    public List<HotelRoom> getHotelRoomInfo(String hid) {
        return hotelMapper.getHotelRoomInfo(hid);
    }
}
