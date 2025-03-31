package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // JPQL Query to find reviews by user ID
    @Query("SELECT r FROM Review r WHERE r.user = ?1")
    List<Review> findByUserId(Long userId);

    // JPQL Query to find reviews by repair shop ID
    @Query("SELECT r FROM Review r WHERE r.repairShop = ?1")
    List<Review> findByRepairShopId(Long repairShopId);

    // JPQL Query to get average rating for a repair shop
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.repairShop = ?1")
    Double findAverageRatingByRepairShop(Long repairShopId);

    // Pagination and sorting support
    Page<Review> findAll(Pageable pageable);
}