package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Payment;
import com.rest.springapp.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Create Payment
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Get Payment by ID
    public Payment getPaymentById(UUID id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // Get All Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Update Payment
    public Payment updatePayment(UUID id, Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isPresent()) {
            Payment updatedPayment = existingPayment.get();
            updatedPayment.setAmount(payment.getAmount());
            updatedPayment.setPayment_method(payment.getPayment_method());
            updatedPayment.setPayment_date(payment.getPayment_date());
            updatedPayment.setStatus(payment.getStatus());
            return paymentRepository.save(updatedPayment);
        }
        return null;
    }

    // Delete Payment
    public void deletePayment(UUID id) {
        paymentRepository.deleteById(id);
    }

    // Find Payments by Status (JPQL Query)
    public List<Payment> findPaymentsByStatus(String status) {
        return paymentRepository.findPaymentsByStatus(status);
    }

    // Get Payments with Pagination & Sorting
    public Page<Payment> getPaymentsWithPaginationAndSorting(String status, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentRepository.findByStatus(status, pageable);
    }

    public Page<Payment> getPaymentsWithPaginationAndSorting(String status, Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentsWithPaginationAndSorting'");
    }
}
