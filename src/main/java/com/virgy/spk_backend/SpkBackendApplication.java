package com.virgy.spk_backend;

import com.virgy.spk_backend.model.User;
import com.virgy.spk_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class SpkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpkBackendApplication.class, args);
	}

	// Bean ini akan dijalankan saat aplikasi startup
	@Bean
	public CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Cek apakah user 'user@mandom.com' sudah ada
			Optional<User> userOpt = userRepository.findByEmail("user@mandom.com");
			if (userOpt.isPresent()) {
				User user = userOpt.get();
				// Jika passwordnya masih 'admin' (belum di-hash), update dengan versi hash
				if (user.getPassword().equals("admin")) {
					user.setPassword(passwordEncoder.encode("admin"));
					userRepository.save(user);
					System.out.println("Password untuk user@mandom.com telah di-hash.");
				}
			}
		};
	}
}