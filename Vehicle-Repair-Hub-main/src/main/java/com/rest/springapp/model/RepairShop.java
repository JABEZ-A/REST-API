package com.rest.springapp.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "repair_shops")
public class RepairShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String services;
    private Double rating;
    private String maintenanceTips;
    private String owner;

    @OneToMany(mappedBy = "repairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "shop-appointments")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "repairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Prevents infinite recursion
    private List<Review> reviews;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getServices() { return services; }
    public void setServices(String services) { this.services = services; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getMaintenanceTips() { return maintenanceTips; }
    public void setMaintenanceTips(String maintenanceTips) { this.maintenanceTips = maintenanceTips; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
