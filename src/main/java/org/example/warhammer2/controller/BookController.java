package org.example.warhammer2.controller;

import org.example.warhammer2.service.BookService;
import org.example.warhammer2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("reviews", reviewService.findByBookId(id));
        return "book-details";
    }

    @PostMapping("/{id}/reviews")
    public String addReview(@PathVariable Long id, @RequestParam String comment) {
        reviewService.addReview(id, comment);
        return "redirect:/books/" + id;
    }
}
