package com.ranyk.ssv.admin.dao;

import com.ranyk.ssv.admin.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName:SysUserDao
 * Description:user数据看操作接口
 *
 * @author ranyi
 * @date 2020-09-07 1:00
 * Version: V1.0
 */
@Repository
public interface SysUserDao {

    /**
     * 查询所有的用户
     *
     * @return 返回 list 集合
     */
    List<SysUser> selectAll();

}
