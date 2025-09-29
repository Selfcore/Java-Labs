package org.example;

import Domain.Car;
import Domain.CargoType;
import Domain.Driver;
import Domain.OrderRequest;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Volvo FH16", 25000);
        Driver driver = new Driver("Ivan Petrenko", 7, car);

        OrderRequest order = new OrderRequest("Kyiv", 50, CargoType.MACHINERY, 12000);

        System.out.println(driver);
        System.out.println(order);

        if (car.getCarryingCapacity() >= order.getWeight()) {
            System.out.println("Driver can handle the order.");
        } else {
            System.out.println("Load too heavy for the driver's car.");
        }
    }
}