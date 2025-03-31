package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Review;
import com.rest.springapp.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByRepairShopId(Long repairShopId) {
        return reviewRepository.findByRepairShopId(repairShopId);
    }

    public Double getAverageRatingForRepairShop(Long repairShopId) {
        return reviewRepository.findAverageRatingByRepairShop(repairShopId);
    }

    public Page<Review> getAllReviews(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return reviewRepository.findAll(pageable);
    }

    public Review updateReview(Long id, Review reviewDetails) {
        return reviewRepository.findById(id).map(review -> {
            review.setRating(reviewDetails.getRating());
            review.setComment(reviewDetails.getComment());
            review.setUser(reviewDetails.getUser());
            review.setRepairShop(reviewDetails.getRepairShop());
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}