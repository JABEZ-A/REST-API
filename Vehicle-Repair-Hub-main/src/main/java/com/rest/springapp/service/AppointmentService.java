package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Appointment;
import com.rest.springapp.model.User;
import com.rest.springapp.model.RepairShop;
import com.rest.springapp.repository.AppointmentRepository;
import com.rest.springapp.repository.UserRepository;
import com.rest.springapp.repository.RepairShopRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RepairShopRepository repairShopRepository;

    // Create or Update Appointment
    public Appointment saveAppointment(Appointment appointment) {
        System.out.println("Received Appointment: " + appointment);

        // Validate and set User
        if (appointment.getUser() == null || appointment.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        User user = userRepository.findById(appointment.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + appointment.getUser().getId()));
        System.out.println("Found User: " + user);

        // Validate and set RepairShop
        if (appointment.getRepairShop() == null || appointment.getRepairShop().getId() == null) {
            throw new RuntimeException("Repair Shop ID is required");
        }

        RepairShop repairShop = repairShopRepository.findById(appointment.getRepairShop().getId())
                .orElseThrow(() -> new RuntimeException("Repair Shop not found with ID: " + appointment.getRepairShop().getId()));
        System.out.println("Found Repair Shop: " + repairShop);

        // Set User and RepairShop before saving
        appointment.setUser(user);
        appointment.setRepairShop(repairShop);

        return appointmentRepository.save(appointment);
    }

    // Get Appointment by ID
    public Appointment getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));

        // Force Hibernate to load related lazy-loaded entities
        appointment.getUser().getId();
        appointment.getRepairShop().getId();

        return appointment;
    }

    // Delete Appointment by ID
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    // Get all appointments with pagination and sorting
    public Page<Appointment> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    // Find appointments by status with pagination and sorting
    public Page<Appointment> getAppointmentsByStatus(String status, Pageable pageable) {
        return appointmentRepository.findByStatus(status, pageable);
    }

    // Find appointments by service requested with pagination and sorting
    public Page<Appointment> getAppointmentsByServiceRequested(String serviceRequested, Pageable pageable) {
        return appointmentRepository.findByServiceRequested(serviceRequested, pageable);
    }
}
