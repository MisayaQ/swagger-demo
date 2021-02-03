package com.misaya.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @program: swagger-demo
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-02-02 10:56
 **/
@Configuration
@EnableSwagger2//开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    //配置了swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment) {
        //如何动态配置当项目处于 test、dev 环境时显示 swagger，处于 prod 时不显示？

        // 1. 设置要显示swagger的环境
        Profiles profiles = Profiles.of("dev", "test");
        // 2. 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("JiaQ7") // 配置分组
                .enable(flag)
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors配置如何扫描接口
                    //basePackage:指定要扫描的包
                    //any():扫描全部
                    //none():不扫描
                    //withClassAnnotation():扫描类上的注解
                .apis(RequestHandlerSelectors.basePackage("com.misaya.swagger.controller"))
                //paths() 过滤路径
//                .paths(PathSelectors.ant("/misaya/**"))
                .build();
    }

    //配置swagger信息=apiInfo
    private ApiInfo apiInfo() {
        Contact contact = new Contact("刘家岐", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "https://www.layui.com/admin/", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
