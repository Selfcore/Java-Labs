package org.example.lab7.services;

import org.example.lab7.models.Book;
import org.example.lab7.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }


    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElse(null);
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedBook.getTitle());
                    existing.setAuthor(updatedBook.getAuthor());
                    existing.setGenre(updatedBook.getGenre());
                    existing.setDescription(updatedBook.getDescription());
                    existing.setPages(updatedBook.getPages());
                    return bookRepository.save(existing);
                })
                .orElse(null);
    }

    public List<Book> filterBooks(String title, String author, Integer pages) {
        List<Predicate<Book>> filters = new ArrayList<>();

        if (title != null && !title.isBlank()) {
            filters.add(b -> b.getTitle() != null && b.getTitle().toLowerCase().contains(title.toLowerCase()));
        }
        if (author != null && !author.isBlank()) {
            filters.add(b -> b.getAuthor() != null && b.getAuthor().toLowerCase().contains(author.toLowerCase()));
        }
        if (pages != null) {
            filters.add(b -> b.getPages() == pages);
        }

        return bookRepository.findAll().stream()
                .filter(book -> filters.stream().allMatch(pred -> pred.test(book)))
                .toList();
    }

    public boolean delete(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
