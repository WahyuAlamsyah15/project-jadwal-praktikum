package com.projekLepkom.Lepkom.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekLepkom.Lepkom.dto.request.CreateRuanganRequest;

import com.projekLepkom.Lepkom.dto.request.UpdateRuanganRequest;

import com.projekLepkom.Lepkom.dto.response.RuanganResponse;

import com.projekLepkom.Lepkom.model.Ruangan;

import com.projekLepkom.Lepkom.repository.RuanganRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RuanganService {
    @Autowired
    private RuanganRepository ruanganRepository;

    public Optional<Ruangan>getId(String id){
        return ruanganRepository.findById(id);
    }

    public void validateNotNull(CreateRuanganRequest createRuanganRequest){
        if (createRuanganRequest.getNama()== null) {
            throw new RuntimeException("nama tidak boleh kosong");
        }
        
    }

    public void existsNama(CreateRuanganRequest CreateRuanganRequest){
        if (ruanganRepository.existsByNama(CreateRuanganRequest.getNama())) {
            throw new RuntimeException("nama sudah ada");
        }
    }

    public Ruangan createMaptoEntity(CreateRuanganRequest CreateRuanganRequest){
        Ruangan Ruangan = new Ruangan();
        Ruangan.setNama(CreateRuanganRequest.getNama());
        return Ruangan;
    }

    public void updateMaptoEntity(Ruangan Ruangan, UpdateRuanganRequest updateJadwalRequest){
        Ruangan.setNama(updateJadwalRequest.getNama());
    }

    public RuanganResponse maptoResponse(Ruangan ruangan){
        RuanganResponse RuanganResponse = new RuanganResponse();
        RuanganResponse.setCreatedBy(ruangan.getCreatedBy());
        RuanganResponse.setCreatedAt(ruangan.getCreatedAt());
        RuanganResponse.setId(ruangan.getId());
        RuanganResponse.setNama(ruangan.getNama());
        RuanganResponse.setUpdatedBy(ruangan.getUpdatedBy());
        RuanganResponse.setUpdatedAt(ruangan.getUpdatedAt());
        return RuanganResponse;
    }
    
    @Transactional
    public RuanganResponse getDataById(String id) {
        Ruangan Ruangan = ruanganRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(id + " tidak ditemukan"));
        return maptoResponse(Ruangan);
    }
  

    @Transactional
    public void add(CreateRuanganRequest createRuanganRequest) {
        validateNotNull(createRuanganRequest);
        existsNama(createRuanganRequest);
        Ruangan Ruangan = createMaptoEntity(createRuanganRequest);
        ruanganRepository.saveAndFlush(Ruangan);
    }

    @Transactional
    public void edit(UpdateRuanganRequest updateJadwalRequest){
        if (updateJadwalRequest.getId()== null) {
            throw new RuntimeException("id tidak boleh kosong");
        }
        ruanganRepository.findById(updateJadwalRequest.getId())
            .ifPresentOrElse(existingJadwal -> {
            validateNotNull(updateJadwalRequest);
            existsNama(updateJadwalRequest);
            createMaptoEntity(updateJadwalRequest);
            ruanganRepository.saveAndFlush(existingJadwal);
        }, () -> {
            throw new RuntimeException("id tidak ada");
        }); 
    }

    

    @Transactional
    public void deleteById(String id){
        if (!ruanganRepository.existsById(id)) {
            throw new RuntimeException(id + " id tidak ditemukan");
        }
        ruanganRepository.deleteById(id);
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
    public List<RuanganResponse> getAll() {
        List<Ruangan> Ruangans = ruanganRepository.findAll();
        if (Ruangans.isEmpty()) {
            throw new RuntimeException("Tidak ada jadwal yang ditemukan");
        }
        return Ruangans.stream().map(this::maptoResponse).toList(); 
    }
    
}
