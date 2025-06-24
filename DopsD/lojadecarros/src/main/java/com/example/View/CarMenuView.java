package com.example.View;

import javax.swing.*;

public class CarMenuView {

    JFrame mainFrame;
    JPanel mainPanel;

    JLabel text;

    JButton create;
    JButton list;
    JButton update;
    JButton delete;
    
    public CarMenuView() {
        
        mainFrame = new JFrame("Gerenciar Carros");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);

        text = new JLabel("O que deseja fazer?");

        create = new JButton("Cadastrar");
        create.addActionListener(e ->{
            new CreateCarView();
        });
        list = new JButton("Listar");

        list.addActionListener(e ->{
            new ListCarView();
        });

        update = new JButton("Atualizar");
        delete = new JButton("Deletar");
        delete.addActionListener(e ->{
            new DeleteCarView();
        });

        mainPanel = new JPanel();
        mainPanel.add(text);
        mainPanel.add(create);
        mainPanel.add(list);
        mainPanel.add(update);
        mainPanel.add(delete);


        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        
    }
}
