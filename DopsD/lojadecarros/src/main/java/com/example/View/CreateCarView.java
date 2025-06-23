package com.example.View;

import javax.swing.*;

import com.example.Controller.CarController;

import java.awt.*;

public class CreateCarView {

    JFrame mainFrame;

    JTextField plateField;
    JLabel plateTitle;

    JTextField modelField;
    JLabel modelTitle;

    JTextField brandField;
    JLabel brandTitle;

    JTextField priceField;
    JLabel priceTitle;

    JTextField yearField;
    JLabel yearTitle;

    JLabel textTitle;

    JButton register;

    public CreateCarView() {
        mainFrame = new JFrame("Cadastro de Carros");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(500, 400);
        mainFrame.setLocationRelativeTo(null);

        // Definindo BorderLayout no frame para separar as áreas
        mainFrame.setLayout(new BorderLayout(10, 10));

        // Painel do título no topo
        JPanel titlePanel = new JPanel();
        textTitle = new JLabel("Cadastre um carro abaixo:");
        titlePanel.add(textTitle);
        mainFrame.add(titlePanel, BorderLayout.NORTH);

        // Painel do formulário com GridLayout
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        plateTitle = new JLabel("Placa:");
        plateField = new JTextField(20);

        modelTitle = new JLabel("Modelo:");
        modelField = new JTextField(20);

        brandTitle = new JLabel("Marca:");
        brandField = new JTextField(20);

        priceTitle = new JLabel("Valor:");
        priceField = new JTextField(20);

        yearTitle = new JLabel("Ano:");
        yearField = new JTextField(20);

        formPanel.add(plateTitle);
        formPanel.add(plateField);

        formPanel.add(modelTitle);
        formPanel.add(modelField);

        formPanel.add(brandTitle);
        formPanel.add(brandField);

        formPanel.add(priceTitle);
        formPanel.add(priceField);

        formPanel.add(yearTitle);
        formPanel.add(yearField);

        mainFrame.add(formPanel, BorderLayout.CENTER);

        // Painel do botão na parte inferior, alinhado à direita
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        register = new JButton("Cadastrar");
        buttonPanel.add(register);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);


        
        CarController controller = new CarController();

        register.addActionListener(e -> {
            try {
                String plate = plateField.getText();
                String model = modelField.getText();
                String brand = brandField.getText();
                double price = Double.parseDouble(priceField.getText());
                int year = Integer.parseInt(yearField.getText());

                controller.createCar(plate, model, brand, price, year);

                JOptionPane.showMessageDialog(mainFrame, "Carro cadastrado com sucesso!");
                mainFrame.dispose(); // Fecha a janela após o cadastro (opcional)

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Preço ou ano inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "Erro ao cadastrar carro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
