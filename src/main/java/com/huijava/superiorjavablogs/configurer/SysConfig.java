/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.configurer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujn
 * @version SysConfig.java, v 0.1 2018-07-20 16:27
 */
@Configuration
@Data
public class SysConfig {
    /*********************系统当前运行环境********************/
    @Value("${application.env}")
    private String applicationEnv;

}
