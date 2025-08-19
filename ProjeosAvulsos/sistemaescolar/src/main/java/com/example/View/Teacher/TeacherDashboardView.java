package com.example.View.Teacher;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TeacherDashboardView {
    private JFrame mainFrame;

    private JButton manageStudentButton;

    public TeacherDashboardView(String teacherName) {
        mainFrame = new JFrame("Painel do Professor");
        mainFrame.setSize(400, 250);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null); // Centraliza na tela
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Bem-vindo, " + teacherName);
        titleLabel.setBounds(110, 20, 200, 30);
        mainFrame.add(titleLabel);

        manageStudentButton = new JButton("Gerenciar Alunos");
        manageStudentButton.setBounds(100, 120, 200, 30);
        mainFrame.add(manageStudentButton);
        

        mainFrame.setVisible(true);
    }

}