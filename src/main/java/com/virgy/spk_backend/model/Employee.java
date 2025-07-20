package com.virgy.spk_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private int kinerja;
    private int kreativitas;
    private int inovasi;
    private int absensi;
    private int kedisiplinan;
    private double perilaku;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String photo;

    public Employee() {
        // Ini adalah penanda kita. Pesan ini akan tercetak di log
        // untuk setiap baris data karyawan yang dibaca dari database.
        System.out.println(">>> KONSTRUKTOR EMPLOYEE.JAVA VERSI BARU (DENGAN STRING PHOTO) DIPANGGIL <<<");
    }

    // Getters and Setters (bisa di-generate otomatis oleh IDE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKinerja() {
        return kinerja;
    }

    public void setKinerja(int kinerja) {
        this.kinerja = kinerja;
    }

    public int getKreativitas() {
        return kreativitas;
    }

    public void setKreativitas(int kreativitas) {
        this.kreativitas = kreativitas;
    }

    public int getInovasi() {
        return inovasi;
    }

    public void setInovasi(int inovasi) {
        this.inovasi = inovasi;
    }

    public int getAbsensi() {
        return absensi;
    }

    public void setAbsensi(int absensi) {
        this.absensi = absensi;
    }

    public int getKedisiplinan() {
        return kedisiplinan;
    }

    public void setKedisiplinan(int kedisiplinan) {
        this.kedisiplinan = kedisiplinan;
    }

    public double getPerilaku() {
        return perilaku;
    }

    public void setPerilaku(double perilaku) {
        this.perilaku = perilaku;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}