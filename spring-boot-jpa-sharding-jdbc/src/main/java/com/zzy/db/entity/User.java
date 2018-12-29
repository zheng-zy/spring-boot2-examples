package com.zzy.db.entity;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7767196423260001354L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String status;

}