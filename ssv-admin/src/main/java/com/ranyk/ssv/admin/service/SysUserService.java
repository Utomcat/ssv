package com.ranyk.ssv.admin.service;

import com.ranyk.ssv.admin.entity.SysUser;

import java.util.List;

/**
 * ClassName:SysUserService
 * Description:user用户业务接口
 *
 * @author ranyi
 * @date 2020-09-07 0:58
 * Version: V1.0
 */
public interface SysUserService {

    /**
     * 查询所有的用户信息
     * @return 返回查询结果的 List 集合
     */
    List<SysUser> getAllUser();

}
