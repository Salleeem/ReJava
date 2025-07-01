package com.example.Controller;

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
}
