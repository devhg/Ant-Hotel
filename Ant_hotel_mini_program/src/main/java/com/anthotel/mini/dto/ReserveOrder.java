package com.anthotel.mini.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Devhui
 * @Date: 2020/3/18 20:56
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class ReserveOrder {
    private String orderId; // 订单Id
    private String hotelId; // 酒店Id
    private String openId;  // 用户唯一身份id
    private String uname;   // 用户姓名
    private String uidCard; // 用户身份证号
    private String phoneNumber; // 用户手机号
    private String roomType; // 房间类型
    private Date reserveTime; // 预定时间
    private Float preFee; // 定金
    private Integer status; // 订单状态
    private String startTime; // 开始时间
    private String endTime;   // 结束时间
}
