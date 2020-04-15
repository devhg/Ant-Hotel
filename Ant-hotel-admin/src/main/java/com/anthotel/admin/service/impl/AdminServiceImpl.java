package com.anthotel.admin.service.impl;

import com.anthotel.admin.dto.Admin;
import com.anthotel.admin.mapper.AdminMapper;
import com.anthotel.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Devhui
 * @Date: 2020/3/31 21:00
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin loginCheck(String username, String password) {
        return adminMapper.loginCheck(username, password);
    }
}
