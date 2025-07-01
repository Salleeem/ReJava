package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.Model.Teacher;

import com.example.Database;

public class TeacherDAO {
    
    public void createTeacher(Teacher teacher) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO teacher (name, cpf, subject, password) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getCpf());
            stmt.setLong(3, teacher.getSubject().getId());
            stmt.setString(4, teacher.getPassword());

            stmt.executeUpdate();
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
