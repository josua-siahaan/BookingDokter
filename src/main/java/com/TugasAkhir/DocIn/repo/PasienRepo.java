package com.TugasAkhir.DocIn.repo;

import com.TugasAkhir.DocIn.model.Pasien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasienRepo extends JpaRepository<Pasien, Long> {
    public Page<Pasien> findByNamaContainsIgnoreCase(String nama, Pageable pageable);
    public List<Pasien> findByNamaContainsIgnoreCase(String nama);

    public Page<Pasien> findByAlamatContainsIgnoreCase(String alamat, Pageable pageable);

}
