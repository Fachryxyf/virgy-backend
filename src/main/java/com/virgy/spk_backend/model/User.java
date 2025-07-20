package com.virgy.spk_backend.model;

import jakarta.persistence.*;

@Entity // Menandakan bahwa kelas ini adalah sebuah entitas JPA (terhubung ke tabel DB)
@Table(name = "users") // Secara eksplisit menentukan nama tabel di database
public class User {

    @Id // Menandakan bahwa field ini adalah primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mengonfigurasi cara primary key dibuat (auto-increment)
    private Long id;

    @Column(unique = true, nullable = false) // Menandakan kolom, harus unik dan tidak boleh null
    private String email;

    @Column(nullable = false) // Kolom ini tidak boleh null
    private String password;

    // Getters and Setters (bisa di-generate otomatis oleh IDE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}