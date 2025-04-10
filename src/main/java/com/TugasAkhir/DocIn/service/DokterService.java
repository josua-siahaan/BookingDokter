package com.TugasAkhir.DocIn.service;

import com.TugasAkhir.DocIn.config.OtherConfig;
import com.TugasAkhir.DocIn.core.IService;
import com.TugasAkhir.DocIn.dto.response.RespDokterDTO;
import com.TugasAkhir.DocIn.dto.validation.ValDokterDTO;
import com.TugasAkhir.DocIn.handler.ResponseHandler;
import com.TugasAkhir.DocIn.model.Dokter;
import com.TugasAkhir.DocIn.repo.DokterRepo;
import com.TugasAkhir.DocIn.security.RequestCapture;
import com.TugasAkhir.DocIn.util.LoggingFile;
import com.TugasAkhir.DocIn.util.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
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
@Transactional
public class DokterService implements IService<Dokter> {

    @Autowired
    private DokterRepo dokterRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination transformPagination;

    @Override
    public ResponseEntity<Object> save(Dokter dokter, HttpServletRequest request) {
        if (dokter == null){
            return ResponseEntity.badRequest().body("Data yang di isi kosong -- USM01FV0001");
        }
        try {
            dokterRepo.save(dokter);
        } catch (Exception e) {
            LoggingFile.print(dokter.getNama(), OtherConfig.getEnablePrint());
            LoggingFile.logException("DokterService", "save(Dokter dokter, HttpServletRequest request)--Line 31"+
                    RequestCapture.allRequest(request), e, OtherConfig.getEnableLog());
            return ResponseEntity.badRequest().body("Data Gagal Di Simpan");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Data berhasil Disimpan --USM01FE0001" + request);
    }

    @Override
    public ResponseEntity<Object> update(Long id, Dokter dokter, HttpServletRequest request) {
        if (dokter == null){
            return ResponseEntity.badRequest().body("Data yang di isi kosong -- USM01FV0011");
        }
        try {
            Optional<Dokter> optionalDokter = dokterRepo.findById(id);
            if (!optionalDokter.isPresent()){
                return ResponseEntity.badRequest().body("Data dengan id " + id + " tidak di temukan -- USM01FV012");
            }
            Dokter nextDokter = optionalDokter.get();
            nextDokter.setNama(dokter.getNama());
            nextDokter.setAlamat(dokter.getAlamat());
            nextDokter.setEmail(dokter.getEmail());
            nextDokter.setNoHp(dokter.getNoHp());
        } catch (Exception e) {
            LoggingFile.print(dokter.getNama(), OtherConfig.getEnablePrint());
            LoggingFile.logException("DokterService", "update(Long id, Dokter dokter, HttpServletRequest request)--Line 59"+
                    RequestCapture.allRequest(request), e, OtherConfig.getEnableLog());
            return ResponseEntity.badRequest().body("Data Gagal Di Perbaharui");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Data berhasil Diubah --USM01FE0011" + request);
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Dokter> optionalDokter = dokterRepo.findById(id);
            if (!optionalDokter.isPresent()){
                return ResponseEntity.badRequest().body("Dokter dengan id " + id + " tidak terdaftar!!");
            }
            Dokter nextDokter = optionalDokter.get();
            dokterRepo.delete(nextDokter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Data Gagal Di Hapus" + request);
        }
        return ResponseEntity.ok("Data berhasil di hapus" + request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Dokter> page = null;
        List<Dokter> list = null;

        page = dokterRepo.findAll(pageable);
        list = page.getContent();
        List<RespDokterDTO> respDokterDTOList = convertToRespDokterDTO(list);

        return new ResponseHandler().handleResponse("Data Ditemukan", HttpStatus.OK, respDokterDTOList, null, request);
//        return ResponseEntity.status(HttpStatus.OK).body(transformPagination.transformPagination(respDokterDTOList, page, null, null)+ "\n"+request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Dokter> optionalDokter =null;
        try {
            optionalDokter = dokterRepo.findById(id);
            if (!optionalDokter.isPresent()){
                return ResponseEntity.badRequest().body("Data dengan id " + id + " tidak ditemukan");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Terjadi Kesalahan Line 109");
        }
        Dokter nextDokter = optionalDokter.get();
        return ResponseEntity.ok("Data ditemukan!!\n" + nextDokter + " \n" + request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Dokter> page = null;
        List<Dokter> list = null;

        switch (columnName){
            case "nama": page = dokterRepo.findByNamaContainsIgnoreCase(value, pageable); break;
            case "jabatan": page = dokterRepo.findByJabatanContainsIgnoreCase(value, pageable); break;
            default: page = dokterRepo.findAll(pageable);
        }
        list = page.getContent();
        List<RespDokterDTO> lt = convertToRespDokterDTO(list);
        return new ResponseHandler().handleResponse("Data ditemukan", HttpStatus.OK, lt, null, request);

    }


    public List<RespDokterDTO> convertToRespDokterDTO(List<Dokter> dokters){
        List<RespDokterDTO> respDokterDTOList = modelMapper.map(dokters, new TypeToken<List<RespDokterDTO>>(){}.getType());
        return respDokterDTOList;
    }

    public Dokter convertToEntity(ValDokterDTO valDokterDTO){
        Dokter dokter = modelMapper.map(valDokterDTO, Dokter.class);
        return dokter;
    }
}
