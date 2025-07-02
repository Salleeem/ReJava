package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Subject;
import com.example.Model.Teacher;

import com.example.Database;

public class TeacherDAO {

    public void createTeacher(Teacher teacher) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO teacher (name, cpf, subject_id, password) VALUES (?, ?, ?, ?)";

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

    public void deleteTeacher(long id) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM teacher WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateTeacher(Teacher teacher) {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE teacher SET name = ?, cpf = ?, password, subject_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getCpf());
            stmt.setString(3, teacher.getPassword());
            stmt.setLong(4, teacher.getSubject().getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> list() {
        List<Teacher> teachers = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM teacher";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setName(rs.getString("name"));
                teacher.setCpf(rs.getString("cpf"));
                teacher.setPassword(rs.getString("password"));
                int subjectId = rs.getInt("subject_id");
                SubjectDAO subjectDAO = new SubjectDAO();
                Subject subject = subjectDAO.findById(subjectId); 
                teacher.setSubject(subject); 

                teachers.add(teacher);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teachers;
    }
}
