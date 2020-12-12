package com.ranyk.ssv.admin.security.utils;

import com.ranyk.ssv.admin.security.authentication.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:SecurityUtils<br/>
 * Description: Security 工具类
 *
 * @author ranyi
 * @date 2020-12-10 16:41
 * Version: V1.0
 */
public class SecurityUtils {


    /**
     * 系统登录认证
     * @param request
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticationToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌并返回给客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    /**
     * 获取令牌进行认证
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request) {
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取系统中SecurityContext对象已保存的Authentication对象的username
     *
     * @return 系统内保存的 Authentication 对象中的 username
     */
    public static String getUserName() {
        String username = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 从认证对象中获取登录用户的用户名信息
     *
     * @param authentication Authentication 对象
     * @return 返回从传入 Authentication 对象中获取的 username
     */
    public static String getUserName(Authentication authentication) {
        String username = null;
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取服务器上下文(SecurityContext)中存放的Authentication认证对象
     *
     * @return Authentication 对象
     */
    public static Authentication getAuthentication() {
        // 1. 获取SecurityContext 对象
        SecurityContext context = SecurityContextHolder.getContext();
        // 2. 判断是否存在 SecurityContext 对象
        if(context == null) {
            // 2.1. 不存在则直接返回 null 对象
            return null;
        }
        // 3. 存在则获取 SecurityContext 中存放的 Authentication 对象
        return context.getAuthentication();
    }
}
