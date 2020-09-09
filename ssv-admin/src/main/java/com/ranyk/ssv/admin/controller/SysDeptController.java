package com.ranyk.ssv.admin.controller;


import com.ranyk.ssv.admin.entity.SysDept;
import com.ranyk.ssv.admin.service.SysDeptService;
import com.ranyk.ssv.core.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 机构控制器
 * @author Louis
 * @date Jan 13, 2019
 */
@RestController
@RequestMapping("/dept/")
@Api(tags = "机构管理请求处理器")
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	
	@PostMapping(value="save")
	@ApiOperation("新增或更新机构")
	public HttpResult save(@RequestBody SysDept record) {
		return HttpResult.ok(sysDeptService.save(record));
	}

	@PostMapping(value="delete")
	@ApiOperation("删除指定的机构,真删除")
	public HttpResult delete(@RequestBody List<SysDept> records) {
		return HttpResult.ok(sysDeptService.delete(records));
	}

	@GetMapping(value="findTree")
	@ApiOperation("按照树形结构查找机构信息")
	public HttpResult findTree() {
		return HttpResult.ok(sysDeptService.findTree());
	}

}
