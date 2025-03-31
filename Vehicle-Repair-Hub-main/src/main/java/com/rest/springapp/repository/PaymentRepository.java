package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.Payment;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    // JPQL query to find payments by status
    @Query("SELECT p FROM Payment p WHERE p.status = ?1")
    List<Payment> findPaymentsByStatus(String status);

    // Pagination and Sorting
    Page<Payment> findByStatus(String status, Pageable pageable);
}
