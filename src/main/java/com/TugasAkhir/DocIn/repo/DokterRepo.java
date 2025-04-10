package com.TugasAkhir.DocIn.repo;

import com.TugasAkhir.DocIn.model.Dokter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DokterRepo extends JpaRepository<Dokter, Long> {
    public Page<Dokter> findByNamaContainsIgnoreCase(String nama, Pageable pageable);
//    public List<Dokter> findByNamaContainsIgnoreCase(String nama);

    public Page<Dokter> findByJabatanContainsIgnoreCase(String jabatan, Pageable pageable);
//    public List<Dokter> findByJabatanContainsIgnoreCase(String jabatan);
}
