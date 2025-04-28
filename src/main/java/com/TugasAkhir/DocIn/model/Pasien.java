package com.TugasAkhir.DocIn.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MstPasien")
public class Pasien {

    @Id
    @Column(name = "IDPasien")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",length = 40,nullable = false,unique = true)
    private String username;

    @Column(name = "Password",length = 60,nullable = false,unique = true)
    private String password;

    @Column(name = "Nama")
    private String nama;

    @Column(name = "Email",length = 64,nullable = false,unique = true)
    private String email;

    @Column(name = "TanggalLahir")
    private LocalDate tanggalLahir;

    @Column(name = "Alamat",length = 255,nullable = false)
    private String alamat;

    @Column(name = "NoHp",length = 16,nullable = false,unique = true)
    private String noHp;

    @Column(name = "OTP",length = 60)
    private String otp;

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

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
