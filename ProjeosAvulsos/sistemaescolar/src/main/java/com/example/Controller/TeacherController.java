package com.example.Controller;

import com.example.DAO.TeacherDAO;
import com.example.Model.Person;
import com.example.Model.Subject;
import com.example.Model.Teacher;

public class TeacherController {

    private TeacherDAO teacherDAO;

    public TeacherController() {
        teacherDAO = new TeacherDAO();
    }

    public void createTeacher(String name, String cpf, String password, Subject subject) {
        Teacher teacher = new Teacher();
        Person person = new Person();

        teacher.setName(name);
        person.setCpf(cpf);
        teacher.setPerson(person);
        teacher.setPassword(password);
        teacher.setSubject(subject);

        teacherDAO.createTeacher(teacher);
    }

    public void deleteTeacher(long id) {
        teacherDAO.deleteTeacher(id);
    }

    public void updateTeacher(Teacher teacher) {
        teacherDAO.updateTeacher(teacher);
    }

}
