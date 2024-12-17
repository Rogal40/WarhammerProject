package org.example.warhammer2.controller;

import org.example.warhammer2.entity.Review;
import org.example.warhammer2.entity.Book;
import org.example.warhammer2.entity.User;
import org.example.warhammer2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add/{bookId}")
    public String addReview(@PathVariable Long bookId, @RequestParam String comment) {
        // Используем новый метод из ReviewService
        reviewService.addReview(bookId, comment);
        return "redirect:/books/" + bookId;
    }
}
