package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.Database;
import com.example.Model.Student;

public class StudentDAO {

    public void createStudent(Student student) {

        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO students (cpf, name, password) VALUES (?, ?, ?)";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, student.getCpf());
                stmt.setString(2, student.getName());
                stmt.setString(3, student.getPassword());

                stmt.executeUpdate();
                stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






































    

    public Student login(String cpf, String password) {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM student WHERE cpf = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setCpf(rs.getString("cpf"));
                student.setPassword(rs.getString("password"));
                return student; 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

