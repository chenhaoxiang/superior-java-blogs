package com.huijava.superiorjavablogs;

import com.uifuture.superstarter.boot.EnableDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 加上EnableDoc以便启用XDOC在线HTML文档
 *
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@EnableDoc
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan("com.huijava.superiorjavablogs.mapper")
@EnableScheduling
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
