package com.ranyk.ssv.core.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.ranyk.ssv.core.constent.KaptCha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * ClassName:KaptChaConfig<br/>
 * Description:验证码配置类,用于配置验证码图片属性
 *
 * @author ranyi
 * @date 2020-12-03 19:41
 * Version: V1.0
 */
@Configuration
public class KaptChaConfig {

    /**
     * 创建验证码
     *
     * @return DefaultKaptcha 实例对象
     */
    @Bean
    public DefaultKaptcha producer() {
        //配置验证码属性
        Properties properties = new Properties();
        properties.put(KaptCha.BORDER.getKey(),KaptCha.BORDER.getValue());
        properties.put(KaptCha.TEXT_PRODUCER_FONT_COLOR.getKey(),KaptCha.TEXT_PRODUCER_FONT_COLOR.getValue());
        properties.put(KaptCha.TEXT_PRODUCER_CHAR_SPACE.getKey(),KaptCha.TEXT_PRODUCER_CHAR_SPACE.getValue());
        //创建验证码配置对象
        Config config = new Config(properties);
        //创建验证码对象
        DefaultKaptcha kaptCha = new DefaultKaptcha();
        //设置验证码配置
        kaptCha.setConfig(config);
        //返回验证码对象
        return kaptCha;
    }

}
