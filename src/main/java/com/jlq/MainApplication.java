package com.jlq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/24 9:50
 */

@SpringBootApplication
@MapperScan(basePackages = "com.jlq.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}


