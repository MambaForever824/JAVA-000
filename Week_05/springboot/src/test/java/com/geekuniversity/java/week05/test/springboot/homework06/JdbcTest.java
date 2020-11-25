package com.geekuniversity.java.week05.test.springboot.homework06;

import com.geekuniversity.java.week05.springboot.homework06.DatabaseConnectionUtil;
import com.geekuniversity.java.week05.springboot.homework06.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.setId(1L);
        student.setName("LiWenHai");
        insert(student);
        select(1L);
        student.setName("LiWenHai123");
        update(student);
        select(1L);
        delete(student);
    }

    private static void insert(Student student) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "insert into student(id, name) values(" + student.getId() + "," + student.getName() + ")";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, statement, null);
        }
    }

    private static void update(Student student) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "update student set name = " + student.getName() + " where id = " + student.getId();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, statement, null);
        }
    }

    private static void delete(Student student) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "delete from student where id = " + student.getId();
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, statement, null);
        }
    }

    public static void select(Long id) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from student where id = " + id;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //取出每一行里面的数据
                long resultId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                System.out.println(id + ", " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(connection, statement, resultSet);
        }
    }
}