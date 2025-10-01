package org.example;

import Domain.Book;
import Domain.Catalog;
import Domain.PaperPublication;

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
    }
}