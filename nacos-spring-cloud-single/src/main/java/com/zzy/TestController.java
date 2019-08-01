package com.zzy;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/4/9.
 */
@RestController
@RefreshScope
public class TestController {

    @Resource
    private CommonConf commonConf;

    @Value("${connectTimeoutInMills:5000}")
    private int connectTimeoutInMills;

    @Value("${defaultSegmentShow:5000}")
    private String defaultSegmentShow;

    @Value("${blackComapnyasd:05780}")
    private String blackComapnyasd;

    /**
     * http://localhost:8080/config/get
     */
    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public Object get() {
        return blackComapnyasd;
    }

    @RequestMapping("test")
    public String getConfig(){
        return JSON.toJSONString(commonConf);
    }


}
