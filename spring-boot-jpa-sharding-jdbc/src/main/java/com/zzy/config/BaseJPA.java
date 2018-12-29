package com.zzy.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2017/12/25.
 */
public interface BaseJPA<T> extends CrudRepository<T, Long>, JpaRepository<T, Long>, JpaSpecificationExecutor<T>, Serializable {

}