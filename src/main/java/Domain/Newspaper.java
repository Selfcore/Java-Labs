package Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
public class Newspaper extends PaperPublication {
    private int issueNumber;
    private LocalDate publicationDate;
    private Map<String, String> columns;

    public Newspaper(String title, String publisher, int year, int issueNumber, LocalDate publicationDate, Map<String, String> columns) {
        super(title, publisher, year);
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
        this.columns = columns;
    }
}
