package Interfaces;

import Models.Laptop;

import java.util.List;

public interface RepositoryImp<T> {
    List<T> getAll();
    T getById(int id);
    void add(T entity);
    void remove(int id);
}
