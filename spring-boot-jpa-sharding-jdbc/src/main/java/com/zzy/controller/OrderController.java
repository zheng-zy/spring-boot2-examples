package com.zzy.controller;

import com.google.common.collect.ImmutableBiMap;
import com.zzy.db.entity.Order;
import com.zzy.db.entity.OrderItem;
import com.zzy.db.repository.OrderItemRepository;
import com.zzy.db.repository.OrderRepository;
import com.zzy.util.QueryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

    @RequestMapping("addOrder")
    @ResponseBody
    public Map<String, Object> addOrder(@RequestBody Order order) {
        log.info("order:{}", order.toString());
        order = orderRepository.save(order);
        log.info("orderId:{}", order.getOrderId());
        return ImmutableBiMap.of("code", 0, "order", order);
    }

    @RequestMapping("getOrderPage")
    @ResponseBody
    public Map<String, Object> getOrderPage(@RequestParam long userId,
                                           @RequestParam int page, @RequestParam int pageSize,
                                           @RequestParam(value = "", required = false) String sortType,
                                           @RequestParam(value = "", required = false) String sortCol) {
        Pageable pageable = QueryUtil.buildPageRequest(page, pageSize, sortType, sortCol);
        Page<Order> orderPage = orderRepository.findAllByUserIdOrderByOrderIdAsc(userId, pageable);
        return ImmutableBiMap.of("code", 0, "data", orderPage);
    }

    @RequestMapping("addOrderItem")
    @ResponseBody
    public Map<String, Object> addOrderItem(@RequestBody OrderItem orderItem) {
        log.info("orderItem:{}", orderItem.toString());
        orderItem = orderItemRepository.save(orderItem);
        log.info("orderItemId:{}", orderItem.getOrderItemId());
        return ImmutableBiMap.of("code", 0, "data", orderItem);
    }

}
