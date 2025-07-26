package com.projekLepkom.Lepkom.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekLepkom.Lepkom.dto.request.CreatePraktikumRequest;
import com.projekLepkom.Lepkom.dto.request.UpdatePraktikumlRequest;
import com.projekLepkom.Lepkom.dto.response.PraktikumResponse;
import com.projekLepkom.Lepkom.model.Praktikum;
import com.projekLepkom.Lepkom.repository.PraktikumRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PraktikumService {
    @Autowired
    private PraktikumRepository praktikumRepository;

    public Optional<Praktikum>getId(String id){
        return praktikumRepository.findById(id);
    }

    public void validateNotNull(CreatePraktikumRequest createJadwalRequest){
        if (createJadwalRequest.getNama()== null) {
            throw new RuntimeException("nama tidak boleh kosong");
        }
        
    }

    public void existsNama(CreatePraktikumRequest createPraktikumRequest){
        if (praktikumRepository.existsByNama(createPraktikumRequest.getNama())) {
            throw new RuntimeException("nama sudah ada");
        }
    }

    public Praktikum createMaptoEntity(CreatePraktikumRequest createPraktikumRequest){
        Praktikum praktikum = new Praktikum();
        praktikum.setNama(createPraktikumRequest.getNama());
        return praktikum;
    }

    public void updateMaptoEntity(Praktikum praktikum, UpdatePraktikumlRequest updateJadwalRequest){
        praktikum.setNama(updateJadwalRequest.getNama());
    }

    public PraktikumResponse maptoResponse(Praktikum jadwal){
        PraktikumResponse praktikumResponse = new PraktikumResponse();
        praktikumResponse.setCreatedBy(jadwal.getCreatedBy());
        praktikumResponse.setCreatedAt(jadwal.getCreatedAt());
        praktikumResponse.setId(jadwal.getId());
        praktikumResponse.setNama(jadwal.getNama());
        praktikumResponse.setUpdatedBy(jadwal.getUpdatedBy());
        praktikumResponse.setUpdatedAt(jadwal.getUpdatedAt());
        return praktikumResponse;
    }
    
    @Transactional
    public PraktikumResponse getDataById(String id) {
        Praktikum praktikum = praktikumRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(id + " tidak ditemukan"));
        return maptoResponse(praktikum);
    }
  

    @Transactional
    public void add(CreatePraktikumRequest createJadwalRequest) {
        validateNotNull(createJadwalRequest);
        existsNama(createJadwalRequest);
        Praktikum praktikum = createMaptoEntity(createJadwalRequest);
        praktikumRepository.saveAndFlush(praktikum);
    }

    @Transactional
    public void edit(UpdatePraktikumlRequest updateJadwalRequest){
        if (updateJadwalRequest.getId()== null) {
            throw new RuntimeException("id tidak boleh kosong");
        }
        praktikumRepository.findById(updateJadwalRequest.getId())
            .ifPresentOrElse(existingJadwal -> {
            validateNotNull(updateJadwalRequest);
            existsNama(updateJadwalRequest);
            createMaptoEntity(updateJadwalRequest);
            praktikumRepository.saveAndFlush(existingJadwal);
        }, () -> {
            throw new RuntimeException("id tidak ada");
        }); 
    }

    

    @Transactional
    public void deleteById(String id){
        if (!praktikumRepository.existsById(id)) {
            throw new RuntimeException(id + " id tidak ditemukan");
        }
        praktikumRepository.deleteById(id);
    }

    @Transactional
    public void deleteIds(List<String> ids){
        if (ids == null) {
            throw new RuntimeException("id tidak boleh kosong");
        }

        for (String id : ids) {
            deleteById(id);
        }
    }

    @Transactional
    public List<PraktikumResponse> getAll() {
        List<Praktikum> praktikums = praktikumRepository.findAll();
        if (praktikums.isEmpty()) {
            throw new RuntimeException("Tidak ada jadwal yang ditemukan");
        }
        return praktikums.stream().map(this::maptoResponse).toList(); 
    }
    
}
