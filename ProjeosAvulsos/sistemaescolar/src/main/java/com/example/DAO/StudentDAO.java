package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
