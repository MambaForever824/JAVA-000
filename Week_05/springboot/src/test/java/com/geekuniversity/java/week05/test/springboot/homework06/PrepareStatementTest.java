package com.geekuniversity.java.week05.test.springboot.homework06;

import com.geekuniversity.java.week05.springboot.homework06.DatabaseConnectionUtil;
import com.geekuniversity.java.week05.springboot.homework06.Student;

import java.sql.*;

public class PrepareStatementTest {

    private static final String INSERT_SQL = "insert into student(id, name) values (?, ?)";

    private static final String UPDATE_SQL = "update student set name = ? where id = ?";

    private static final String DELETE_SQL = "delete from student where id = ?";

    private static final String SELECT_SQL = "select * from where id = ?";

    public static void main(String[] args) {
        Student student = new Student();
        student.setId(2L);
        student.setName("test000");
        insert(student);
        select(2L);
        student.setName("test111");
        update(student);
        select(2L);
        delete(2L);
    }

    private static void insert(Student student) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            // 事务不自动提交
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setNString(2, student.getName());
            int result = preparedStatement.executeUpdate();
            // 事务提交
            connection.commit();
            System.out.println("新增数据数量：" + result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, preparedStatement, null);
        }
    }

    private static void update(Student student) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setNString(1, student.getName());
            preparedStatement.setLong(2, student.getId());
            int result = preparedStatement.executeUpdate();
            System.out.println("更新数据数量：" + result);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, preparedStatement, null);
        }
    }

    private static void delete(Long id) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        PreparedStatement preparedStatement  = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setLong(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, preparedStatement, null);
        }
    }

    public static void select(Long id) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_SQL);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //取出每一行里面的数据
                long resultId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                System.out.println(id + ", " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, preparedStatement, resultSet);
        }
    }
}