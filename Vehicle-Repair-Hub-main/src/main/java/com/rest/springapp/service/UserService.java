package com.rest.springapp.service;

import com.rest.springapp.model.User;
import com.rest.springapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    // 🔹 Get user by email (JPQL)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔹 Get all users (Pagination and Sorting)
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllUsers(pageable);
    }

    // 🔹 Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 🔹 Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 🔹 Update an existing user
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 🔹 Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 🔹 Get users with pagination and sorting
    public Page<User> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String direction) {
        // Create the Sort object based on direction (ascending or descending)
        Sort sort = direction.equalsIgnoreCase("asc") 
                    ? Sort.by(sortBy).ascending() 
                    : Sort.by(sortBy).descending();
        
        // Create the Pageable object with pagination and sorting
        Pageable pageable = PageRequest.of(page, size, sort);
        
        // Fetch the users with pagination and sorting
        return userRepository.findAllUsers(pageable);
    }
}
