package com.anthotel.admin.mapper;

import com.anthotel.admin.dto.InOutRecord;
import com.anthotel.admin.dto.UserItem;
import com.anthotel.admin.dto.UserSearch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:43
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Repository
public interface UserMapper {
    List<UserItem> fetchUserList();

    List<InOutRecord> fetchInOutRecord();

    List<UserItem> userSearch(UserSearch userSearch);

    List<InOutRecord> inoutSearch(UserSearch userSearch);

    int insert(Map<String,Object> map);

    UserItem getOneUser(String orderId);
}
