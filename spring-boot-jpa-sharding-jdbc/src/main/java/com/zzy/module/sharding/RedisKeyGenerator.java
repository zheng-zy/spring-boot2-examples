package com.zzy.module.sharding;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import io.shardingjdbc.core.keygen.KeyGenerator;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/26.
 */
public class RedisKeyGenerator implements KeyGenerator {

    private RedisClient client = RedisClient.create(RedisURI.Builder.redis("192.168.97.57",6379).withTimeout(Duration.ofMillis(6000)).withPassword("666666").withDatabase(1).build());
    private GenericObjectPool<StatefulRedisConnection<String, String>> pool = ConnectionPoolSupport.createGenericObjectPool(() -> client.connect(), getGenericObjectPoolConfig());


    public RedisKeyGenerator() {
    }

    private GenericObjectPoolConfig getGenericObjectPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(8);
        config.setMinIdle(0);
        config.setMaxTotal(8);
        config.setMaxWaitMillis(-1);
        return config;
    }

    @Override
    public synchronized Number generateKey() {
        try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) {
            RedisAsyncCommands<String, String> commands = connection.async();
            RedisFuture<Long> future = commands.incr("id");
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return System.currentTimeMillis();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (!pool.isClosed()){
            pool.close();
        }
        super.finalize();
    }
}
