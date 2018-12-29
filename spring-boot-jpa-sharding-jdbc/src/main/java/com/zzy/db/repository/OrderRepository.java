package com.zzy.db.repository;

import com.zzy.config.BaseJPA;
import com.zzy.db.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
public interface OrderRepository extends BaseJPA<Order> {

    Page<Order> findAllByUserIdOrderByOrderIdAsc(long userId, Pageable pageable);


}
