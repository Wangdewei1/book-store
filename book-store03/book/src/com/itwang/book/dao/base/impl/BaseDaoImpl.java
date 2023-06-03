package com.itwang.book.dao.base.impl;

import com.itwang.book.dao.base.api.BaseDao;
import com.itwang.book.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    public void batchAllData(String sql, Object[][] params) {
        try {
            Connection connection = JDBCUtils.getConnection();
            queryRunner.batch(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer generateGetPrimaryKey(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            preparedStatement.executeUpdate();

            //获取主键
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()){
                BigInteger bigInteger = (BigInteger) generatedKeys.getObject(1);
                return bigInteger.intValue();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}