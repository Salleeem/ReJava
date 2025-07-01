package com.example.View;

import javax.swing.*;

public class MainView {

    private JFrame mainFrame;
    private JButton cars;
    private JLabel welcomeText;

    public MainView() {

        mainFrame = new JFrame("Menu Principal");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);

        welcomeText = new JLabel("Bem vindo a aplicação, o que deseja fazer?");
        cars = new JButton("Login de Admin");
        cars.addActionListener(e ->{
            new AdminLoginView();
        });

        JPanel mainPanel = new JPanel();
        mainPanel.add(welcomeText);
        mainPanel.add(cars);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

    }

}
