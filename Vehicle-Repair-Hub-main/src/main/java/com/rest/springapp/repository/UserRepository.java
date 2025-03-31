package com.rest.springapp.repository;

import com.rest.springapp.model.User;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 🔹 Find User by Email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    // 🔹 Get All Users with Pagination
    @Query("SELECT u FROM User u")
    Page<User> findAllUsers(Pageable pageable);

    // 🔹 JPQL Query for Updating User Details
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password, u.role = :role, u.phone = :phone, u.address = :address WHERE u.id = :id")
    int updateUser(
        @Param("id") Long id,
        @Param("name") String name,
        @Param("email") String email,
        @Param("password") String password,
        @Param("role") String role,
        @Param("phone") String phone,
        @Param("address") String address
    );

    // 🔹 JPQL Query for Deleting User by ID
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteUserById(@Param("id") Long id);
}
