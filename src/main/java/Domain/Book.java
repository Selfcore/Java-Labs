package Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book extends PaperPublication {
    private String author;
    private String genre;
    private int pages;

    public Book(String title, String publisher, int year, String author, String genre, int pages) {
        super(title, publisher, year);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }
}