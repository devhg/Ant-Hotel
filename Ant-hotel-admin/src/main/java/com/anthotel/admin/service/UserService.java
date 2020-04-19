package com.anthotel.admin.service;

import com.anthotel.admin.dto.InOutRecord;
import com.anthotel.admin.dto.UserItem;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:42
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
public interface UserService {
    List<UserItem> fetchUserList();

    List<InOutRecord> fetchInOutRecord();
}
