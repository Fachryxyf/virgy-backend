package com.virgy.spk_backend.dto;

// Kelas ini untuk menampung data response login (JWT token)
public class AuthResponse {
    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}