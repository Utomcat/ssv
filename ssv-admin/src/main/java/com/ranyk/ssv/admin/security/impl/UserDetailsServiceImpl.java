package com.ranyk.ssv.admin.security.impl;

import com.ranyk.ssv.admin.entity.SysUser;
import com.ranyk.ssv.admin.security.user.JwtUserDetails;
import com.ranyk.ssv.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName:UserDetailsServiceImpl
 * Description:用户详情业务实现类
 *
 * @author ranyi
 * @date 2020-12-10 17:33
 * Version: V1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;


    /**
     * 登录时验证的用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 根据用户名查询用户对象
        SysUser user = userService.findByName(username);
        // 2. 判断用户对象是否存在
        if (user == null) {
            throw new UsernameNotFoundException("根据指定的用户名 " + username + " 未找到对应的用户信息!");
        }
        // 3. 通过用户名来获取对应的用户权限列表
        Set<String> permissions = userService.findPermissions(username);
        // 4. 给当前用户进行授权
        List<GrantedAuthorityImpl> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        // 5. 返回一个新建的Jwt的用户详情对象
        return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}
