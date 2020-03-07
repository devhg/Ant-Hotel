package com.anthotel.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


/**
 * @Author: Devhui
 * @Date: 2020/3/6 16:57
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

// 加入scanBasePackages 是为能够注入其他模块中的对象
@SpringBootApplication(scanBasePackages = {"com.anthotel.face", "com.anthotel.web"})
@MapperScan({"com.anthotel.web.mapper"})
@EnableTransactionManagement
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源处理
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/")
                .addResourceLocations("classpath:/hospitalpay");
    }
}
