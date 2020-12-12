package com.ranyk.ssv.admin.security.utils;

import com.ranyk.ssv.admin.security.user.JwtUserDetails;
import com.ranyk.ssv.admin.util.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ClassName:JwtAuthenticationProvider<br/>
 * Description: JWT 方式认证提供者类
 *
 * @author ranyi
 * @date 2020-12-03 23:21
 * Version: V1.0
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {

    /**
     * JWT 方式认证提供构造方法,用来设置认证的用户详情对象和设置密码的编码方式
     *
     * @param userDetailsService 用户详情Service对象
     */
    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        //调用设置用户详情方法
        setUserDetailsService(userDetailsService);
        //调用设置密码编码方式
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 覆写认证的方法
     *
     * @param authentication 需要认证对象
     * @return 认证后的对象
     * @throws AuthenticationException 抛出认证异常
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //登录认证逻辑的覆写
        return super.authenticate(authentication);
    }

    /**
     * 覆写其他身份认证检查方法方法
     *
     * @param userDetails 用户详情对象
     * @param authentication 需要认证的对象
     * @throws AuthenticationException 抛出认证异常
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //除登录逻辑外的其他逻辑验证,如: 密码验证等
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        String salt = ((JwtUserDetails) userDetails).getSalt();
        // 覆写密码验证逻辑
        if (!new PasswordEncoder(salt).matches(userDetails.getPassword(), presentedPassword)) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }
}
