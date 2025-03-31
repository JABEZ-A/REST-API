package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.springapp.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Pagination and sorting example
    Page<Appointment> findAll(Pageable pageable);

    // JPQL Query to find appointments by status
    @Query("SELECT a FROM Appointment a WHERE a.status = ?1")
    Page<Appointment> findByStatus(String status, Pageable pageable);

    // JPQL Query to find appointments by service requested
    @Query("SELECT a FROM Appointment a WHERE a.serviceRequested LIKE %?1%")
    Page<Appointment> findByServiceRequested(String serviceRequested, Pageable pageable);
}
