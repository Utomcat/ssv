package com.ranyk.ssv.admin.controller;


import com.ranyk.ssv.admin.constent.SysConstants;
import com.ranyk.ssv.admin.dao.SysRoleMapper;
import com.ranyk.ssv.admin.entity.SysRole;
import com.ranyk.ssv.admin.entity.SysRoleMenu;
import com.ranyk.ssv.admin.service.SysRoleService;
import com.ranyk.ssv.core.http.HttpResult;
import com.ranyk.ssv.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色控制器
 * @author Louis
 * @date Jan 13, 2019
 */
@RestController
@RequestMapping("/role/")
@Api(tags = "用户角色请求处理器")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@PostMapping(value="save")
	@ApiOperation("新增或更新系统的角色信息")
	public HttpResult save(@RequestBody SysRole record) {
		SysRole role = sysRoleService.findById(record.getId());
		if(role != null) {
			if(SysConstants.ADMIN.getValue().equalsIgnoreCase(role.getName())) {
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		// 新增角色
		if((record.getId() == null || record.getId() ==0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
			return HttpResult.error("角色名已存在!");
		}
		return HttpResult.ok(sysRoleService.save(record));
	}

	@PostMapping(value="delete")
	@ApiOperation("删除指定的角色信息,真实删除")
	public HttpResult delete(@RequestBody List<SysRole> records) {
		return HttpResult.ok(sysRoleService.delete(records));
	}

	@PostMapping(value="findPage")
	@ApiOperation("分页查询系统的角色信息")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysRoleService.findPage(pageRequest));
	}
	
	@GetMapping(value="findAll")
	@ApiOperation("查询所有系统内的角色信息")
	public HttpResult findAll() {
		return HttpResult.ok(sysRoleService.findAll());
	}
	
	@GetMapping(value="findRoleMenus")
	@ApiOperation("查找对应角色的功能菜单列表")
	public HttpResult findRoleMenus(@RequestParam Long roleId) {
		return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
	}
	
	@PostMapping(value="saveRoleMenus")
	@ApiOperation("新增或更新指定角色的功能菜单")
	public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
		for(SysRoleMenu record:records) {
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
			if(SysConstants.ADMIN.getValue().equalsIgnoreCase(sysRole.getName())) {
				// 如果是超级管理员，不允许修改
				return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}
		return HttpResult.ok(sysRoleService.saveRoleMenus(records));
	}
}
