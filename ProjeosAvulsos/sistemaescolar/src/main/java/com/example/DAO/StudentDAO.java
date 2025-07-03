package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.Database;
import com.example.Model.Person;
import com.example.Model.Student;

public class StudentDAO {

    public void createStudent(Student student) {

        try (Connection conn = Database.getConnection()) {

            // Primeiro inserir na tabela person
            String personSql = "INSERT INTO person (cpf) VALUES (?) RETURNING id";
            PreparedStatement personStmt = conn.prepareStatement(personSql);
            personStmt.setString(1, student.getPerson().getCpf());

            ResultSet rs = personStmt.executeQuery();
            Long personId = null;

            if (rs.next()) {
                personId = rs.getLong("id");
            } else {
                throw new RuntimeException("Erro ao inserir na tabela person");
            }

            // Depois insrimos o id de person no student para referenciar o cpf
            String studentSql = "INSERT INTO student (name, password, person_id) VALUES (?, ?, ?)";
            PreparedStatement studentStmt = conn.prepareStatement(studentSql);

            studentStmt.setString(1, student.getName());
            studentStmt.setString(2, student.getPassword());
            studentStmt.setLong(3, personId);

            studentStmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(long id) {
        try (Connection conn = Database.getConnection()) {

            // Primeiro precisa fazer a busca do "person_id" associado
            String selectSql = "SELECT person_id FROM student WHERE id = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setLong(1, id);
            ResultSet rs = selectStmt.executeQuery();

            long personId = -1;
            if (rs.next()) {
                personId = rs.getLong("person_id");
            }

            // Agora sim deletamos o aluno
            String deleteStudentSql = "DELETE FROM student WHERE id = ?";
            PreparedStatement deleteStudentStmt = conn.prepareStatement(deleteStudentSql);
            deleteStudentStmt.setLong(1, id);
            deleteStudentStmt.executeUpdate();

            // E agora deletamos o person para não deixar "Lixo" no banco
            if (personId != -1) {
                String deletePersonSql = "DELETE FROM person WHERE id = ?";
                PreparedStatement deletePersonStmt = conn.prepareStatement(deletePersonSql);
                deletePersonStmt.executeLargeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try (Connection conn = Database.getConnection()) {

            // Precisa atualizar o CPF do person primeiro
            String personSql = "UPDATE person SET cpf = ? WHERE id = ?";
            PreparedStatement personStmt = conn.prepareStatement(personSql);
            personStmt.setString(1, student.getPerson().getCpf());
            personStmt.setLong(2, student.getPerson().getId());
            personStmt.executeUpdate();

            // Agora sim atualizamos os dados do aluno
            String studentSql = "UPDATE student SET name = ?, password = ?";
            PreparedStatement studentStmt = conn.prepareStatement(studentSql);
            studentStmt.setString(1, student.getName());
            studentStmt.setString(1, student.getPassword());
            studentStmt.setLong(4, student.getId());

            studentStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> list() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT s.id as student_id, s.name, s.password, " +
            "p.id as person_id, p.cpf " +
            "FROM student s " +
            "JOIN person p ON s.person_id = p.id";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("student_id"));
                student.setName(rs.getString("name"));
                student.setPassword(rs.getString("password"));

                Person person = new Person();
                person.setId(rs.getLong("person_id"));
                person.setCpf(rs.getString("cpf"));
                student.setPerson(person);

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;

    }

}
