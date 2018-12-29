package com.zzy.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
@Entity
@Table(name = "t_order_item")
@Data
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -8280261039695805189L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private Long orderId;
    private Long userId;
    private String status;

}
