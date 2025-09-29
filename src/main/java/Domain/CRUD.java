package Domain;

public interface CRUD<T> {
    void addItem(T item);
    void deleteItem(T item);
    void updateItem(T item);
    void display();
}
