package com.zzy.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/27.
 */
@Entity
@Table(name = "t_file")
@Data
public class File implements Serializable {
    private static final long serialVersionUID = -988806124902840613L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    private String md5;
    private String status;

}