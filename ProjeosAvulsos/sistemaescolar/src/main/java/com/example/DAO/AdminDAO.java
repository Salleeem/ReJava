package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.Database;
import com.example.Model.Admin;

public class AdminDAO {

    public Admin login(String cpf, String password) {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM admin WHERE cpf = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setCpf(rs.getString("cpf"));
                admin.setPassword(rs.getString("password"));
                return admin; 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
