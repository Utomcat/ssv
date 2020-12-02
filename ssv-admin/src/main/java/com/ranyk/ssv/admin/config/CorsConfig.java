package com.ranyk.ssv.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:CorsConfig<br/>
 * Description:跨域访问配置类
 *
 * @author ranyi
 * @date 2019-12-19 11:47
 * Version: V1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 添加跨域访问的请求参数
     * @param corsRegistry Cors注册对象
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                .maxAge(168000)
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
