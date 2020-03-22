package com.anthotel.mini;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @Author: Devhui
 * @Date: 2020/3/17 20:12
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.anthotel.mini"})
@MapperScan({"com.anthotel.mini.mapper"})
@EnableTransactionManagement
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源处理
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/")
                .addResourceLocations("classpath:/hospitalpay");
    }
}
