package com.anthotel.admin.mapper;

import com.anthotel.admin.dto.CanteenRecord;
import com.anthotel.admin.dto.ReserveRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:43
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Repository
public interface OrderMapper {
    List<ReserveRecord> fetchLiveOrderList();

    ReserveRecord getLivenOrder(String id);

    List<CanteenRecord> fetchCanteenOrderList();

    CanteenRecord getCanteenOrder(String id);
}
