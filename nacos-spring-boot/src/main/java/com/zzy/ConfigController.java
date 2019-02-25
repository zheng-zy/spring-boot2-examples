package com.zzy;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/2/25.
 */
@RestController
@RequestMapping("config")
public class ConfigController {

    @NacosValue(value = "${admin.name:xiaoming}", autoRefreshed = true)
    private String name;
    @NacosValue(value = "${admin.age:18}", autoRefreshed = true)
    private Integer age;

    @RequestMapping("getAdminInfo")
    public Map<String, Object> getAdminInfo() {
        return ImmutableMap.of("name", name, "age", age);
    }

}
