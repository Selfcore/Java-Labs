package Domain;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void deleteItem(PaperPublication item) {
        if(item == null)
            return;

        paperPublications.remove(item);
    }

    @Override
    public void updateItem(PaperPublication item) {

    }

    @Override
    public void display() {
        paperPublications.forEach(System.out::println);
    }

    public List<PaperPublication> searchByTitle(String title) {
        return paperPublications.stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
}
