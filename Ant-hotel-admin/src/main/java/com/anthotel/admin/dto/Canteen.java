package com.anthotel.admin.dto;

import lombok.Data;

import java.sql.Date;

/**
 * @Author: Devhui
 * @Date: 2020/4/15 19:40
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class Canteen {
    private String userId;
    private String name;
    private String food;
    private Float totalPrice;
    private String coupon;
    private String cost;
    private Date onTime;
    private Date offTime;
    private String orderId;

}
