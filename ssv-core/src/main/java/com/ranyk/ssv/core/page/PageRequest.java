package com.ranyk.mango.core.page;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: PageRequest
 * Description: 分页请求对象类
 *
 * 用于封装分页查询时，查询条件中的 查询条件和limit中的参数，即需要那一页，总共有多少页，，以及查询的其他限制条件
 *
 * @author ranyi
 * @date 2019-12-17 17:02
 * Version: V1.0
 */
@Data
public class PageRequest {

    /**
     * 当前是第几页 默认为第一页
     */
    private int pageNum = 1;

    /**
     * 每一页查询的数据量 默认每一页查询10条数据
     */
    private  int pageSize = 10;

    /**
     * 查询参数的Map集合，利用Key-Value进行存放前端发起请求时传入的参数列表
     */
    private Map<String,Object> params = new HashMap<>();

    /**
     * 获取指定名称的参数值，从查询参数集合(前端的参数列表，后端的参数集合)中获取，利用Map集合的get(String key)方法
     * @param key 需要获取的参数的key值
     * @return 返回获取的key值
     */
    public Object getParam(String key){
        return getParams().get(key);
    }

}
