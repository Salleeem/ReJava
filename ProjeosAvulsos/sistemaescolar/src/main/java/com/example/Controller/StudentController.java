package com.example.Controller;

import com.example.DAO.StudentDAO;
import com.example.Model.Student;

public class StudentController {

    private StudentDAO studentDAO;

    public StudentController() {
        studentDAO = new StudentDAO();
    }

    public void createStudent(String cpf, String name, String password) {
        Student student = new Student();

        student.setCpf(cpf);
        student.setName(name);
        student.setPassword(password);

        studentDAO.createStudent(student);

    }

}
