package com.ranyk.ssv.admin.service;


import com.ranyk.ssv.admin.entity.SysDict;
import com.ranyk.ssv.core.service.CurdService;

import java.util.List;


/**
 * 字典管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysDictService extends CurdService<SysDict> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysDict> findByLable(String lable);
}
