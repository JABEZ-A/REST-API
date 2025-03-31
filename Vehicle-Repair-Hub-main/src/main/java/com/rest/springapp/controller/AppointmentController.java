package com.rest.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Appointment;
import com.rest.springapp.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Create or update Appointment
    @PostMapping
    public ResponseEntity<Appointment> createOrUpdateAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    // Get Appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return appointment != null ? new ResponseEntity<>(appointment, HttpStatus.OK) : 
                                     new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete Appointment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all appointments with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Appointment>> getAllAppointments(@RequestParam int page, @RequestParam int size, 
    @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, sortOrder.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        Page<Appointment> appointments = appointmentService.getAllAppointments(pageable);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Get appointments by status with pagination
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Appointment>> getAppointmentsByStatus(@PathVariable String status, 
    @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Appointment> appointments = appointmentService.getAppointmentsByStatus(status, pageable);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Get appointments by service requested with pagination
    @GetMapping("/serviceRequested/{serviceRequested}")
    public ResponseEntity<Page<Appointment>> getAppointmentsByServiceRequested(@PathVariable String serviceRequested, 
        @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Appointment> appointments = appointmentService.getAppointmentsByServiceRequested(serviceRequested, pageable);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
