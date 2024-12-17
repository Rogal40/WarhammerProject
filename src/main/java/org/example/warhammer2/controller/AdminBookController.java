package org.example.warhammer2.controller;

import org.example.warhammer2.entity.Book;
import org.example.warhammer2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/books")
public class AdminBookController {

    @Autowired
    private BookService bookService;

    // Показать список всех книг
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "admin-books";
    }

    // Показать форму для добавления книги
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // Обработать добавление книги
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/admin/books";
    }

    // Показать форму для редактирования книги
    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "edit-book";
    }

    // Обновить книгу
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id); // Обновляем ID
        bookService.saveBook(book);
        return "redirect:/admin/books";
    }

    // Удалить книгу
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }
}
