package com.anthotel.face.dto;


import lombok.Data;

import java.util.Date;


@Data
public class UserFaceInfo {

    private String userId;

    private Integer groupId;

    private String faceId;

    private String name;

    private String phoneNumber;

    private Date createTime;

    private Date updateTime;

    private byte[] faceFeature;

}

