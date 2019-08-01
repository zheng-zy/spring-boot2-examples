package com.zzy.entity;

import lombok.Data;

import java.util.Date;


/**
 * <p>歌曲文件</p>
 * Created by @author zhezhiyong@163.com on 2018/12/5.
 */
@Data
public class SongFile {

    private Integer id;
    private Integer fileId;
    private String fileName;
    private String fileMd5;
    private Integer fileType;
    private Integer requestCount;
    private String companyCode;
    private Date createTime;
    private Date updateTime;

}
