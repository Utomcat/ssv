package com.ranyk.mango.core.http;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * ClassName:HttpResult
 * Description:对Http请求的返回(响应)结果的封装对象 响应的内容为 响应状态码、响应信息、响应数据
 *
 * @author ranyi
 * @date 2019-12-17 18:25
 * Version: V1.0
 */
@Data
public class HttpResult {

    /**
     * 声明Http请求返回的状态码变量，并初始化其值为200(请求响应成功)
     */
    private int code = 200;

    /**
     * 声明Http请求返回的信息变量
     */
    private  String  msg;

    /**
     * 声明Http请求返回的数据对象
     */
    private Object data;

    /**
     * 服务器响应错误处理方法
     * @return HttpResult Http请求处理结果(响应)对象
     */
    @NotNull
    public static HttpResult error(){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员");
    }

    /**
     * 服务器响应错误处理方法
     * @param msg 相关错误信息
     * @return  HttpResult Http请求处理结果(响应)对象
     */
    @NotNull
    public  static HttpResult error(String msg){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,msg);
    }

    /**
     * 服务器响应错误处理方法
     * @param code 错误状态代码
     * @param msg 错误信息
     * @return HttpResult Http请求处理结果(响应)对象
     */
    @NotNull
    private static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    /**
     * 服务器响应成功处理方法
     * @return HttpResult Http请求处理结果(响应)对象
     */
    @NotNull
    @Contract(" -> new")
    public static HttpResult ok(){
        return new HttpResult();
    }

    /**
     * 服务器响应成功处理方法
     * @param msg 成功信息
     * @return HttpResult Http请求处理结果(响应)对象
     */
    @NotNull
    public static HttpResult ok(String msg){
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }

    /**
     * 服务器响应成功处理方法
     * @param data 成功处理后的响应数据对象
     * @return HttpResult Http请求处理结果(响应)对象
     */
    @NotNull
    public static HttpResult ok(Object data){
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }

}
