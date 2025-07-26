package com.projekLepkom.Lepkom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekLepkom.Lepkom.dto.request.CreateKelasRequest;
import com.projekLepkom.Lepkom.dto.request.UpdateKelasRequest;
import com.projekLepkom.Lepkom.service.KelasService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/kelas")
@AllArgsConstructor
public class KelasController {

    @Autowired
    private KelasService kelasService;

    @PostMapping
    public ResponseEntity <String> add(@RequestBody CreateKelasRequest createKelasRequest) {
        try {
            kelasService.add(createKelasRequest);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("data berhasil dibuat");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity <String> putMethodName(@RequestBody UpdateKelasRequest updateKelasRequest) {
        try {
            kelasService.edit(updateKelasRequest);
            return ResponseEntity.ok().body("data berhasil diedit");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity <?> getAll() {
        try {
            return ResponseEntity.ok().body(kelasService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <?> getById(@PathVariable String id){
        try {
            return ResponseEntity.ok().body(kelasService.getDataById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <?> deleteById(@PathVariable String id){
        try {
            kelasService.deleteById(id);
            return ResponseEntity.ok().body("data berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity <?> deleteIds(@RequestBody List<String> ids){
        try {
            kelasService.deleteIds(ids);
            return ResponseEntity.ok().body("data berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
