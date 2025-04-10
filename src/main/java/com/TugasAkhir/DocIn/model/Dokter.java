package com.TugasAkhir.DocIn.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MstDokter")
public class Dokter {

    @Id
    @Column(name = "IDDokter")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",length = 40,nullable = false,unique = true)
    private String username;

    @Column(name = "Password",length = 60,nullable = false,unique = true)
    private String password;

    @Column(name = "Email",length = 64,nullable = false,unique = true)
    private String email;

    @Column(name = "Alamat",length = 255,nullable = false)
    private String alamat;

    @Column(name = "NoHp",length = 16,nullable = false,unique = true)
    private String noHp;

    @Column(name = "Nama" , length = 50, nullable = false)
    private String nama;

    @Column(name = "Jabatan" , length = 50, nullable = false)
    private String jabatan;

    @Column(name = "TanggalLahir")
    private LocalDate tanggalLahir;

    @Transient
    private Integer umur;

    @Column(name = "ProfilePicture")
    private String pathImage;

    @Column(name = "LinkProfilePicture")
    private String linkImage;

    @ManyToOne
    @JoinColumn(name = "IDPasien", foreignKey = @ForeignKey(name = "fk-dokter-to-pasien"))
    private Pasien pasien;

    @Column(name = "OTP",length = 60)
    private String otp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
}
