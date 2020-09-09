package com.ranyk.ssv.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranyk.ssv.common.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * ClassName:MybatisPageHelper
 * Description:Mybatis 分页查询助手类
 *
 * @author ranyi
 * @date 2019-12-17 17:06
 * Version: V1.0
 */
public class MybatisPageHelper {

    /**
     * 定义全局变量参数 分页查询请求 findPage
     */
    public static final String findPage = "findPage";

    /**
     * 分页查询，约定查询方法名为 “findPage”
     * @param pageRequest 分页请求对象，用于封装分页请求的参数
     * @param mapper Dao接口对象，对应着需要调用MyBatis的哪个Mapper映射xml文件
     * @return 返回其查询结果对象
     */
    @NotNull
    public static PageResult findPage(PageRequest pageRequest, Object mapper){

        //调用实际执行的分页方法
        return findPage(pageRequest,mapper,findPage);
    }

    /**
     * 调用分页插件(PageHelper类)进行查询
     * @param pageRequest 分页请求对象，用于封装分页请求的参数
     * @param mapper Dao接口对象，对应着需要调用MyBatis的哪个Mapper映射xml文件
     * @param queryMethodName 需要执行Dao接口中的分页查询的方法名，对应着需要调用MyBatis的映射XML文件中的哪个id标签
     * @param args 方法参数
     * @return 返回结果对象封装对象 PageResult
     */
    @NotNull
    public static PageResult findPage(@NotNull PageRequest pageRequest, Object mapper, String queryMethodName, Object... args){

        //初始化当前页码的值
        int pageNum = pageRequest.getPageNum();
        //初始化每页显示的数据量的值
        int pageSize = pageRequest.getPageSize();

        // 方法执行结果返回Page对象，但是此Page对象不知在何处使用 ????
        PageHelper.startPage(pageNum,pageSize);

        //执行指定的方法，获取执行结果
        Object result = ReflectionUtils.invoke(mapper,queryMethodName,args);

        //返回执行结果封装对象PageResult
        return getPageResult(pageRequest,new PageInfo((List) result));
    }

    /**
     * 将分页的结果信息封装到结果对象中
     * @param pageRequest 分页请求对象，用于封装分页请求的参数
     * @param pageInfo 分页查询信息对象
     * @return 返回分页结果对象 PageResult
     */
    @NotNull
    private static PageResult getPageResult(PageRequest pageRequest, @NotNull PageInfo<?> pageInfo) {
        //创建请求响应结果对象，并初始化
        PageResult pageResult = new PageResult();

        //设置响应结果中当前页码的属性值
        pageResult.setPageNum(pageInfo.getPageNum());

        //设置请求响应结果中当前页面的数据量属性值
        pageResult.setPageSize(pageInfo.getPageSize());

        //设置请求响应结果中数据总量的属性值
        pageResult.setTotalSize(pageInfo.getTotal());

        //设置请求响应结果中页数总量属性值
        pageResult.setTotalPages(pageInfo.getPages());

        //设置请求响应结果中的分页数据的属性值
        pageResult.setContent(pageInfo.getList());

        //返回请求响应结果对象
        return pageResult;
    }
}
