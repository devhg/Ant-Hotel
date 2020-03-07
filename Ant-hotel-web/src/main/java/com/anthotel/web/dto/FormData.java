package com.anthotel.web.dto;

import lombok.Data;

/**
 * @Author: Devhui
 * @Date: 2020/3/2 21:48
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class FormData {
    String name; // 姓名
    String userId; // 身份证号
    String address; // 地址
    String dateTo; // 身份证有效期至
    String phone; // 手机号
    String idImage; // 身份证照片
    String videoImage; // 视频截屏
}
