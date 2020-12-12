package com.ranyk.ssv.admin.config;

import com.ranyk.ssv.admin.security.filter.JwtAuthenticationFilter;
import com.ranyk.ssv.admin.security.utils.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * ClassName:WebSecurityConfig<br/>
 * Description:Spring Security 配置类
 *
 * @author ranyi
 * @date 2020-12-03 23:11
 * Version: V1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 配置自定义的身份认证 Provider
     *
     * @param auth 身份验证管理生成器对象
     * @throws Exception 再添加认证Provider时发生错误,抛出 Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 添加自定义的身份验证 Provider
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    /**
     * 配置有关的访问规则
     *
     * @param http HttpSecurity 对象
     * @throws Exception 发生错误时,抛出 Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1. 配置请求禁用csrf
        http.cors().and().csrf().disable()
                //2. 认证请求
                .authorizeRequests()
                //2.1. 添加认证规则 -- 跨域预检请求规则
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //2.2. 添加认证规则 -- 登录请求规则
                .antMatchers("/sys_login/login").permitAll()
                .antMatchers("/sys_login/captcha.jpg").permitAll()
                //2.3. 添加认证规则 -- swagger请求规则
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                //3. 其他请求认证配置 -- 均需先进行认证授权
                .anyRequest().authenticated()
                //4. 退出处理器
                .and().logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        //5. 在认证请求之前添加过滤器 -- 添加JWT登录认证的过滤器
        /**
         *
         * http.addFilterBefore(new JwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
         */

        //6. 在认证请求之前添加过滤器 -- 添加JWT授权认证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 生成认证管理器对对象,并注册成 Bean
     *
     * @return 生成的认证管理器对象
     * @throws Exception 发生错误时,抛出 Exception 异常
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
