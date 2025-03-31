package com.rest.springapp.repository;

import com.rest.springapp.model.RepairShop;
import com.rest.springapp.repository.RepairShopRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairShopRepository extends JpaRepository<RepairShop, Long> {
    @Query("SELECT r FROM RepairShop r WHERE r.location = :location")
    List<RepairShop> findByLocation(String location);

    // Corrected method signature with Pageable for pagination support
    Page<RepairShop> findAll(Pageable pageable);

    List<RepairShop> findByName(String name);
}
