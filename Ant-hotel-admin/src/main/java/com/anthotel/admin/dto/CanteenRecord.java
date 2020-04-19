package com.anthotel.admin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Devhui
 * @Date: 2020/4/15 19:40
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class CanteenRecord {
    private String userId;
    private String orderId;
    private String name;
    private String food;
    private Float totalPrice;
    private Float coupon;
    private Float cost;
    private Date onTime;
}
