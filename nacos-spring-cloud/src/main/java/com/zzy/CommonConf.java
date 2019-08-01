package com.zzy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/4/9.
 */
@RefreshScope
@Component
@Data
public class CommonConf {
    @Value("${blackCompany:03311}")
    private String blackCompany;
}
