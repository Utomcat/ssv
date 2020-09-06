package com.ranyk.ssv.admin.service.impl;

import com.ranyk.ssv.admin.dao.SysUserDao;
import com.ranyk.ssv.admin.entity.SysUser;
import com.ranyk.ssv.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:SysUserServiceImpl
 * Description:user用户业务接口实现类
 *
 * @author ranyi
 * @date 2020-09-07 0:59
 * Version: V1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public List<SysUser> getAllUser() {
        return sysUserDao.selectAll();
    }
}
