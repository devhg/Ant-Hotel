package com.anthotel.admin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Devhui
 * @Date: 2020/4/19 14:15
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class UserItem {
    private String orderId;
    private String name;
    private Date signTime;
    private Date leaveTime;
    private String roomId;
    private Float cost;
    private Float coupon;
}
