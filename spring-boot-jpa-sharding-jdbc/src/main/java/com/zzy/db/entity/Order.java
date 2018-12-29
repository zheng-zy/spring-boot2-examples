package com.zzy.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
@Entity
@Table(name = "t_order")
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 2282070645563320702L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long userId;
    private String status;

}
