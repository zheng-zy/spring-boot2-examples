package com.zzy.db.repository;

import com.zzy.config.BaseJPA;
import com.zzy.db.entity.User;

import java.util.List;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
public interface UserRepository extends BaseJPA<User> {

    List<User> findAllByUserIdBetweenOrderByUserIdAsc(long start, long end);

}
