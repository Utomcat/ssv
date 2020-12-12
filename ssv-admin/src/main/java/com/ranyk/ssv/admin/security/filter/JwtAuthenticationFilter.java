package com.ranyk.ssv.admin.security.filter;

import com.ranyk.ssv.admin.security.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:JwtAuthenticationFilter<br/>
 * Description: JWT 授权认证过滤器类
 *
 * @author ranyi
 * @date 2020-12-04 12:26
 * Version: V1.0
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityUtils.checkAuthentication(request);
        chain.doFilter(request,response);
    }
}
