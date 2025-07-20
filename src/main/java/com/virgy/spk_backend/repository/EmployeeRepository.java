package com.virgy.spk_backend.repository;

import com.virgy.spk_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Untuk saat ini, metode CRUD dasar yang disediakan oleh JpaRepository
    // sudah cukup. Jika nanti butuh query kustom (misal: cari karyawan
    // berdasarkan nama), Anda bisa menambahkannya di sini seperti pada
    // UserRepository.
}