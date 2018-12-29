package com.zzy.db.repository;

import com.zzy.config.BaseJPA;
import com.zzy.db.entity.File;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
public interface FileRepository extends BaseJPA<File> {


    File findFirstByMd5(String md5);

}
