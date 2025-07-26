package com.projekLepkom.Lepkom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekLepkom.Lepkom.dto.request.CreateAsistenRequest;
import com.projekLepkom.Lepkom.dto.request.UpdateAsistenRequest;
import com.projekLepkom.Lepkom.service.AsistenService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/asisten")
public class AsistenController {
    
    @Autowired
    private AsistenService asistenService;

    @PostMapping()
    public ResponseEntity <String> post(@RequestBody CreateAsistenRequest createAsistenRequest) {
        try {
            asistenService.add(createAsistenRequest);
            return ResponseEntity.ok().body("data berhasil ditambahkan");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity <?> getAll() {
        try {
            return ResponseEntity.ok().body(asistenService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <?> getByid(@PathVariable (name = "id") String id) {
        try {
            return ResponseEntity.ok().body(asistenService.getByid(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity <?> edit(@RequestBody UpdateAsistenRequest updateAsistenRequest) {
        try {
            asistenService.edit(updateAsistenRequest);
            return ResponseEntity.ok().body("data berhasil di edit");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity <?> deleteIds(@RequestBody List<String>ids){
        try {
            asistenService.deleteIds(ids);
            return ResponseEntity.ok().body("data berhasil di hapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <?> deleteById(@PathVariable String id){
        try {
            asistenService.deleteById(id);
            return ResponseEntity.ok().body("data dengan "+id + " berhasil di hapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
