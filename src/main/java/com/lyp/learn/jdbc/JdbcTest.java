package com.lyp.learn.jdbc;

import java.sql.*;

/**
 *  纯 jdbc 编写代码
 */
public class JdbcTest {
    public static void main(String[] args) {

        //数据库连接
        Connection connection = null;
        //预处理statement
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //通过驱动管理类获取链接
            String jdbcUrl = "jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8&serverTimezone=UTC&useSSL=true";
            connection = DriverManager.getConnection(jdbcUrl,"root","Root$123");

            //定义sql 语句 ? 表示占位符
            String sql = "SELECT * FROM user WHERE username = ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数,第一个参数为sql语句中参数的行号(从1开始)，第二个参数为设置的参数值
            preparedStatement.setString(1,"王五");
            //向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //遍历查询结果集
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + " : " + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源，从内到外依次释放
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
