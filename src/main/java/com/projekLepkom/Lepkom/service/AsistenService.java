package com.projekLepkom.Lepkom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekLepkom.Lepkom.dto.request.CreateAsistenRequest;
import com.projekLepkom.Lepkom.dto.request.UpdateAsistenRequest;
import com.projekLepkom.Lepkom.dto.response.AsistenResponse;

import com.projekLepkom.Lepkom.model.Asisten;
import com.projekLepkom.Lepkom.repository.AsistenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AsistenService {

    @Autowired
    private AsistenRepository asistenRepository;

    public Optional<Asisten> getAsistenId(String id){
        return asistenRepository.findById(id);
    }

    public void validateNotNull(CreateAsistenRequest createAsistenRequest){
        if (createAsistenRequest.getNama()==null) {
            throw new RuntimeException("nama tidak boleh kosong");
        }
        if (createAsistenRequest.getNpm()==null) {
            throw new RuntimeException("npm tidak boleh kosong");
        }
        if (createAsistenRequest.getEmail()==null) {
            throw new RuntimeException("email tidak boleh kosong");
        }
    }

    public void existsNpm(CreateAsistenRequest createAsistenRequest) {
        if (asistenRepository.existsByNpm(createAsistenRequest.getNpm())) {
            throw new RuntimeException("npm sudah ada");
        }
    }

    public Asisten createMaptoEntity(CreateAsistenRequest createAsistenRequest){
        Asisten asisten = new Asisten();
        asisten.setNama(createAsistenRequest.getNama());
        asisten.setNpm(createAsistenRequest.getNpm());
        asisten.setEmail(createAsistenRequest.getEmail());
        return asisten;
    }

    public void updateMaptoEntity(Asisten asisten, UpdateAsistenRequest updateAsistenRequest){
        existsNpm(updateAsistenRequest);
        asisten.setNama(updateAsistenRequest.getNama());
        asisten.setNpm(updateAsistenRequest.getNpm());
        asisten.setEmail(updateAsistenRequest.getEmail());
    }

    public AsistenResponse maptoResponse(Asisten asisten){
        AsistenResponse asistenResponse = new AsistenResponse();
        asistenResponse.setCreatedBy(asisten.getCreatedBy());
        asistenResponse.setCreatedAt(asisten.getCreatedAt());
        asistenResponse.setUpdatedBy(asisten.getUpdatedBy());
        asistenResponse.setUpdatedAt(asisten.getUpdatedAt());
        asistenResponse.setId(asisten.getId());
        asistenResponse.setNama(asisten.getNama());
        asistenResponse.setNpm(asisten.getNpm());
        asistenResponse.setEmail(asisten.getEmail());
        return asistenResponse;
    }

    @Transactional
    public void add(CreateAsistenRequest createAsistenRequest){
        validateNotNull(createAsistenRequest);
        existsNpm(createAsistenRequest);
        Asisten asisten = createMaptoEntity(createAsistenRequest);
        asistenRepository.saveAndFlush(asisten);
    }

    @Transactional
    public void edit(UpdateAsistenRequest updateAsistenRequest){
        if (updateAsistenRequest.getId().isEmpty()) {
            throw new RuntimeException("asisten id tidak boleh kosong");
        }
        asistenRepository.findById(updateAsistenRequest.getId())
            .ifPresentOrElse(existId -> {
            validateNotNull(updateAsistenRequest);
            existsNpm(updateAsistenRequest);
            asistenRepository.save(existId);
        }, () ->{
            throw new EntityNotFoundException("asisten id tidak ada");
            }
        );
    }

    @Transactional
    public List<AsistenResponse>getAll(){
        List<Asisten> asisten = asistenRepository.findAll();
        if (asistenRepository.findAll().isEmpty()) {
            throw new RuntimeException("data masih kosong");
        }
        return asisten.stream().map(this::maptoResponse).toList();
    }
    
    @Transactional
    public AsistenResponse getByid(String id){
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("tidak boleh kosong");
        }
        Asisten asisten = asistenRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("id tidak ada"));
        return maptoResponse(asisten);

    }

    @Transactional
    public void deleteById(String id){
        if (!asistenRepository.existsById(id)) {
            throw new EntityNotFoundException(id + " id tidak ditemukan");
        }
        asistenRepository.deleteById(id);
    }

    @Transactional
    public void deleteIds(List<String> ids){
        if (ids == null) {
            throw new RuntimeException("id tidak boleh kosong");
        }

        for (String idAsisten : ids) {
            deleteById(idAsisten);
        }
    }
}
