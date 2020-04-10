package com.zzy.handler;

import com.alibaba.fastjson.JSON;
import com.zzy.entity.User;
import com.zzy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/10/11.
 */
@Component
@Slf4j
public class UserHandler {
    @Resource
    private UserService userService;

    public Mono<ServerResponse> Hi(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject("Hi , this is SpringWebFlux"));
    }

    public Mono<ServerResponse> getUserObj(ServerRequest request) {
        String userId = request.pathVariable("userId");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(userService.findUserById(Long.valueOf(userId))));
    }

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(Flux.just(userService.list()), List.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        String userId = request.pathVariable("userId");
        User user = userService.findUserById(Long.valueOf(userId));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "");
        map.put("data", user);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(map), Map.class);
    }

    /**
     * 参数示例
     * @param request 请求对象
     */
    public Mono<ServerResponse> getUserByJson(ServerRequest request) {
        MultiValueMap<String, String> params = request.queryParams();
        Mono<User> userMono = request.bodyToMono(User.class);
        userMono.subscribe(u -> log.info(JSON.toJSONString(u)));
        userMono.subscribe(u -> log.info(JSON.toJSONString(u)));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "");
        map.put("data", userMono);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(map), Map.class);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(userService.findUserById(1L)));
    }
}
