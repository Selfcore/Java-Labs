package org.example;

import Domain.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        catalog.initializeTestData(3);

        catalog.display();

        catalog.displayGroupedByType();

        List<PaperPublication> found = catalog.searchByTitle("Supernatural");

        List<Book> authorBooks = catalog.searchBooksByAuthor("Stephen King");

        found.forEach(System.out::println);
        authorBooks.forEach(System.out::println);

        Car car = new Car("Volvo FH16", 25000, List.of(CargoType.MACHINERY, CargoType.CLOTHING, CargoType.ELECTRONICS), false);
        Driver driver = new Driver("Ivan Petrenko", 7, car);

        OrderRequest order = new OrderRequest("Kyiv", 50, CargoType.MACHINERY, 12000, 4, false, false);

        System.out.println(driver);
        System.out.println(order);

        DepotRepository depotRepository = new DepotRepository();
        depotRepository.addCar(car);
        depotRepository.addDriver(driver);
        depotRepository.addOrder(order);

        Dispatcher dispatcher = new Dispatcher(depotRepository);
        dispatcher.assignOrderToDriver();

        driver.completeOrder();
    }
}