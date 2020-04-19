package com.anthotel.admin.controller;

import com.anthotel.admin.dto.InOutRecord;
import com.anthotel.admin.dto.UserItem;
import com.anthotel.admin.service.UserService;
import com.anthotel.common.base.ResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/4/19 14:22
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public ResultKit getUserList() {
        List<UserItem> userItems = userService.fetchUserList();
        if (userItems != null) {
            return ResultKit.newSuccessResult(userItems);
        }
        return ResultKit.newFailedResult("error");
    }

    @GetMapping("inout")
    public ResultKit getUserInRecord() {
        List<InOutRecord> inOutRecords = userService.fetchInOutRecord();
        if (inOutRecords != null) {
            return ResultKit.newSuccessResult(inOutRecords);
        }
        return ResultKit.newFailedResult("error");
    }

}
