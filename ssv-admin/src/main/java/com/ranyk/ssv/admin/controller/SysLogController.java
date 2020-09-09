package com.ranyk.ssv.admin.controller;


import com.ranyk.ssv.admin.entity.SysLog;
import com.ranyk.ssv.admin.service.SysLogService;
import com.ranyk.ssv.core.http.HttpResult;
import com.ranyk.ssv.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 操作日志控制器
 * @author Louis
 * @date Jan 13, 2019
 */
@RestController
@RequestMapping("/log/")
@Api(tags = "日志请求处理器")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;

	@PostMapping(value="findPage")
	@ApiOperation("分页查询日志记录")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysLogService.findPage(pageRequest));
	}
	
	@PostMapping(value="delete")
	@ApiOperation("删除指定的日志记录")
	public HttpResult delete(@RequestBody List<SysLog> records) {
		return HttpResult.ok(sysLogService.delete(records));
	}
}
