package com.ranyk.ssv.admin.controller;


import com.ranyk.ssv.admin.entity.SysDict;
import com.ranyk.ssv.admin.service.SysDictService;
import com.ranyk.ssv.core.http.HttpResult;
import com.ranyk.ssv.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 字典控制器
 * @author Louis
 * @date Jan 13, 2019
 */
@RestController
@RequestMapping("/dict/")
@Api(tags = "字典请求处理器")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;
	
	@PostMapping(value="save")
	@ApiOperation("新增或更新条字典记录")
	public HttpResult save(@RequestBody SysDict record) {
		return HttpResult.ok(sysDictService.save(record));
	}

	@PostMapping(value="delete")
	@ApiOperation("删除一个字典记录")
	public HttpResult delete(@RequestBody List<SysDict> records) {
		return HttpResult.ok(sysDictService.delete(records));
	}

	@PostMapping(value="findPage")
	@ApiOperation("分页查询字典记录")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysDictService.findPage(pageRequest));
	}
	
	@GetMapping(value="findByLabel")
	@ApiOperation("根据字典标签查询数据记录")
	public HttpResult findByLabel(@RequestParam String lable) {
		return HttpResult.ok(sysDictService.findByLable(lable));
	}
}
