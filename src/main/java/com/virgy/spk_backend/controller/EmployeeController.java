package com.virgy.spk_backend.controller;

import com.virgy.spk_backend.model.Employee;
import com.virgy.spk_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/employees") // Base path untuk semua endpoint di controller ini
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // GET: Mengambil semua data karyawan
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // POST: Menambah karyawan baru
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // GET: Mengambil satu karyawan berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Karyawan tidak ditemukan dengan id: " + id));
        return ResponseEntity.ok(employee);
    }

    // PUT: Memperbarui data karyawan berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Karyawan tidak ditemukan dengan id: " + id));

        employee.setNama(employeeDetails.getNama());
        employee.setKinerja(employeeDetails.getKinerja());
        employee.setKreativitas(employeeDetails.getKreativitas());
        employee.setInovasi(employeeDetails.getInovasi());
        employee.setAbsensi(employeeDetails.getAbsensi());
        employee.setKedisiplinan(employeeDetails.getKedisiplinan());
        employee.setPerilaku(employeeDetails.getPerilaku());
        employee.setPhoto(employeeDetails.getPhoto());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // DELETE: Menghapus karyawan berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Karyawan tidak ditemukan dengan id: " + id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }
}