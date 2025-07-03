package com.example.Controller;

import com.example.DAO.StudentDAO;
import com.example.Model.Person;
import com.example.Model.Student;

public class StudentController {

    private StudentDAO studentDAO;

    public StudentController() {
        studentDAO = new StudentDAO();
    }

    public void createStudent(String cpf, String name, String password) {
        Student student = new Student();
        Person person = new Person();

        person.setCpf(cpf);
        student.setName(name);
        student.setPerson(person);
        student.setPassword(password);

        studentDAO.createStudent(student);

    }

    public void deleteStudent(long id) {
        studentDAO.deleteStudent(id);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

}
