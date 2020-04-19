package com.anthotel.admin.dto;

import lombok.Data;

/**
 * @Author: Devhui
 * @Date: 2020/4/19 19:37
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class RoomInfo {
    private String roomId;
    private Byte roomStatus;
    private String roomType;
    private String things;
}
