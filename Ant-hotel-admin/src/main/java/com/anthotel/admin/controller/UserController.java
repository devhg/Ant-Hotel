package com.anthotel.admin.controller;

import com.anthotel.admin.dto.InOutRecord;
import com.anthotel.admin.dto.UserItem;
import com.anthotel.admin.dto.UserSearch;
import com.anthotel.admin.service.UserService;
import com.anthotel.common.base.ResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("one")
    public ResultKit getOneUser(@RequestParam("orderId") String orderId) {
        UserItem userItem = userService.getOneUser(orderId);
        if (userItem != null) {
            return ResultKit.newSuccessResult(userItem);
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
    @GetMapping("inoutSearch")
    public ResultKit inoutSearch(@RequestParam Map<String,String> params) {
//        System.out.println(params);
        UserSearch userSearch = new UserSearch(params.get("roomId"), params.get("name"));
        List<InOutRecord> inOutRecords = userService.inoutSearch(userSearch);
        if (inOutRecords != null) {
            return ResultKit.newSuccessResult(inOutRecords);
        }
        return ResultKit.newFailedResult("error");
    }

    @GetMapping("userSearch")
    public ResultKit userSearch(@RequestParam Map<String, String> params) {
//        System.out.println(params);
        UserSearch userSearch = new UserSearch(params.get("roomId"), params.get("name"));
        List<UserItem> userItems = userService.userSearch(userSearch);
        if (userItems != null) {
            return ResultKit.newSuccessResult(userItems);
        }
        return ResultKit.newFailedResult("error");
    }

    @GetMapping("insert")
    public ResultKit insert(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        int result = userService.insert(map);
        if (result==1) {
            return ResultKit.newSuccessResult(result);
        }
        return ResultKit.newFailedResult("error");
    }
}
