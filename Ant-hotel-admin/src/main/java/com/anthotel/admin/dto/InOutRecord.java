package com.anthotel.admin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Devhui
 * @Date: 2020/4/19 14:33
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
@Data
public class InOutRecord {
    private String userId;
    private String name;
    private String roomId;
    private Date updateTime;
    private Byte type;
}
