package com.example.View.Admin;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

import com.example.DAO.AdminDAO;
import com.example.Model.Admin;

public class AdminLoginView extends JFrame {

    private JFormattedTextField cpfField; 
    private JPasswordField passwordField;
    private JButton loginButton;
    private AdminDAO adminDAO;

    public AdminLoginView() {
        super("Login do Administrador");

        adminDAO = new AdminDAO();

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(null);

        JLabel userLabel = new JLabel("CPF:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            cpfField = new JFormattedTextField(cpfMask);
        } catch (ParseException e) {
            e.printStackTrace();
            cpfField = new JFormattedTextField();
        }
        cpfField.setBounds(120, 30, 180, 25);
        add(cpfField);

        JLabel passLabel = new JLabel("Senha:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        loginButton = new JButton("Entrar");
        loginButton.setBounds(120, 110, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminLogin();
            }
        });

        setVisible(true);
    }

    private void adminLogin() {
        String cpfWithMask = cpfField.getText();
        String cleanCpf = cpfWithMask.replaceAll("[^0-9]", ""); // remove tudo que não é número

        String password = new String(passwordField.getPassword());

        Admin admin = adminDAO.login(cleanCpf, password); 

        if (admin != null) {
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + admin.getUsername() + "!");
            new AdminDashboardView(admin.getUsername());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
