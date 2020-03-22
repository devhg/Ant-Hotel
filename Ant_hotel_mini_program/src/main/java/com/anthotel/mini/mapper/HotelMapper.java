package com.anthotel.mini.mapper;

import com.anthotel.mini.dto.Hotel;
import com.anthotel.mini.dto.HotelRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 20:19
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Repository
public interface HotelMapper {

    List<Hotel> fetchHotelList();

    List<HotelRoom> getHotelRoomInfo(String hid);
}
