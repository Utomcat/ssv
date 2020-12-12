package com.ranyk.ssv.backup.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:CorsConfig
 * Description:跨域配置
 *
 * @author ranyi
 * @date 2020-12-13 0:06
 * Version: V1.0
 */
public class CorsConfig implements WebMvcConfigurer {

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
