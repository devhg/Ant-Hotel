package com.anthotel.mini.service;

import com.anthotel.mini.dto.ReserveOrder;
import com.anthotel.mini.mapper.OrderMapper;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    public List<Map> getOrders(String openId) {
        return orderMapper.getOrders(openId);
    }

}
