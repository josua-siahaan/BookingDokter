package com.TugasAkhir.DocIn.controller;

import com.TugasAkhir.DocIn.config.OtherConfig;
import com.TugasAkhir.DocIn.dto.validation.ValDokterDTO;
import com.TugasAkhir.DocIn.model.Dokter;
import com.TugasAkhir.DocIn.service.DokterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dokter")
public class DokterController {
    @Autowired
    private DokterService dokterService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValDokterDTO valDokterDTO, HttpServletRequest request){
        return dokterService.save(dokterService.convertToEntity(valDokterDTO), request);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request){
        Pageable pageable = PageRequest.of(0, OtherConfig.getPageDefault(), Sort.by("id"));
        return dokterService.findAll(pageable, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ValDokterDTO valDokterDTO, HttpServletRequest request
    ){
        return dokterService.update(id, dokterService.convertToEntity(valDokterDTO), request);
    }

    @DeleteMapping
    public ResponseEntity<Object> delate(@PathVariable(value = "id") Long id, HttpServletRequest request){
        return dokterService.delete(id, request);
    }

    @GetMapping("/{sort}/{sortBy}/{page}")
    public ResponseEntity<Object> findByParam(
            @PathVariable(value = "sort") String sort,
            @PathVariable(value = "sortBy") String sortBy,
            @PathVariable(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request){
        Pageable pageable = null;
        sortBy = sortColumnByMap(sortBy);
        switch (sort){
            case "asc": pageable = PageRequest.of(page, size, Sort.by(sortBy)); break;
            default: pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        return dokterService.findByParam(pageable, column, value, request);
    }

    private String sortColumnByMap(String sortBy){
        switch (sortBy){
            case "nama":sortBy = "nama";break;
            case "jabatan":sortBy = "jabatan";break;
            default:sortBy = "id";break;
        }
        return sortBy;
    }
}
