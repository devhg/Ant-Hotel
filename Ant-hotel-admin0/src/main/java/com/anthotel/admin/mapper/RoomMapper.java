package com.anthotel.admin.mapper;

import com.anthotel.admin.dto.InOutRecord;
import com.anthotel.admin.dto.RoomInfo;
import com.anthotel.admin.dto.UserItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:43
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Repository
public interface RoomMapper {
    List<RoomInfo> fetchRoomList(String keyWords);
}
