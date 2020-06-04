package com.anthotel.admin.service.impl;

import com.anthotel.admin.dto.CanteenRecord;
import com.anthotel.admin.dto.ReserveRecord;
import com.anthotel.admin.dto.UserSearch;
import com.anthotel.admin.mapper.OrderMapper;
import com.anthotel.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/16 19:42
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<ReserveRecord> fetchLiveOrderList() {
        return orderMapper.fetchLiveOrderList();
    }

    @Override
    public ReserveRecord getLivenOrder(String id) {
        return orderMapper.getLivenOrder(id);
    }

    @Override
    public List<CanteenRecord> fetchCanteenOrderList() {
        return orderMapper.fetchCanteenOrderList();
    }

    @Override
    public CanteenRecord getCanteenOrder(String id) {
        return orderMapper.getCanteenOrder(id);
    }

    @Override
    public List<ReserveRecord> liveSearch(UserSearch userSearch) {
        return orderMapper.liveSearch(userSearch);
    }

    @Override
    public List<CanteenRecord> foodSearch(UserSearch userSearch) {
        return orderMapper.foodSearch(userSearch);
    }
}
