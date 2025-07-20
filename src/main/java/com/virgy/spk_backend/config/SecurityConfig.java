package com.virgy.spk_backend.config;

import com.virgy.spk_backend.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // --- BEAN BARU UNTUK KONFIGURASI CORS ---
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Izinkan origin dari frontend React Anda
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "https://virgy.vercel.app/"));
        // Izinkan metode HTTP yang diperlukan
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Izinkan semua header
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Izinkan kredensial (penting untuk cookie/auth)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration); // Terapkan konfigurasi ini untuk path /api/**
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Menggunakan konfigurasi CORS yang sudah kita definisikan
                .cors(withDefaults())

                // ---> PASTIKAN BARIS INI ADA DAN TEPAT DI SINI <---
                // Menonaktifkan proteksi CSRF
                .csrf(csrf -> csrf.disable())

                // Mengatur otorisasi untuk setiap request
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll() // Izinkan endpoint login diakses publik
                        .anyRequest().authenticated() // Semua request lain butuh autentikasi
                )
                // Mengatur session management menjadi stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Menambahkan filter JWT kita ke dalam rantai filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}