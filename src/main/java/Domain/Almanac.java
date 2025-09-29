package Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Almanac extends PaperPublication {
    private String genre;
    private int pages;
    private List<Book> works;

    public Almanac(String title, String publisher, int year, String genre, int pages, List<Book> works) {
        super(title, publisher, year);
        this.genre = genre;
        this.pages = pages;
        this.works = works;
    }
}
