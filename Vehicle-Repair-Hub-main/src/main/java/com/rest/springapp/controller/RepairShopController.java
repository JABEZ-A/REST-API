package com.rest.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.RepairShop;
import com.rest.springapp.service.RepairShopService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repairshops")
public class RepairShopController {

    @Autowired
    private RepairShopService repairShopService;

    @PostMapping
    public RepairShop createRepairShop(@RequestBody RepairShop repairShop) {
        return repairShopService.saveRepairShop(repairShop);
    }

    @GetMapping
    public List<RepairShop> getAllRepairShops() {
        return repairShopService.getAllRepairShops();
    }

    @GetMapping("/paged")
    public Page<RepairShop> getPagedRepairShops(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        // Determine the sort direction (ascending or descending)
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        // Create Pageable object with page, size, and sort
        Pageable pageable = PageRequest.of(page, size, sort);

        // Get paged results from service
        return repairShopService.getAllRepairShops(pageable);
    }

    @GetMapping("/{id}")
    public RepairShop getRepairShopById(@PathVariable Long id) {
        Optional<RepairShop> repairShop = repairShopService.getRepairShopById(id);
        return repairShop.orElseThrow(() -> new RuntimeException("RepairShop not found"));
    }

    @PutMapping("/{id}")
    public RepairShop updateRepairShop(@PathVariable Long id, @RequestBody RepairShop repairShop) {
        return repairShopService.updateRepairShop(id, repairShop);
    }

    @DeleteMapping("/{id}")
    public void deleteRepairShop(@PathVariable Long id) {
        repairShopService.deleteRepairShop(id);
    }

    // Custom JPQL operation: Find repair shops by name
    @GetMapping("/search")
    public List<RepairShop> findByName(@RequestParam String name) {
        return repairShopService.findByName(name);
    }
}
