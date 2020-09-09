package com.ranyk.ssv.admin.dao;

import com.ranyk.ssv.admin.entity.SysUser;
import org.apache.ibatis.annotations.Param;
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
public interface SysUserMapper {

    /**
     * 查询所有的用户
     *
     * @return 返回 list 集合
     */
    List<SysUser> selectAll();


    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> findPage();

    SysUser findByName(@Param(value = "name") String name);

    List<SysUser> findPageByName(@Param(value = "name") String name);

    List<SysUser> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);

}
