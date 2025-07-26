package com.projekLepkom.Lepkom.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekLepkom.Lepkom.dto.request.CreatePraktikumRequest;
import com.projekLepkom.Lepkom.dto.request.UpdatePraktikumlRequest;
import com.projekLepkom.Lepkom.service.PraktikumService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/praktikum")
@AllArgsConstructor
public class PraktikumController {
    @Autowired
    PraktikumService praktikumService;

    @PostMapping()
    public ResponseEntity <String> post(@RequestBody CreatePraktikumRequest createJadwalRequest) {
        try {
            praktikumService.add(createJadwalRequest);
            return ResponseEntity.ok().body("data berhasil ditambahkan");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity <?> getAll() {
        try {
            return ResponseEntity.ok().body(praktikumService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

    @PutMapping()
    public ResponseEntity <String> edit(@RequestBody UpdatePraktikumlRequest updateJadwalRequest) {
        try {
            praktikumService.edit(updateJadwalRequest);
            return ResponseEntity.ok().body("data berhasil diedit");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <?> getById(@PathVariable (name = "id") String id) {
        try {
            return ResponseEntity.ok().body(praktikumService.getDataById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity <?> deleteById(@PathVariable (name = "id") String id) {
        try {
            praktikumService.deleteById(id);
            return ResponseEntity.ok().body("data berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity <String> deleteAll(@RequestBody List<String>ids){
        try {
            praktikumService.deleteIds(ids);
            return ResponseEntity.ok("data berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
    
    

