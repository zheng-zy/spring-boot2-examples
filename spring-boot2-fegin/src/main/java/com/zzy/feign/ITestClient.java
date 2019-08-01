package com.zzy.feign;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/4/26.
 */
@FeignClient(name = "kmgate")
public interface ITestClient {

    @RequestMapping(method = RequestMethod.POST,
            value = "/getSongScore",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    JSONObject getSongScore(F1000Mapper mapper);

//    @Slf4j
//    @Component
//    class SolrFallback implements ITestClient {
//
//        @Override
//        public JSONObject getSongScore(F1000Mapper mapper) {
//            JSONObject object = new JSONObject();
//            object.put("code", -1);
//            return object;
//        }
//    }

}
