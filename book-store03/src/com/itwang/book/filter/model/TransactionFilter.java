//package com.itwang.book.filter.model;
//
//import com.itwang.book.filter.base.MyFilter;
//import com.itwang.book.utils.JDBCUtils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class TransactionFilter extends MyFilter {
//    @Override
//    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        Connection connection = JDBCUtils.getConnection();
//
//        try {
//            //关闭事务的自动提交
//            connection.setAutoCommit(false);
//
//            //执行核心操作
//            filterChain.doFilter(request, response);
//
//            //提交事务
//            connection.commit();
//        } catch (Throwable e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//            throw new RuntimeException(e);
//        } finally {
//            JDBCUtils.releaseDataSource(connection);
//        }
//    }
//}
