package com.anthotel.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Fitness {
    private String userId;
    private String name;
    private String roomId;
    private String fitnessType;
    private Date onTime;
}
