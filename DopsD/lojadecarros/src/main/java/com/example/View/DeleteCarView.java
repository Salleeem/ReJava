package com.example.View;

import com.example.Controller.CarController;
import com.example.Model.Car;

import javax.swing.*;
import java.awt.*;

public class DeleteCarView {

    JFrame mainFrame;
    JTextField idField;
    JButton searchButton;
    JButton deleteButton;

    JLabel resultLabel;
    JLabel plateLabel;
    JLabel modelLabel;
    JLabel brandLabel;
    JLabel priceLabel;
    JLabel yearLabel;

    JPanel resultPanel;

    CarController controller = new CarController();
    Car currentCar = null;

    public DeleteCarView() {
        mainFrame = new JFrame("Excluir Carro");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("ID do carro:"));
        idField = new JTextField(10);
        inputPanel.add(idField);

        searchButton = new JButton("Buscar");
        inputPanel.add(searchButton);
        mainFrame.add(inputPanel, BorderLayout.NORTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        resultLabel = new JLabel("");
        plateLabel = new JLabel();
        modelLabel = new JLabel();
        brandLabel = new JLabel();
        priceLabel = new JLabel();
        yearLabel = new JLabel();

        resultPanel.add(resultLabel);
        resultPanel.add(plateLabel);
        resultPanel.add(modelLabel);
        resultPanel.add(brandLabel);
        resultPanel.add(priceLabel);
        resultPanel.add(yearLabel);

        mainFrame.add(resultPanel, BorderLayout.CENTER);

        deleteButton = new JButton("Excluir");
        deleteButton.setVisible(false);
        JPanel deletePanel = new JPanel();
        deletePanel.add(deleteButton);
        mainFrame.add(deletePanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);

        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                currentCar = controller.findCarById(id);

                if (currentCar != null) {
                    resultLabel.setText("Carro encontrado:");
                    plateLabel.setText("Placa: " + currentCar.getPlate());
                    modelLabel.setText("Modelo: " + currentCar.getModel());
                    brandLabel.setText("Marca: " + currentCar.getBrand());
                    priceLabel.setText("Preço: R$ " + currentCar.getPrice());
                    yearLabel.setText("Ano: " + currentCar.getYear());
                    deleteButton.setVisible(true);
                } else {
                    resultLabel.setText("Carro não encontrado.");
                    plateLabel.setText("");
                    modelLabel.setText("");
                    brandLabel.setText("");
                    priceLabel.setText("");
                    yearLabel.setText("");
                    deleteButton.setVisible(false);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Digite um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Tem certeza que deseja excluir este carro?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION && currentCar != null) {
                controller.deleteCar(currentCar.getId());

                JOptionPane.showMessageDialog(mainFrame, "Carro excluído com sucesso!");
                mainFrame.dispose(); // Fecha a janela após exclusão
            }
        });

    }

}
