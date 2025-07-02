package com.example.Controller;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.Database;
import com.example.DAO.SubjectDAO;
import com.example.Model.Subject;

public class SubjectController {

    private SubjectDAO subjectDAO;

    public SubjectController() {
        subjectDAO = new SubjectDAO();
    }

    public void createSubject(String name) {
        Subject subject = new Subject();

        subject.setName(name);

        subjectDAO.createSubject(subject);

    }

    public void deleteSubject(int id) {
        subjectDAO.deleteSubject(id);
    }

    public void update() {

    }

    public List<Subject> list() {
        List<Subject> subjects = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM subject";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getLong("id"));
                subject.setName(rs.getString("name"));

                subjects.add(subject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }
}
