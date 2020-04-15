package com.anthotel.admin.dto;

import lombok.Data;

import java.sql.Date;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 19:21
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class ReserveRecord {
    private String orderId; // 预定订单编号
    private String hotelId;
    private String openId;
    private String uname; // 用户姓名
    private String phoneNumber; // 用户手机号
    private String roomType; // 房间类型
    private Date reserveTime;
    private Float preFee;
    private Integer status;
    private Date startTime;
    private Date endTime;
}
