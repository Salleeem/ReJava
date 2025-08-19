package com.example.View;

import javax.swing.*;

import com.example.View.Admin.AdminLoginView;
import com.example.View.Student.StudentLoginView;
import com.example.View.Teacher.TeacherLoginView;

public class MainView {

    private JFrame mainFrame;

    private JButton adminButton;
    private JButton teacherButton;
    private JButton studentButton;

    private JLabel welcomeText;

    public MainView() {

        mainFrame = new JFrame("Menu Principal");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);

        welcomeText = new JLabel("Bem vindo a aplicação, o que deseja fazer?");

        adminButton = new JButton("Login de Admin");
        adminButton.addActionListener(e ->{
            new AdminLoginView();
        });

        teacherButton = new JButton("Login do Professor");
        teacherButton.addActionListener(e ->{
            new TeacherLoginView();
        });

        studentButton = new JButton("Login do Aluno");
        studentButton.addActionListener(e ->{
            new StudentLoginView();
        });

        
        JPanel mainPanel = new JPanel();
        mainPanel.add(welcomeText);
        mainPanel.add(adminButton);
        mainPanel.add(teacherButton);
        mainPanel.add(studentButton);
        

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

    }

}
