package com.example.View;

import com.example.Dao.CarDAO;
import com.example.Model.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListCarView {

    JFrame mainFrame;
    JTable carTable;
    JScrollPane scrollPane;

    public ListCarView() {
        mainFrame = new JFrame("Lista de Carros");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Placa", "Modelo", "Marca", "Valor", "Ano"};

        CarDAO dao = new CarDAO();
        List<Car> cars = dao.list(); 

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Car car : cars) {
            Object[] row = {
                car.getId(),
                car.getPlate(),
                car.getModel(),
                car.getBrand(),
                car.getPrice(),
                car.getYear()
            };
            model.addRow(row);
        }

        carTable = new JTable(model);
        scrollPane = new JScrollPane(carTable);

        mainFrame.add(scrollPane, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
