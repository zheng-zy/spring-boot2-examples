package com.zzy.config;

import com.zzy.module.sharding.*;
import com.zzy.util.DataSourceUtil;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.ComplexShardingStrategyConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
@Configuration
public class DataSourceConfig {

    /**
     * 分表
     *
     * @return 数据源
     * @throws SQLException 数据库异常
     */
    @Bean
    public DataSource userShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        //  单库分表
        TableRuleConfiguration fileRule = new TableRuleConfiguration();
        fileRule.setLogicTable("t_file");
        fileRule.setActualDataNodes("ds0.t_file${0..1}");
        fileRule.setKeyGeneratorColumnName("file_id");
        fileRule.setKeyGeneratorClass(MySqlKeyGenerator.class.getName());
        fileRule.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("file_id",
                ModuloShardingDatabaseAlgorithm.class.getName()));
        fileRule.setTableShardingStrategyConfig(new ComplexShardingStrategyConfiguration("file_id,md5",
                FileComplexKeysShardingAlgorithm.class.getName()));

        //  单库分表
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_user");
        result.setActualDataNodes("ds0.t_user${0..2}");
        result.setKeyGeneratorColumnName("user_id");
        result.setKeyGeneratorClass(MySqlKeyGenerator.class.getName());
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",
                ModuloShardingDatabaseAlgorithm.class.getName(),
                ModuloShardingDatabaseAlgorithm.class.getName()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",
                ModuloShardingTableAlgorithm.class.getName(),
                ModuloShardingTableAlgorithm.class.getName()));


        TableRuleConfiguration result2 = new TableRuleConfiguration();
        result2.setLogicTable("t_order");
        result2.setActualDataNodes("ds${0..1}.t_order${[0,1]}");
        result2.setKeyGeneratorColumnName("order_id");
        result2.setKeyGeneratorClass(RedisKeyGenerator.class.getName());
        result2.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",
                ModuloShardingDatabaseAlgorithm.class.getName(),
                ModuloShardingDatabaseAlgorithm.class.getName()));
        result2.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",
                ModuloShardingTableAlgorithm.class.getName(),
                ModuloShardingTableAlgorithm.class.getName()));

        TableRuleConfiguration result3 = new TableRuleConfiguration();
        result3.setLogicTable("t_order_item");
        result3.setActualDataNodes("ds${0..1}.t_order_item${[0,1]}");
        result3.setKeyGeneratorColumnName("order_item_id");
        result3.setKeyGeneratorClass(RedisKeyGenerator.class.getName());
        result3.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",
                ModuloShardingDatabaseAlgorithm.class.getName(),
                ModuloShardingDatabaseAlgorithm.class.getName()));
        result3.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",
                ModuloShardingTableAlgorithm.class.getName(),
                ModuloShardingTableAlgorithm.class.getName()));

        shardingRuleConfig.getTableRuleConfigs().add(result);
        shardingRuleConfig.getTableRuleConfigs().add(result2);
        shardingRuleConfig.getTableRuleConfigs().add(result3);
        shardingRuleConfig.getTableRuleConfigs().add(fileRule);

        // 配置表关联
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");

        Map<String, DataSource> dbMap = new HashMap<>(2);
        dbMap.put("ds0", DataSourceUtil.dataSource("ds0"));
        dbMap.put("ds1", DataSourceUtil.dataSource("ds1"));

        Properties properties = new Properties();
        properties.put("sql.show", true);

        return ShardingDataSourceFactory.createDataSource(dbMap, shardingRuleConfig, new HashMap<>(1), properties);
    }


}
