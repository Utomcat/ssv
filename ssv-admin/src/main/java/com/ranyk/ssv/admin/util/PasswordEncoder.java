package com.ranyk.ssv.admin.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

/**
 * ClassName:PasswordEncoder
 * Description:密码加密类
 *
 * @author ranyi
 * @date 2019-12-19 11:56
 * Version: V1.0
 */
public class PasswordEncoder {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d","e", "f"};

    private final static String MD5 = "MD5";
    private final static String SHA = "SHA";

    private Object salt;
    private String  algorithm;

    /**
     * 无参构造函数
     */
    public PasswordEncoder() {
    }

    /**
     * 传入salt参数的构造函数
     * @param salt salt参数
     */
    public PasswordEncoder(Object salt) {
        this(salt,MD5);
    }

    /**
     * 传入salt参数和algorithm参数的构造函数
     * @param salt salt参数
     * @param algorithm algorithm参数
     */
    public PasswordEncoder(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    /**
     * 密码进行加密的方法
     * @param rawPass 原密码
     * @return 返回加密后的字符串
     */
    public String encode(String rawPass){
        String result = null;
        try {

            MessageDigest md = MessageDigest.getInstance(algorithm);

            //获取加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("UTF-8")));

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 密码验证的方法，判断输入的密码是否和保存的密码一致
     * @param encPass 密文 -- 加密后的字符串
     * @param rawPass 明文 -- 未加密的字符串
     * @return
     */
    public boolean matches(String encPass,String rawPass){
        String pass1 = ""+encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    @NotNull
    private String mergePasswordAndSalt(String password) {
        if (password == null){
            password = "";
        }
        if ((salt == null)||"".equals(salt)) {
            return password;
        }else {
            return password+"{"+salt.toString()+"}";
        }
    }

    /**
     * 转换字节数组为16进制的字符串
     * @param b 字节数组
     * @return 返回16进制的字符串
     */
    @NotNull
    private String byteArrayToHexString(@NotNull byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将字节转换为16进制字符串
     * @param b 字节参数
     * @return 返回16进制的字符串
     */
    @NotNull
    @Contract(pure = true)
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1]+hexDigits[d2];
    }

}
