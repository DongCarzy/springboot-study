package com.dxp.netty.service;

import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 处理客户端注册业务.
 * <p>
 * 比对账号密码等信息是不是对的.
 *
 * @author carzy
 * @date 2020/7/4
 */
@Service
public class RegisterService {

    @Async
    public CompletableFuture<Boolean> register(String content) {
        Map<String, Object> map = new GsonJsonParser().parseMap(content);
        String username = (String) map.get("username");
        String pwd = (String) map.get("password");
        return CompletableFuture.completedFuture(check(username, pwd));
    }

    private boolean check(String username, String pwd) {
        //todo.. 数据库中查询并校验
        return true;
    }

}
