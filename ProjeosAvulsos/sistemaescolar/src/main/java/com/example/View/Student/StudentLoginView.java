package com.example.View.Student;

import javax.swing.*;
import java.awt.event.*;
import com.example.DAO.AdminDAO;
import com.example.Model.Admin;

public class StudentLoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private AdminDAO adminDAO;

    public StudentLoginView() {
        super("Login do Aluno");

        adminDAO = new AdminDAO();

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza

        // Layout manual
        setLayout(null);

        JLabel userLabel = new JLabel("CPF:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 180, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Senha:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        loginButton = new JButton("Entrar");
        loginButton.setBounds(120, 110, 100, 30);
        add(loginButton);

        // Ação do botão
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        setVisible(true);
    }

    private void realizarLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Admin admin = adminDAO.login(username, password);

        if (admin != null) {
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + admin.getUsername() + "!");
            
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
