package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.Database;
import com.example.Model.Grade;

public class GradeDAO {

    public void createGrade(Grade grade) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO grade (t1, t2, simul, schoolwork, part, subject, student) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, grade.getT1());
            stmt.setDouble(2, grade.getT2());
            stmt.setDouble(3, grade.getSimul());
            stmt.setDouble(4, grade.getSchoolwork());
            stmt.setDouble(5, grade.getPart());
            stmt.setLong(6, grade.getSubject().getId());
            stmt.setLong(7, grade.getStudent().getId());

            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGrade(int id) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELET FROM grade WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGrade(Grade grade) {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE grade SET t1 = ?, t2 = ?, simul = ?, schoolwork = ?, part = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, grade.getT1());
            stmt.setDouble(2, grade.getT2());
            stmt.setDouble(3, grade.getSimul());
            stmt.setDouble(4, grade.getSchoolwork());
            stmt.setDouble(5, grade.getPart());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Grade> list() {
        List<Grade> grades = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM grade";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Grade grade = new Grade();
                grade.setId(rs.getLong("id"));
                grade.setT1(rs.getDouble("t1"));
                grade.setT2(rs.getDouble("t2"));
                grade.setSimul(rs. getDouble("simul"));
                grade.setSchoolwork(rs.getDouble("schoolwork"));
                grade.setPart(rs.getDouble("part"));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grades;
    }
}
