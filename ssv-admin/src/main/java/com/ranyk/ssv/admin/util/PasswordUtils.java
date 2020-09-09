package com.ranyk.ssv.admin.util;

import java.util.UUID;

/**
 * ClassName:PasswordUtils
 * Description:密码工具类
 *
 * @author ranyi
 * @date 2019-12-19 11:57
 * Version: V1.0
 */
public class PasswordUtils {

    /**
     * 匹配密码
     * @param salt 密码盐
     * @param rawPass 明文密码 -- 未加密的密码字符串
     * @param encPass 密文密码 -- 加密后的密码字符串
     * @return 返回配匹配结果
     */
    public static boolean matches(String salt,String rawPass,String encPass){
        return new PasswordEncoder(salt).matches(encPass,rawPass);
    }

    /**
     * 对明文密码进行加密
     * @param rawPass 明文密码 -- 未加密的密码字符串
     * @param salt 密码盐参数
     * @return 返回加密后的密码字符串传
     */
    public static String encode(String rawPass,String salt){
        return new PasswordEncoder(salt).encode(rawPass);
    }

    /**
     * 获取密码加密盐参数
     * @return 返回密码盐字符串
     */
    public static String getSalt(){
        return UUID.randomUUID().toString().replaceAll("-","").substring(0,20);
    }

}
