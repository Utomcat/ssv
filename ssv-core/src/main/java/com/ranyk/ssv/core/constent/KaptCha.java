package com.ranyk.ssv.core.constent;

import com.ranyk.ssv.common.constent.BaseEnumConstantInterfaceVO;
import lombok.AllArgsConstructor;

/**
 * ClassName:KaptCha
 * Description:验证码配置属性
 *
 * @author ranyi
 * @date 2020-12-03 19:47
 * Version: V1.0
 */
@AllArgsConstructor
public enum KaptCha implements BaseEnumConstantInterfaceVO {
    /**
     * 验证码边框属性设置
     */
    BORDER("kaptcha.border","no"),
    /**
     * 验证码字体颜色属性设置
     */
    TEXT_PRODUCER_FONT_COLOR("kaptcha.textproducer.font.color","black"),
    /**
     * 验证码字符个数属性设置
     */
    TEXT_PRODUCER_CHAR_SPACE("kaptcha.textproducer.char.space","5");

    private String key;
    private String value;


    /**
     * 获取指定的常量值
     *
     * @return 返回给定类型的常量值
     */
    @Override
    public Object getValue() {
        return this.value;
    }

    /**
     * 设置指定的常量值
     *
     * @param value 需设置的值
     */
    @Override
    public void setValue(Object value) {
        this.value = String.valueOf(value);
    }

    /**
     * 获取验证码图片的属性key
     *
     * @return 返回对应的key值
     */
    public String getKey(){
        return this.key;
    }

    /**
     * 设置验证码图片的属性key
     *
     * @param key 设置值
     */
    public void setKey(String key){
        this.key = key;
    }

}
