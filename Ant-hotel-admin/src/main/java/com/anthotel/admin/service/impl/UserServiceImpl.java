package com.anthotel.admin.service.impl;

import com.anthotel.admin.dto.*;
import com.anthotel.admin.mapper.OrderMapper;
import com.anthotel.admin.mapper.UserMapper;
import com.anthotel.admin.service.OrderService;
import com.anthotel.admin.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:42
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserItem> fetchUserList() {
        return userMapper.fetchUserList();
    }

    @Override
    public List<InOutRecord> fetchInOutRecord() {
        return userMapper.fetchInOutRecord();
    }

    @Override
    public List<UserItem> userSearch(UserSearch userSearch) {
        return userMapper.userSearch(userSearch);
    }

    @Override
    public List<InOutRecord> inoutSearch(UserSearch userSearch) {
        return userMapper.inoutSearch(userSearch);
    }

    @Override
    public int insert(Map<String,Object> map) {
        return userMapper.insert(map);
    }

    @Override
    public UserItem getOneUser(String orderId) {
        return userMapper.getOneUser(orderId);
    }
}
