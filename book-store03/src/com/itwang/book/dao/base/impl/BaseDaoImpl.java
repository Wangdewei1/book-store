package com.itwang.book.dao.base.impl;

import com.itwang.book.dao.base.api.BaseDao;
import com.itwang.book.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<T> selectAllData(String sql, Class<T> clazz, Object... args) {
        Connection connection = JDBCUtils.getConnection();

        try {
            List<T> list = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.releaseDataSource(connection);
        }
    }

    @Override
    public T selectOne(String sql, Class<T> clazz, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            T entity = queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.releaseDataSource(connection);
        }
    }

    @Override
    public Object selectScalar(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            Object o = queryRunner.query(connection,sql, new ScalarHandler(), args);
            return o;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.releaseDataSource(connection);
        }
    }

    @Override
    public Integer update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            int rows = queryRunner.update(connection, sql, args);
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}