package com.rest.springapp.service;

import com.rest.springapp.model.RepairShop;
import com.rest.springapp.repository.RepairShopRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RepairShopService {

    @Autowired
    private RepairShopRepository repairShopRepository;

    @Transactional
    public RepairShop saveRepairShop(RepairShop repairShop) {
        // Save or update the entity, Hibernate will handle the version field
        return repairShopRepository.save(repairShop);
    }

    public List<RepairShop> getAllRepairShops() {
        // Retrieve all repair shops
        return repairShopRepository.findAll();
    }

    public Page<RepairShop> getAllRepairShops(Pageable pageable) {
        // Retrieve all repair shops with pagination
        return repairShopRepository.findAll(pageable);
    }

    public Optional<RepairShop> getRepairShopById(Long id) {
        // Retrieve repair shop by id
        return repairShopRepository.findById(id);
    }

    @Transactional
    public RepairShop updateRepairShop(Long id, RepairShop repairShop) {
        // Fetch the existing RepairShop before updating
        RepairShop existingRepairShop = repairShopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RepairShop not found with id: " + id));

        // Update the fields as needed
        existingRepairShop.setName(repairShop.getName());
        existingRepairShop.setLocation(repairShop.getLocation());
        existingRepairShop.setServices(repairShop.getServices());
        existingRepairShop.setRating(repairShop.getRating());
        existingRepairShop.setMaintenanceTips(repairShop.getMaintenanceTips());
        existingRepairShop.setOwner(repairShop.getOwner());

        // Return the updated RepairShop
        return repairShopRepository.save(existingRepairShop);
    }

    public void deleteRepairShop(Long id) {
        // Delete the repair shop by id
        repairShopRepository.deleteById(id);
    }

    public List<RepairShop> findByName(String name) {
        // Search repair shops by name
        return repairShopRepository.findByName(name);
    }
}
