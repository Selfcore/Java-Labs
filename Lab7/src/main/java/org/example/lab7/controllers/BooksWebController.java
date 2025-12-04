package org.example.lab7.controllers;

import org.example.lab7.models.Book;
import org.example.lab7.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class BooksWebController {
    private final BookService bookService;

    @Autowired
    public BooksWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer pages,
            Model model) {
        List<Book> books;
        if (title != null || author != null || pages != null) {
            books = bookService.filterBooks(title, author, pages);
        } else {
            books = bookService.getAll();
        }
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping
    public String createBook(@ModelAttribute Book book) {
        bookService.create(book);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "edit-form";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book updatedBook) {
        bookService.update(id, updatedBook);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/";
    }
}