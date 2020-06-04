package com.anthotel.admin.service;

import com.anthotel.admin.dto.Admin;

/**
 * @Author: Devhui
 * @Date: 2020/3/31 21:00
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
public interface AdminService {
    Admin loginCheck(String username, String password);
}
