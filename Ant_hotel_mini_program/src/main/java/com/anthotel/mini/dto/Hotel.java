package com.anthotel.mini.dto;

import lombok.Data;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 20:19
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class Hotel {
    private String hotelId; // 酒店编号
    private String hotelName; // 酒店的名字
    private String address; // 酒店地址
    private String service; // 酒店服务
    private Integer baseCost; // 酒店基础消费
    private Float score; // 酒店评分
}
