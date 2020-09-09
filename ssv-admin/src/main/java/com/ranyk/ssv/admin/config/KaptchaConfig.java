package com.ranyk.ssv.admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * ClassName:KaptchaConfig
 * Description:验证码配置类
 *
 * 用于配置验证码图片的相关设置
 *
 * @author ranyi
 * @date 2019-12-27 21:28
 * Version: V1.0
 */
@Configuration
public class KaptchaConfig {

    /**
     *
     * @return
     */
    @Bean
    public DefaultKaptcha producer(){

        Properties properties = new Properties();
        properties.put("kaptcha.border","no");
        properties.put("kaptcha.textproducer.font.color","black");
        properties.put("kaptcha.textproducer.char.space","5");

        Config config = new Config(properties);

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
