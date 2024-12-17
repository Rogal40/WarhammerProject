package org.example.warhammer2.service;

import org.example.warhammer2.entity.Book;
import org.example.warhammer2.entity.Review;
import org.example.warhammer2.repository.BookRepository;
import org.example.warhammer2.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Review> findByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public void addReview(Long bookId, String comment) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Review review = new Review();
        review.setComment(comment);
        review.setBook(book);
        reviewRepository.save(review);
    }
}
