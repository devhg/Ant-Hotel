package com.anthotel.mini.dto;

import lombok.Data;

/**
 * @Author: Devhui
 * @Date: 2020/3/18 20:37
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class HotelRoom {
    private String hotelId;
    private String roomType;
    private String roomService;
    private Integer roomCount;
    private Float roomPrice;
    private Float hourPrice;
    private Integer id;
}
