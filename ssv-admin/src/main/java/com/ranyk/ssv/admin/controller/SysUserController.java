package com.ranyk.ssv.admin.controller;

import com.ranyk.ssv.admin.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:SysUserController
 * Description:用户请求处理类
 *
 * @author ranyi
 * @date 2020-09-07 0:54
 * Version: V1.0
 */
@RestController
@Api(tags = "user用户请求处理类")
@RequestMapping(value = "/user/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("getAllUser")
    @ApiOperation(value = "查询所有用户的信息",notes = "")
    public Object getAllUser(){
        return sysUserService.getAllUser();
    }

}
