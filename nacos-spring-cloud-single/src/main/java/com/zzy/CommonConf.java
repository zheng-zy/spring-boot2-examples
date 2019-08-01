package com.zzy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/11/28.
 */
@RefreshScope
@Component
@Data
public class CommonConf {
    @Value("${showRegion:420000,430000,350000}")
    private String showRegion;
    @Value("${pilotRegion:420000,430000,350000,440000}")
    private String pilotRegion;
    @Value("${defaultSegmentShow:0}")
    private Integer defaultSegmentShow = 0;
    @Value("${hbase.zks:192.168.97.211,192.168.97.212,192.168.97.213}")
    private String zks;
    @Value("${kafka.brokerList:192.97.211:9092,192.168.97.212:9092,192.168.97.213:9092}")
    private String brokerList;
    @Value("${kafka.topic:VodAdvertisement}")
    private String topic;
    @Value("${kafka.userSegmentTopic:UserSegment}")
    private String userSegmentTopic;
    @Value("${kafka.partition:0}")
    private String partition;
    @Value("${kafka.isError:1}")
    private Integer isError;
    @Value("${grayCompany:05780}")
    private String grayCompany;
    @Value("${blackComapnyasd:05780}")
    private String blackComapnyasd;


}


