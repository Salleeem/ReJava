package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.Database;
import com.example.Model.Subject;

public class SubjectDAO {
    
    public void createSubject(Subject subject){
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO subject (name) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,subject.getName());
            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
         e.printStackTrace();
        }
    }
}
