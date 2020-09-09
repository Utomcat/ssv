package com.ranyk.mango.core.page;

import lombok.Data;

import java.util.List;

/**
 * ClassName:PageResult
 * Description:分页查询返回结果对象类
 *
 * @author ranyi
 * @date 2019-12-17 16:58
 * Version: V1.0
 */
@Data
public class PageResult {

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数据的数量
     */
    private int pageSize;

    /**
     * 查询到的数据总量
     */
    private long totalSize;

    /**
     * 数据总共的页码数
     */
    private int totalPages;

    /**
     * 分页的数据
     */
    private List<?> content;



}
