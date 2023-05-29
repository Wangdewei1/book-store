package com.itwang.book.dao.base.api;

import java.util.List;

public interface BaseDao<T> {
    /**
     * 查询所有的数据
     */
    List<T> selectAllData(String sql,Class<T> clazz,Object ...args);

    /**
     * 查询返回一个实体类
     */
    T selectOne(String sql,Class<T> clazz,Object ...args);

    /**
     * 返回一条数据
     */
    Object selectScalar(String sql,Object ...args);

    /**
     * 增删改数据
     */
    Integer update(String sql,Object ...args);

    /**
     * 通用的批量操作数据
     */
    void batchAllData(String sql,Object [][] params);


    /**
     * 返回自增主键 的通用方法
     */
    Integer generateGetPrimaryKey(String sql ,Object ...args);
}