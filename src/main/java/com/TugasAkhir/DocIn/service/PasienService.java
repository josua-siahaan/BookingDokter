package com.TugasAkhir.DocIn.service;

import com.TugasAkhir.DocIn.config.OtherConfig;
import com.TugasAkhir.DocIn.core.IService;
import com.TugasAkhir.DocIn.dto.response.RespDokterDTO;
import com.TugasAkhir.DocIn.dto.response.RespPasienDTO;
import com.TugasAkhir.DocIn.dto.validation.ValDokterDTO;
import com.TugasAkhir.DocIn.dto.validation.ValPasienDTO;
import com.TugasAkhir.DocIn.handler.GlobalResponse;
import com.TugasAkhir.DocIn.handler.ResponseHandler;
import com.TugasAkhir.DocIn.model.Dokter;
import com.TugasAkhir.DocIn.model.Pasien;
import com.TugasAkhir.DocIn.repo.PasienRepo;
import com.TugasAkhir.DocIn.security.RequestCapture;
import com.TugasAkhir.DocIn.util.LoggingFile;
import com.TugasAkhir.DocIn.util.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.TransactionScoped;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@TransactionScoped
public class PasienService implements IService<Pasien> {

    @Autowired
    private PasienRepo pasienRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination transformPagination;


    @Override
    public ResponseEntity<Object> save(Pasien pasien, HttpServletRequest request) {
        if (pasien == null){
            return ResponseEntity.badRequest().body("Data yang di isi kosong -- USM01FV0001");
        }
        try {
            pasienRepo.save(pasien);
        } catch (Exception e) {
            LoggingFile.print(pasien.getNama(), OtherConfig.getEnablePrint());
            LoggingFile.logException("PasienService", "save(Pasien pasien, HttpServletRequest request)--Line 38"+
                    RequestCapture.allRequest(request), e, OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDisimpan("USM01FE001", request);
//            return ResponseEntity.badRequest().body("Data Gagal Di Simpan");
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Data berhasil Disimpan --USM01FE0001" + request);
    }

    @Override
    public ResponseEntity<Object> update(Long id, Pasien pasien, HttpServletRequest request) {
        if (pasien == null){
            return ResponseEntity.badRequest().body("Data yang di isi kosong -- USM01FV0011");
        }
        try {
            Optional<Pasien> optionalPasien = pasienRepo.findById(id);
            if (!optionalPasien.isPresent()){
                return ResponseEntity.badRequest().body("Data dengan id " + id + " tidak di temukan -- USM01FV012");
            }
            Pasien nextPasien = optionalPasien.get();
            nextPasien.setNama(pasien.getNama());
            nextPasien.setAlamat(pasien.getAlamat());
            nextPasien.setEmail(pasien.getEmail());
            nextPasien.setNoHp(pasien.getNoHp());
        } catch (Exception e) {
            LoggingFile.print(pasien.getNama(), OtherConfig.getEnablePrint());
            LoggingFile.logException("DokterService", "update(Long id, Pasien pasien, HttpServletRequest request)--Line 65"+
                    RequestCapture.allRequest(request), e, OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDiubah("USM01FE001", request);
//            return ResponseEntity.badRequest().body("Data Gagal Di Perbaharui");
        }
        return GlobalResponse.dataBerhasilDiubah(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Data berhasil Diubah --USM01FE0011" + request);
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Pasien> optionalPasien = pasienRepo.findById(id);
            if (!optionalPasien.isPresent()){
                return ResponseEntity.badRequest().body("Pasien dengan id " + id + " tidak terdaftar!!");
            }
            Pasien nextPasien = optionalPasien.get();
            pasienRepo.delete(nextPasien);
        } catch (Exception e) {
            return GlobalResponse.dataGagalDihapus("USM01FV021", request);
//            return ResponseEntity.badRequest().body("Data Gagal Di Hapus" + request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
//        return ResponseEntity.ok("Data berhasil di hapus" + request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Pasien> page = null;
        List<Pasien> list = null;

        page = pasienRepo.findAll(pageable);
        list = page.getContent();
        List<RespPasienDTO> respPasienDTOList = convertToRespPasienDTO(list);

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(respPasienDTOList, page,null, null), request);
//        return new ResponseHandler().handleResponse("Data Ditemukan", HttpStatus.OK, respPasienDTOList, null, request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Pasien> optionalPasien =null;
        try {
            optionalPasien = pasienRepo.findById(id);
            if (!optionalPasien.isPresent()){
                return ResponseEntity.badRequest().body("Data dengan id " + id + " tidak ditemukan");
            }
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("USM01FE041", request);
        }
        Pasien nextPasien = optionalPasien.get();
        return GlobalResponse.dataDitemukan(optionalPasien,request);
//        return ResponseEntity.ok("Data ditemukan!!\n" + nextPasien + " \n" + request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Pasien> page = null;
        List<Pasien> list = null;

        switch (columnName){
            case "nama": page = pasienRepo.findByNamaContainsIgnoreCase(value, pageable); break;
            case "alamat": page = pasienRepo.findByAlamatContainsIgnoreCase(value, pageable); break;
            default: page = pasienRepo.findAll(pageable);
        }
        list = page.getContent();
        List<RespPasienDTO> lt = convertToRespPasienDTO(list);
        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt, page, columnName, value),request);
    }

    public List<RespPasienDTO> convertToRespPasienDTO(List<Pasien> pasiens){
        List<RespPasienDTO> respPasienDTOList = modelMapper.map(pasiens, new TypeToken<List<RespPasienDTO>>(){}.getType());
        return respPasienDTOList;
    }

    public Pasien convertToEntity(ValPasienDTO valPasienDTODTO){
        Pasien pasien = modelMapper.map(valPasienDTODTO, Pasien.class);
        return pasien;
    }
}
