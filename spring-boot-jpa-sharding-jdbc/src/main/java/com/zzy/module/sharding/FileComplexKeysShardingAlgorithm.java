package com.zzy.module.sharding;

import com.alibaba.fastjson.JSON;
import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/27.
 */
@Slf4j
public class FileComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> tableNames, Collection<ShardingValue> shardingValues) {
        log.info("tableNames:{}", JSON.toJSONString(tableNames));
        log.info("shardingValues:{}", JSON.toJSONString(shardingValues));

        return null;
    }
}
