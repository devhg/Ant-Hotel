package com.anthotel.mini.service;

import com.anthotel.mini.dto.ReserveOrder;
import com.anthotel.mini.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 20:19
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public int insertOrder(ReserveOrder order) {
        return orderMapper.insertOrder(order);
    }

    public List<ReserveOrder> getOrders(String openId) {
        return orderMapper.getOrders(openId);
    }

}
