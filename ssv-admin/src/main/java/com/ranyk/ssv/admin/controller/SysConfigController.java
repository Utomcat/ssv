package com.ranyk.ssv.admin.controller;


import com.ranyk.ssv.admin.entity.SysConfig;
import com.ranyk.ssv.admin.service.SysConfigService;
import com.ranyk.ssv.core.http.HttpResult;
import com.ranyk.ssv.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Title: SysConfigController.java
 *
 * @author: ranyk
 * @Description: config请求分发器
 * @date：2019年12月16日 上午12:41:27
 * @version 1.0
 */
@RestController
@RequestMapping("/config/")
@Api(tags = "系统配置请求处理")
public class SysConfigController {

	@Autowired
	private SysConfigService sysConfigService;
	
	@PostMapping(value="save")
	@ApiOperation("保存配置")
	public HttpResult save(@RequestBody SysConfig record) {
		return HttpResult.ok(sysConfigService.save(record));
	}

	@PostMapping(value="delete")
	@ApiOperation("删除配置")
	public HttpResult delete(@RequestBody List<SysConfig> records) {
		return HttpResult.ok(sysConfigService.delete(records));
	}

	@PostMapping(value="findPage")
	@ApiOperation("分页查找配置")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysConfigService.findPage(pageRequest));
	}
	
	@GetMapping(value="findByLabel")
	@ApiOperation("通过标签查找配置")
	public HttpResult findByLabel(@RequestParam String lable) {
		return HttpResult.ok(sysConfigService.findByLable(lable));
	}
}
