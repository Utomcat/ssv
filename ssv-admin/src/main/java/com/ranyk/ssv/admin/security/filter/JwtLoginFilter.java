package com.ranyk.ssv.admin.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.ranyk.ssv.admin.security.authentication.JwtAuthenticationToken;
import com.ranyk.ssv.admin.security.utils.JwtTokenUtils;
import com.ranyk.ssv.admin.util.HttpUtils;
import com.ranyk.ssv.common.utils.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * ClassName:JwtLoginFilter<br/>
 * Description: Jwt 登录过滤器
 *
 * @author ranyi
 * @date 2020-12-04 0:07
 * Version: V1.0
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * JWT登录过滤器构造方法,设置当前环境的认证管理器
     *
     * @param authenticationManager 认证管理器
     */
    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        //设置认证管理器
        setAuthenticationManager(authenticationManager);
    }


    /**
     * 覆写拦截器需要进行的拦截操作
     *
     * @param req   ServletRequest 对象
     * @param res   ServletResponse 对象
     * @param chain FilterChain 对象
     * @throws IOException      IO异常
     * @throws ServletException Servlet 异常
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //POST 请求 /sys_user/login 登录时拦截,由此方法触发执行登录认证流程,可在此处覆写整个登录认证逻辑
        super.doFilter(req, res, chain);
    }

    /**
     * 覆写父类的认证方法,修改用户名、密码的获取方式,
     *
     * @param request  HTTPServletRequest 请求对象
     * @param response HTTPServletResponse 响应对象
     * @return 返回 Authentication 认证对象
     * @throws AuthenticationException Authentication 异常
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 1. 从请求中获取登录的有关参数(用户名、密码等参数)
        JSONObject requestObj = getParameter(request);
        // 2. 从获取到上一步中获取的结果中取用户名、密码等参数值
        String username = requestObj.getString("username");
        String password = requestObj.getString("password");
        // 3. 用户名如果为空则用空字符串替换
        if (StringUtils.isBlank(username)) {
            username = "";
        }
        // 4. 密码如果为空则用空字符串替换
        if (StringUtils.isBlank(password)) {
            password = "";
        }
        // 5. 用户名去空格
        username = username.trim();
        // 6. 认证登录的用户名和密码
        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password);
        // 7. 将登录认证的结果保存
        setDetails(request,token);
        // 8. 将登录信息的认证结果保存到后台的应用 Content 中
        return this.getAuthenticationManager().authenticate(token);
    }

    /**
     * 覆写父类的认证成功的操作,移除后台跳转,添加生成的令牌并返回给客户端
     *
     * @param request HTTPServletRequest 对象
     * @param response HTTPServletResponse 对象
     * @param chain FilterChain 对象
     * @param authResult Authentication 对象
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 1. 将登录的认证信息存储到 Context 中
        SecurityContextHolder.getContext().setAuthentication(authResult);
        // 2. 记住我服务勾选
        getRememberMeServices().loginSuccess(request,response,authResult);
        // 3. 出发时间监听器,以确定是否发布事件
        if (this.eventPublisher != null){
            // 3.1. 发布事件 交互式身份验证成功事件
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult,this.getClass()));
        }
        // 4. 生成认证后的 Token 令牌
        JwtAuthenticationToken token = new JwtAuthenticationToken(null, null, JwtTokenUtils.generateToken(authResult));
        // 5. 将生成的 Token 令牌写进响应对象中,以便后续访问中的请求中携带此令牌进行请求
        HttpUtils.write(response,token);
    }

    /**
     * 从请求中获取请求的参数对象
     *
     * @param request HttpServletRequest 对象
     * @return 返回获取到的
     */
    private JSONObject getParameter(HttpServletRequest request) {

        StringBuilder requestStr = new StringBuilder();
        Reader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            // 获取指定字符集的输入流
            inputStreamReader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
            // 从输入流中获取输入字符流
            reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                requestStr.append(line);
                line = reader.readLine();
            }
            logger.error(requestStr.length() > 0 ? "从请求中获取参数完成." : "请求中的参数为空.");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
                logger.error("流已成功关闭.");
            }catch (IOException e){
                logger.error("关闭流时出现异常,异常信息为: " + e.getMessage() + " .");
            }
        }

        return JSONObject.parseObject(requestStr.toString());
    }

}
