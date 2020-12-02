package com.ranyk.ssv.admin.controller;

import com.ranyk.ssv.admin.constent.SysConstants;
import com.ranyk.ssv.admin.entity.SysUser;
import com.ranyk.ssv.admin.service.SysUserService;
import com.ranyk.ssv.admin.util.PasswordUtils;
import com.ranyk.ssv.common.utils.FileUtils;
import com.ranyk.ssv.core.http.HttpResult;
import com.ranyk.ssv.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

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
    @ApiOperation(value = "查询所有用户的信息",notes = "查询sys_user表中到的数据信息")
    public List<SysUser> getAllUser(){
        return sysUserService.getAllUser();
    }

    @PostMapping(value="save")
    @ApiOperation(value = "保存用户信息",notes = "对用户信息的新增、修改操作")
    public HttpResult save(@RequestBody SysUser record) {
        SysUser user = sysUserService.findById(record.getId());
        if(user != null) {
            if(SysConstants.ADMIN.getValue().equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        if(record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if(user == null) {
                // 新增用户
                if(sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已存在!");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                // 修改用户, 且修改了密码
                if(!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return HttpResult.ok(sysUserService.save(record));
    }

    @PostMapping(value="delete")
    @ApiOperation(value = "删除用户信息",notes = "根据id delete sys_user 表中的数据")
    public HttpResult delete(@RequestBody List<SysUser> records) {
        for(SysUser record:records) {
            SysUser sysUser = sysUserService.findById(record.getId());
            if(sysUser != null && SysConstants.ADMIN.getValue().equalsIgnoreCase(sysUser.getName())) {
                return HttpResult.error("超级管理员不允许删除!");
            }
        }
        return HttpResult.ok(sysUserService.delete(records));
    }

    @GetMapping(value="findByName")
    @ApiOperation(value = "通过用户名查询用户信息")
    public HttpResult findByName(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findByName(name));
    }

    @GetMapping(value="findPermissions")
    @ApiOperation(value = "查询用户权限")
    public HttpResult findPermissions(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findPermissions(name));
    }

    @GetMapping(value="findUserRoles")
    @ApiOperation(value = "查询用户角色",notes = "根据用户的ID查询该用户的角色信息")
    public HttpResult findUserRoles(@RequestParam Long userId) {
        return HttpResult.ok(sysUserService.findUserRoles(userId));
    }

    @PostMapping(value="findPage")
    @ApiOperation(value = "分页查询用户")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }

    @PostMapping(value="exportExcelUser")
    @ApiOperation(value = "将用户的信息导出成Excel")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) {
        File file = sysUserService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res, file, file.getName());
    }

}
