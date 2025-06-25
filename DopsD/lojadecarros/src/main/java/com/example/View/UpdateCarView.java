package com.example.View;

import com.example.Controller.CarController;
import com.example.Model.Car;

import javax.swing.*;
import java.awt.*;

public class UpdateCarView {

    JFrame frame;

    JTextField idField, plateField, modelField, brandField, priceField, yearField;
    JButton searchButton, updateButton;

    CarController controller = new CarController();
    Car currentCar;

    public UpdateCarView() {
        frame = new JFrame("Atualizar Carro");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("ID do carro:"));
        idField = new JTextField(10);
        searchButton = new JButton("Buscar");
        searchPanel.add(idField);
        searchPanel.add(searchButton);

        frame.add(searchPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        plateField = new JTextField(20);
        modelField = new JTextField(20);
        brandField = new JTextField(20);
        priceField = new JTextField(20);
        yearField = new JTextField(20);

        formPanel.add(new JLabel("Placa:"));
        formPanel.add(plateField);

        formPanel.add(new JLabel("Modelo:"));
        formPanel.add(modelField);

        formPanel.add(new JLabel("Marca:"));
        formPanel.add(brandField);

        formPanel.add(new JLabel("Preço:"));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Ano:"));
        formPanel.add(yearField);

        frame.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Atualizar");
        updateButton.setEnabled(false);
        buttonPanel.add(updateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                currentCar = controller.findCarById(id);

                if (currentCar != null) {
                    plateField.setText(currentCar.getPlate());
                    modelField.setText(currentCar.getModel());
                    brandField.setText(currentCar.getBrand());
                    priceField.setText(String.valueOf(currentCar.getPrice()));
                    yearField.setText(String.valueOf(currentCar.getYear()));
                    updateButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Carro não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    updateButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Digite um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            try {
                currentCar.setPlate(plateField.getText());
                currentCar.setModel(modelField.getText());
                currentCar.setBrand(brandField.getText());
                currentCar.setPrice(Double.parseDouble(priceField.getText()));
                currentCar.setYear(Integer.parseInt(yearField.getText()));

                controller.updateCar(
                        currentCar.getId(),
                        plateField.getText(),
                        modelField.getText(),
                        brandField.getText(),
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(yearField.getText()));

                JOptionPane.showMessageDialog(frame, "Carro atualizado com sucesso!");
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao atualizar o carro. Verifique os dados.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
