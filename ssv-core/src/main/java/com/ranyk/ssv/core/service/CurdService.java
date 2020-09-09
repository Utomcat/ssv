package com.ranyk.mango.core.service;

import com.ranyk.mango.core.page.PageRequest;
import com.ranyk.mango.core.page.PageResult;

import java.util.List;

/**
 * ClassName:CurdService
 * Description:对通用的增删改查接口的封装
 *
 * @author ranyi
 * @date 2019-12-17 16:52
 * Version: V1.0
 */
public interface CurdService<T> {

    /**
     * 保存操作
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 删除操作
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 批量删除操作
     * @param records
     * @return
     */
    int delete(List<T> records);

    /**
     * 根据ID查询
     * @param id 主键ID
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象，
     * 如mybatis和JPA的分页对象从而避免因为替换ORN框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会影响服务层以上的分页接口，起到了解耦的作用
     *
     * @param pageRequest 自定义，统一分页查询的请求
     * @return PageResult 自定义，统一分页查询的结果
     */
    PageResult findPage(PageRequest pageRequest);


}
