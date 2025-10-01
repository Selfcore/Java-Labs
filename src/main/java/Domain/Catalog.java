package Domain;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Catalog implements CRUD<PaperPublication> {
    private List<PaperPublication> paperPublications = new ArrayList<>();

    @Override
    public void addItem(PaperPublication item) {
        if(item == null)
            return;

        paperPublications.add(item);
    }

    public void addBook(Book book) {
        addItem(book);
    }

    public void addNewspaper(Newspaper newspaper) {
        addItem(newspaper);
    }

    public void addAlmanac(Almanac almanac) {
        addItem(almanac);
    }

    @Override
    public void deleteItem(PaperPublication item) {
        if(item == null)
            return;

        paperPublications.remove(item);
    }

    @Override
    public void updateItem(PaperPublication updateItem) {
        for(int i = 0; i < paperPublications.size(); i++) {
            PaperPublication current = paperPublications.get(i);
            if (current.equals(updateItem)) {
                paperPublications.set(i, updateItem);
                return;
            }
        }
    }

    @Override
    public void display() {
        paperPublications.forEach(System.out::println);
    }

    public void displayGroupedByType() {
        Map<String, List<PaperPublication>> grouped = paperPublications.stream()
                .collect(Collectors.groupingBy(p -> p.getClass().getSimpleName()));

        grouped.forEach((type, items) -> {
            System.out.println("=== " + type + " ===");
            items.forEach(System.out::println);
            System.out.println();
        });
    }

    public List<PaperPublication> searchByTitle(String title) {
        return paperPublications.stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthor(String author) {
        return paperPublications.stream()
                .filter(p -> p instanceof Book)
                .map(p -> (Book) p)
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public void initializeTestData(int countPerType) {
        for (int i = 0; i < countPerType; i++) {
            addBook(PublicationGenerator.generateBook());
            addNewspaper(PublicationGenerator.generateNewspaper());
            addAlmanac(PublicationGenerator.generateAlmanac());
        }
    }
}
