package com.example.Controller;

import com.example.Dao.CarDAO;
import com.example.Model.Car;

public class CarController {

    private CarDAO carDAO;

    public CarController() {

        carDAO = new CarDAO();

    }

    public void createCar(String plate, String model, String brand, double price, int year) {

        Car car = new Car();

        car.setPlate(plate);
        car.setModel(model);
        car.setBrand(brand);
        car.setPrice(price);
        car.setYear(year);

        carDAO.create(car);

    }

    public void deleteCar(int id) {
        carDAO.delete(id);
    }

    public void updateCar(int id, String plate, String model, String brand, double price, int year) {
        Car car = new Car();
        car.setId(id);
        car.setPlate(plate);
        car.setModel(model);
        car.setBrand(brand);
        car.setPrice(price);
        car.setYear(year);

        carDAO.update(car);
    }

}