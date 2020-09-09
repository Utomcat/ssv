package com.ranyk.ssv.admin.service;

import com.ranyk.ssv.admin.entity.SysUser;
import com.ranyk.ssv.admin.entity.SysUserRole;
import com.ranyk.ssv.core.page.PageRequest;
import com.ranyk.ssv.core.service.CurdService;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * ClassName:SysUserService
 * Description:user用户业务接口
 *
 * @author ranyi
 * @date 2020-09-07 0:58
 * Version: V1.0
 */
public interface SysUserService extends CurdService<SysUser>  {

    /**
     * 查询所有的用户信息
     * @return 返回查询结果的 List 集合
     */
    List<SysUser> getAllUser();

    SysUser findByName(String username);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

    /**
     * 查找用户的角色集合
     * @param userName
     * @return
     */
    List<SysUserRole> findUserRoles(Long userId);

    /**
     * 生成用户信息Excel文件
     * @param pageRequest 要导出的分页查询参数
     * @return
     */
    File createUserExcelFile(PageRequest pageRequest);

}
