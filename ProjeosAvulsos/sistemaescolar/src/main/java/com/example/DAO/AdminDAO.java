package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.Database;
import com.example.Model.Admin;
import com.example.Model.Person;

public class AdminDAO {

    public Admin login(String cpf, String password) {
    try (Connection conn = Database.getConnection()) {
        // Consulta com JOIN entre admin e person
        String sql = "SELECT a.id as admin_id, a.username, a.password, p.id as person_id, p.cpf " +
                     "FROM admin a " +
                     "JOIN person p ON a.person_id = p.id " +
                     "WHERE p.cpf = ? AND a.password = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Admin admin = new Admin();
            admin.setId(rs.getLong("admin_id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));

            // Cria e seta o objeto Person dentro do Admin
            Person person = new Person();
            person.setId(rs.getLong("person_id"));
            person.setCpf(rs.getString("cpf"));
            admin.setPerson(person);

            return admin;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

}
