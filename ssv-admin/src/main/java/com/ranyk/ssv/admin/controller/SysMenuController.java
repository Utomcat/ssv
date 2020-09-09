package com.ranyk.ssv.admin.controller;


import com.ranyk.ssv.admin.entity.SysMenu;
import com.ranyk.ssv.admin.service.SysMenuService;
import com.ranyk.ssv.core.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单控制器
 * @author Louis
 * @date Jan 13, 2019
 */
@RestController
@RequestMapping("/menu/")
@Api(tags = "系统菜单请求处理器")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	@PostMapping(value="save")
	@ApiOperation("新增或更新菜单数据")
	public HttpResult save(@RequestBody SysMenu record) {
		return HttpResult.ok(sysMenuService.save(record));
	}

	@PostMapping(value="delete")
	@ApiOperation("删除指定的菜单数据")
	public HttpResult delete(@RequestBody List<SysMenu> records) {
		return HttpResult.ok(sysMenuService.delete(records));
	}

	@GetMapping(value="findNavTree")
	@ApiOperation("查询指定用户名的用户可用的菜单树")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(sysMenuService.findTree(userName, 1));
	}
	
	@ApiOperation("查询所有的菜单树")
	@GetMapping(value="findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(sysMenuService.findTree(null, 0));
	}
}
