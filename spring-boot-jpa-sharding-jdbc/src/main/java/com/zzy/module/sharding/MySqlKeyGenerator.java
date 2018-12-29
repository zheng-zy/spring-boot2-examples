package com.zzy.module.sharding;

import com.zzy.util.DataSourceUtil;
import io.shardingjdbc.core.keygen.KeyGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/26.
 */
public class MySqlKeyGenerator implements KeyGenerator {

    private DataSource dataSource;
    private String sql = "insert into t_generate_key()values();";

    public MySqlKeyGenerator() {
        this.dataSource = DataSourceUtil.dataSource("ds0");
    }

    @Override
    public synchronized Number generateKey() {
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            return System.currentTimeMillis();
        }
        return System.currentTimeMillis();
    }
}
