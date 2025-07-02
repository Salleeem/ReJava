package com.example.View;

import javax.swing.*;

import com.example.View.Teacher.ManageTeacherTabsView;
import com.example.View.Student.ManageStudentTabsView;

import java.awt.event.*;

public class AdminDashboardView {

    private JFrame mainFrame;
    private JButton manageTeacherButton;
    private JButton manageStudentButton;

    public AdminDashboardView() {
        mainFrame = new JFrame("Painel do Administrador");
        mainFrame.setSize(400, 250);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null); // Centraliza na tela
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Bem-vindo, Administrador");
        titleLabel.setBounds(110, 20, 200, 30);
        mainFrame.add(titleLabel);

        manageTeacherButton = new JButton("Gerenciar Professores");
        manageTeacherButton.addActionListener(e ->{
            new ManageTeacherTabsView();
        });
        manageTeacherButton.setBounds(100, 70, 200, 30);
        mainFrame.add(manageTeacherButton);

        manageStudentButton = new JButton("Gerenciar Alunos");
        manageStudentButton.setBounds(100, 120, 200, 30);
        mainFrame.add(manageStudentButton);

     

        manageStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageStudentTabsView();
            }
        });

        mainFrame.setVisible(true);
    }
}
