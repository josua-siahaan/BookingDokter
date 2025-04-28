package com.TugasAkhir.DocIn.dto.validation;

import com.TugasAkhir.DocIn.model.JabatanDokter;
import com.TugasAkhir.DocIn.model.Pasien;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ValPasienDTO {

    @Pattern(regexp = "^([a-z0-9\\.]{8,16})$", message = "Format Huruf kecil ,numeric dan titik saja min 8 max 16 karakter, contoh : paulch.123")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w].{8,15}$", message = "Format minimal 1 angka, 1 huruf kecil, 1 huruf besar, 1 spesial karakter (_ \"Underscore\", - \"Hyphen\", # \"Hash\", atau $ \"Dollar\" atau @ \"At\") setelah 4 kondisi min 9 max 16 alfanumerik, contoh : aB4$12345")
    private String password;

    @Pattern(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$", message = "Format tidak valid contoh : user_name123@sub.domain.com")
    private String email;

    @Pattern(regexp = "^[\\w\\s]{5,50}$", message = "Alfanumerik Menimal 5 maks 50 karakter")
    private String alamat;

    @Pattern(regexp = "^(62|\\+62|0)8[0-9]{9,13}$",
            message = "Format No HP Tidak Valid , min 9 max 13 setelah angka 8, contoh : (0/62/+62)81111111")
    private String noHp;

    @Pattern(regexp = "^[\\w\\s]{5,50}$", message = "Alfanumerik Menimal 5 maks 50 karakter")
    private String nama;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("tanggal-lahir")
    private LocalDate tanggalLahir;

    public @Pattern(regexp = "^([a-z0-9\\.]{8,16})$", message = "Format Huruf kecil ,numeric dan titik saja min 8 max 16 karakter, contoh : paulch.123") String getUsername() {
        return username;
    }

    public void setUsername(@Pattern(regexp = "^([a-z0-9\\.]{8,16})$", message = "Format Huruf kecil ,numeric dan titik saja min 8 max 16 karakter, contoh : paulch.123") String username) {
        this.username = username;
    }

    public @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w].{8,15}$", message = "Format minimal 1 angka, 1 huruf kecil, 1 huruf besar, 1 spesial karakter (_ \"Underscore\", - \"Hyphen\", # \"Hash\", atau $ \"Dollar\" atau @ \"At\") setelah 4 kondisi min 9 max 16 alfanumerik, contoh : aB4$12345") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w].{8,15}$", message = "Format minimal 1 angka, 1 huruf kecil, 1 huruf besar, 1 spesial karakter (_ \"Underscore\", - \"Hyphen\", # \"Hash\", atau $ \"Dollar\" atau @ \"At\") setelah 4 kondisi min 9 max 16 alfanumerik, contoh : aB4$12345") String password) {
        this.password = password;
    }

    public @Pattern(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$", message = "Format tidak valid contoh : user_name123@sub.domain.com") String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$", message = "Format tidak valid contoh : user_name123@sub.domain.com") String email) {
        this.email = email;
    }

    public @Pattern(regexp = "^[\\w\\s]{5,50}$", message = "Alfanumerik Menimal 5 maks 50 karakter") String getAlamat() {
        return alamat;
    }

    public void setAlamat(@Pattern(regexp = "^[\\w\\s]{5,50}$", message = "Alfanumerik Menimal 5 maks 50 karakter") String alamat) {
        this.alamat = alamat;
    }

    public @Pattern(regexp = "^(62|\\+62|0)8[0-9]{9,13}$",
            message = "Format No HP Tidak Valid , min 9 max 13 setelah angka 8, contoh : (0/62/+62)81111111") String getNoHp() {
        return noHp;
    }

    public void setNoHp(@Pattern(regexp = "^(62|\\+62|0)8[0-9]{9,13}$",
            message = "Format No HP Tidak Valid , min 9 max 13 setelah angka 8, contoh : (0/62/+62)81111111") String noHp) {
        this.noHp = noHp;
    }

    public @Pattern(regexp = "^[\\w\\s]{5,50}$", message = "Alfanumerik Menimal 5 maks 50 karakter") String getNama() {
        return nama;
    }

    public void setNama(@Pattern(regexp = "^[\\w\\s]{5,50}$", message = "Alfanumerik Menimal 5 maks 50 karakter") String nama) {
        this.nama = nama;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}
