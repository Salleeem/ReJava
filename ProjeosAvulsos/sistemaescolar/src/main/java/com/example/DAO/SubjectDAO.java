package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.Database;
import com.example.Model.Subject;

public class SubjectDAO {

    public void createSubject(Subject subject) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO subject (name) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, subject.getName());
            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSubject(int id) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM subject WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSubject(Subject subject) {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE subject SET name = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, subject.getName());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Subject> list() {
        List<Subject> subjects = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {

            String sql = "SELECT * FROM subject";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("name"));

                subjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;

    }

    public Subject findById(int id) {
        Subject subject = null;

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM subject WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setId(rs.getLong("id"));
                subject.setName(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

}
