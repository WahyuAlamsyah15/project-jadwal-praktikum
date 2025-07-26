package com.projekLepkom.Lepkom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekLepkom.Lepkom.dto.request.CreateKelasRequest;
import com.projekLepkom.Lepkom.dto.request.UpdateKelasRequest;
import com.projekLepkom.Lepkom.dto.response.KelasResponse;
import com.projekLepkom.Lepkom.model.Kelas;
import com.projekLepkom.Lepkom.repository.KelasRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KelasService {
    @Autowired
    private KelasRepository kelasRepository;

    public Optional<Kelas>getId(String id){
        return kelasRepository.findById(id);
    }


    public void validateNotNull(CreateKelasRequest createKelasRequest){
        if (createKelasRequest.getNama()==null) {
            throw new RuntimeException("kelas tidak boleh kosong");
        }
    }

    public void existKelas(CreateKelasRequest createKelasRequest){
        if (kelasRepository.existsByNama(createKelasRequest.getNama())){
            throw new RuntimeException("kelas sudah ada");
        }
    }

    public Kelas createMaptoEntity(CreateKelasRequest createKelasRequest){
        Kelas kelas = new Kelas();
        kelas.setNama(createKelasRequest.getNama());
        return kelas;
    }


    public void updateMaptoEntity(Kelas kelas, UpdateKelasRequest updateKelasRequest){
        kelas.setNama(updateKelasRequest.getNama());
    }

    public KelasResponse maptoResponse(Kelas kelas){
        KelasResponse kelasResponse = new KelasResponse();
        kelasResponse.setCreatedBy(kelas.getCreatedBy());
        kelasResponse.setCreatedAt(kelas.getCreatedAt());
        kelasResponse.setUpdatedBy(kelas.getUpdatedBy());
        kelasResponse.setUpdatedAt(kelas.getUpdatedAt());
        kelasResponse.setId(kelas.getId());
        kelasResponse.setNama(kelas.getNama());
        return kelasResponse;
    }

    @Transactional
    public void add(CreateKelasRequest createKelasRequest){
        validateNotNull(createKelasRequest);
        existKelas(createKelasRequest);
        Kelas kelas = createMaptoEntity(createKelasRequest);
        kelasRepository.saveAndFlush(kelas);
    }

    @Transactional
    public KelasResponse getDataById(String id){
        if (id == null) {
            throw new EntityNotFoundException(id + " tidak ditemukan");
        }
        Kelas kelas = kelasRepository.findById(id).orElseThrow(()-> new RuntimeException(id + " tidak ditemukan"));
        return maptoResponse(kelas);
    }

    @Transactional 
    public List<KelasResponse> getAll(){
        List<Kelas> kelasList = kelasRepository.findAll();

        List<KelasResponse> kelasResponses = new ArrayList<>();
        for (Kelas kelas : kelasList) {
            kelasResponses.add(maptoResponse(kelas));
        }
        return kelasResponses;
        // return kelas.stream().map(this::maptoResponse).toList();
    }

    @Transactional
    public Kelas edit( UpdateKelasRequest updateKelasRequest ){
        if (updateKelasRequest.getId()==null) {
            throw new RuntimeException("id tidak boleh kosong");
        }
        kelasRepository.findById(updateKelasRequest.getId());
        Kelas kelas = kelasRepository.saveAndFlush(createMaptoEntity(updateKelasRequest));
        return kelas;
    }    

    @Transactional
    public void deleteById(String id){
        if (id == null) {
            throw new RuntimeException("id tidak boleh kosong");
        }
        if (!kelasRepository.existsById(id)) {
            throw new EntityNotFoundException(" id tidak ditemukan");
        }
        kelasRepository.deleteById(id);
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
    
}
