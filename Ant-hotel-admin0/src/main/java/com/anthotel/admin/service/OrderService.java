package com.anthotel.admin.service;

import com.anthotel.admin.dto.CanteenRecord;
import com.anthotel.admin.dto.ReserveRecord;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:42
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
public interface OrderService {
    List<ReserveRecord> fetchLiveOrderList();

    ReserveRecord getLivenOrder(String id);

    List<CanteenRecord> fetchCanteenOrderList();

    CanteenRecord getCanteenOrder(String id);
}
