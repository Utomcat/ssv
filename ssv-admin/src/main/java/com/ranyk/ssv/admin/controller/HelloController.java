package com.ranyk.ssv.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:HelloController
 * Description:Hello请求处理
 *
 * @author ranyi
 * @date 2020-09-06 20:19
 * Version: V1.0
 */
@Api(tags = "Hello请求处理类")
@RestController
@RequestMapping("/hello/")
public class HelloController {


    @GetMapping("world")
    @ApiOperation("world测试请求")
    public Object hello(){
        return "Hello World!";
    }

}
