package com.virgy.spk_backend.repository;

import com.virgy.spk_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Menandakan bahwa ini adalah sebuah Spring Bean untuk akses data
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA akan otomatis membuat query untuk metode ini
    // berdasarkan namanya: "findByEmail" -> SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
}