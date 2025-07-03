package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Person;
import com.example.Model.Subject;
import com.example.Model.Teacher;

import com.example.Database;

public class TeacherDAO {

    public void createTeacher(Teacher teacher) {
        try (Connection conn = Database.getConnection()) {

            // 1. Inserir na tabela person
            String personSql = "INSERT INTO person (cpf) VALUES (?) RETURNING id";
            PreparedStatement personStmt = conn.prepareStatement(personSql);
            personStmt.setString(1, teacher.getPerson().getCpf());

            ResultSet rs = personStmt.executeQuery();
            Long personId = null;
            if (rs.next()) {
                personId = rs.getLong("id");
            } else {
                throw new RuntimeException("Erro ao inserir na tabela person.");
            }

            // 2. Inserir na tabela teacher com o person_id obtido
            String teacherSql = "INSERT INTO teacher (name, password, subject_id, person_id) VALUES (?, ?, ?, ?)";
            PreparedStatement teacherStmt = conn.prepareStatement(teacherSql);
            teacherStmt.setString(1, teacher.getName());
            teacherStmt.setString(2, teacher.getPassword());
            teacherStmt.setLong(3, teacher.getSubject().getId());
            teacherStmt.setLong(4, personId);

            teacherStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(long id) {
        try (Connection conn = Database.getConnection()) {

            //Primeiro precisa fazer a busca do "person_id" associado
            String selectSql = "SELECT person_id FROM teacher WHERE id = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setLong(1, id);
            ResultSet rs = selectStmt.executeQuery();

            long personId = -1;
            if (rs.next()) {
                personId =  rs.getLong("person_id");
            }

            //Agora sim deletamos o professor
            String deleteTeacherSql = "DELETE FROM teacher WHERE id = ?";
            PreparedStatement deleteTeacherStmt = conn.prepareStatement(deleteTeacherSql);
            deleteTeacherStmt.setLong(1, id);
            deleteTeacherStmt.executeUpdate();

            //E agora deletamos o person para não deixar "Lixo" no banco
            if (personId != -1) {
                String deletePersonSql = "DELETE FROM person WHERE id = ?";
                PreparedStatement deletePersonStmt = conn.prepareStatement(deletePersonSql);
                deletePersonStmt.setLong(1,personId);
                deletePersonStmt.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateTeacher(Teacher teacher) {
        try (Connection conn = Database.getConnection()) {

            // Atualiza o CPF da pessoa
            String personSql = "UPDATE person SET cpf = ? WHERE id = ?";
            PreparedStatement personStmt = conn.prepareStatement(personSql);
            personStmt.setString(1, teacher.getPerson().getCpf());
            personStmt.setLong(2, teacher.getPerson().getId());
            personStmt.executeUpdate();

            // Atualiza os dados do professor
            String teacherSql = "UPDATE teacher SET name = ?, password = ?, subject_id = ? WHERE id = ?";
            PreparedStatement teacherStmt = conn.prepareStatement(teacherSql);
            teacherStmt.setString(1, teacher.getName());
            teacherStmt.setString(2, teacher.getPassword());
            teacherStmt.setLong(3, teacher.getSubject().getId());
            teacherStmt.setLong(4, teacher.getId());
            teacherStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> list() {
        List<Teacher> teachers = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT t.id as teacher_id, t.name, t.password, t.subject_id, " +
                    "p.id as person_id, p.cpf, " +
                    "s.id as subject_id, s.name as subject_name " +
                    "FROM teacher t " +
                    "JOIN person p ON t.person_id = p.id " +
                    "LEFT JOIN subject s ON t.subject_id = s.id";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("teacher_id"));
                teacher.setName(rs.getString("name"));
                teacher.setPassword(rs.getString("password"));

                // Vincula o Person
                Person person = new Person();
                person.setId(rs.getLong("person_id"));
                person.setCpf(rs.getString("cpf"));
                teacher.setPerson(person);

                // Vincula o Subject
                Subject subject = new Subject();
                subject.setId(rs.getLong("subject_id"));
                subject.setName(rs.getString("subject_name"));
                teacher.setSubject(subject);

                teachers.add(teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return teachers;
    }

}
