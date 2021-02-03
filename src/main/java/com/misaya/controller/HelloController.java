package com.misaya.controller;

import com.misaya.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: swagger-demo
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-02-02 10:53
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    //只要我们的接口中，返回值中存在实体类，他就会被扫描到Swagger中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

    @ApiOperation("家岐的接口")
    @PostMapping("/zero")
    @ResponseBody
    public String kuang(@ApiParam("这个名字会被返回")String username){
        return username;
    }
}
