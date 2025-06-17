package com.example.Dao;

import java.sql.Connection;

import com.example.DataBase;
import com.example.Model.Car;

public class CarDAO {

    public void create(Car car) {
try (Connection conn = DataBase.getConnection()) {
        
} catch (Exception e) {
    e.printStackTrace();
}
}

}
