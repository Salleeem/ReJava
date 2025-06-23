package com.example.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.DataBase;
import com.example.Model.Car;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class CarDAO {

    public void create(Car car) {
        try (Connection conn = DataBase.getConnection()) {
            String sql = "INSERT INTO car (plate, model, brand, price, year) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, car.getPlate());
            stmt.setString(2, car.getModel());
            stmt.setString(3, car.getBrand());
            stmt.setDouble(4, car.getPrice());
            stmt.setInt(5, car.getYear());

            stmt.executeUpdate();
            System.out.println("Carro cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DataBase.getConnection()) {
            String sql = "DELETE FROM car WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Carro deletado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Car car) {
        try (Connection conn = DataBase.getConnection()) {
            String sql = "UPDATE car SET plate = ?, model = ?, brand = ?, price = ?, year = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, car.getPlate());
            stmt.setString(2, car.getModel());
            stmt.setString(3, car.getBrand());
            stmt.setDouble(4, car.getPrice());
            stmt.setInt(5, car.getYear());
            stmt.setInt(6, car.getId());

            stmt.executeUpdate();

            System.out.println("Carro atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Car> list() {
        List<Car> cars = new ArrayList<>();

        try (Connection conn = DataBase.getConnection()) {
            String sql = "SELECT * FROM car";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setPlate(rs.getString("plate"));
                car.setModel(rs.getString("model"));
                car.setBrand(rs.getString("brand"));
                car.setPrice(rs.getDouble("price"));
                car.setYear(rs.getInt("year"));

                cars.add(car);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;
    }

}
