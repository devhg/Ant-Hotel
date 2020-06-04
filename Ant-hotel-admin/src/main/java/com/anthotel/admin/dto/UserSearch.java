package com.anthotel.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserSearch {
    private String roomId;
    private String name;
}
