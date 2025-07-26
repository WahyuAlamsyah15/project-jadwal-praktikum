package com.projekLepkom.Lepkom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekLepkom.Lepkom.dto.request.CreateJadwalPraktikumRequest;
import com.projekLepkom.Lepkom.dto.request.UpdateJadwalPraktikumRequest;
import com.projekLepkom.Lepkom.dto.response.JadwalPraktikumResponse;
import com.projekLepkom.Lepkom.model.JadwalPraktikum;
import com.projekLepkom.Lepkom.repository.JadwalPraktikumRespositoy;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JadwalPraktikumService {
    @Autowired 
    private JadwalPraktikumRespositoy jadwalPraktikumRespositoy;

    @Autowired 
    private AsistenService asistenService;

    @Autowired 
    private KelasService kelasService;

    @Autowired 
    private PraktikumService praktikumService;

    @Autowired 
    private RuanganService ruanganService;

    public void validateNotNull(CreateJadwalPraktikumRequest createJadwalPraktikumRequest){
        if (createJadwalPraktikumRequest.getAsistenId()== null) {
            throw new RuntimeException("asisten id tidak boleh kosong");
        }
        if (createJadwalPraktikumRequest.getKelasId()== null) {
            throw new RuntimeException("kelas id tidak boleh kosong");
        }
        if (createJadwalPraktikumRequest.getRuanganId()== null) {
            throw new RuntimeException("ruangan id tidak boleh kosong");
        }
        if (createJadwalPraktikumRequest.getPraktikumId()== null) {
            throw new RuntimeException("ruangan id tidak boleh kosong");
        }
        if (createJadwalPraktikumRequest.getTanggalPraktikum()== null) {
            throw new RuntimeException("tanggal tidak boleh kosong");
        }
    }

    public void existsByAsistenIdAndTanggalPraktikum(CreateJadwalPraktikumRequest createJadwalPraktikumRequest){
        if (jadwalPraktikumRespositoy.existsByAsistenIdAndTanggalPraktikum(
            createJadwalPraktikumRequest.getAsistenId(), createJadwalPraktikumRequest.getTanggalPraktikum())) {
            throw new RuntimeException("asisten id sudah ada jadwal");
        }
    }

    public void existsByKelasIdAndTanggalPraktikum(CreateJadwalPraktikumRequest createJadwalPraktikumRequest){
        if (jadwalPraktikumRespositoy.existsByKelasIdAndTanggalPraktikum(
            createJadwalPraktikumRequest.getKelasId(), createJadwalPraktikumRequest.getTanggalPraktikum())) {
            throw new RuntimeException("kelas id sudah ada jadwal");
        }
    }

    public void existsByRuanganIdAndTanggalPraktikum(CreateJadwalPraktikumRequest createJadwalPraktikumRequest){
        if (jadwalPraktikumRespositoy.existsByRuanganIdAndTanggalPraktikum(
            createJadwalPraktikumRequest.getRuanganId(), createJadwalPraktikumRequest.getTanggalPraktikum())) {
            throw new RuntimeException("ruangan id sudah ada jadwal");
        }
    }

    public JadwalPraktikum createMaptoEntity(CreateJadwalPraktikumRequest createJadwalPraktikumRequest){
        JadwalPraktikum jPraktikum = new JadwalPraktikum();
        jPraktikum.setAsisten(asistenService.getAsistenId(createJadwalPraktikumRequest.getAsistenId()).orElseThrow());
        jPraktikum.setKelas(kelasService.getId(createJadwalPraktikumRequest.getKelasId()).orElseThrow());
        jPraktikum.setRuangan(ruanganService.getId(createJadwalPraktikumRequest.getRuanganId()).orElseThrow());
        jPraktikum.setPraktikum(praktikumService.getId(createJadwalPraktikumRequest.getPraktikumId()).orElseThrow());
        jPraktikum.setTanggalPraktikum(createJadwalPraktikumRequest.getTanggalPraktikum());
        return jPraktikum;
    }
    
    public void updateEntity(JadwalPraktikum jPraktikum, UpdateJadwalPraktikumRequest uJadwalPraktikumRequest){
        jPraktikum.setTanggalPraktikum(uJadwalPraktikumRequest.getTanggalPraktikum());
    }

    public JadwalPraktikumResponse mapToEntity(JadwalPraktikum jadwalPraktikum){
        JadwalPraktikumResponse jPraktikumResponse = new JadwalPraktikumResponse();
        jPraktikumResponse.setId(jadwalPraktikum.getId());
        jPraktikumResponse.setCreatedAt(jadwalPraktikum.getCreatedAt());
        jPraktikumResponse.setCreatedBy(jadwalPraktikum.getCreatedBy());
        jPraktikumResponse.setUpdatedAt(jadwalPraktikum.getUpdatedAt());
        jPraktikumResponse.setUpdatedBy(jadwalPraktikum.getUpdatedBy());
        jPraktikumResponse.setAsistenId(jadwalPraktikum.getAsisten().getId());
        jPraktikumResponse.setAsistenNama(jadwalPraktikum.getAsisten().getNama());
        jPraktikumResponse.setAsistenNpm(jadwalPraktikum.getAsisten().getNpm());
        jPraktikumResponse.setAsistenEmail(jadwalPraktikum.getAsisten().getEmail());
        jPraktikumResponse.setPraktikumId(jadwalPraktikum.getPraktikum().getId());
        jPraktikumResponse.setPraktikumNama(jadwalPraktikum.getPraktikum().getNama());
        jPraktikumResponse.setKelasId(jadwalPraktikum.getKelas().getId());
        jPraktikumResponse.setKelasNama(jadwalPraktikum.getKelas().getNama());
        jPraktikumResponse.setRuanganId(jadwalPraktikum.getRuangan().getId());
        jPraktikumResponse.setRuanganNama(jadwalPraktikum.getRuangan().getNama());
        jPraktikumResponse.setTanggalPraktikum(jadwalPraktikum.getTanggalPraktikum());
        return jPraktikumResponse;
    }

    @Transactional
    public void add(CreateJadwalPraktikumRequest cJadwalPraktikumRequest){
        validateNotNull(cJadwalPraktikumRequest);
        existsByAsistenIdAndTanggalPraktikum(cJadwalPraktikumRequest);
        existsByKelasIdAndTanggalPraktikum(cJadwalPraktikumRequest);
        existsByRuanganIdAndTanggalPraktikum(cJadwalPraktikumRequest);
        JadwalPraktikum jPraktikum = createMaptoEntity(cJadwalPraktikumRequest);
        jadwalPraktikumRespositoy.saveAndFlush(jPraktikum);
    }

    @Transactional 
    public void edit(UpdateJadwalPraktikumRequest uJadwalPraktikumRequest){
        if (uJadwalPraktikumRequest.getId()==null) {
            throw new EntityNotFoundException("id tidak ada");
        }
        jadwalPraktikumRespositoy.findById(uJadwalPraktikumRequest.getId())
            .ifPresentOrElse(existId ->{
                validateNotNull(uJadwalPraktikumRequest);
                existsByAsistenIdAndTanggalPraktikum(uJadwalPraktikumRequest);
                existsByKelasIdAndTanggalPraktikum(uJadwalPraktikumRequest);
                existsByRuanganIdAndTanggalPraktikum(uJadwalPraktikumRequest);
                jadwalPraktikumRespositoy.save(existId);
            },()->{
                throw new RuntimeException("data berhasil diedit");
                }
        );
    }

    @Transactional
    public List<JadwalPraktikumResponse>getAll(){
        List<JadwalPraktikum> jadwalPraktikums = jadwalPraktikumRespositoy.findAllByOrderByTanggalPraktikumDesc();
        return jadwalPraktikums.stream().map(this::mapToEntity).toList();
    }

    @Transactional
    public Optional<JadwalPraktikum>getById(String id){
        if (id == null) {
            throw new RuntimeException("id tidak boleh kosong");
        }
        if (!jadwalPraktikumRespositoy.findById(id).isPresent()) {
            throw new RuntimeException("id tidak ada");
        }
        return jadwalPraktikumRespositoy.findById(id);
    }

    @Transactional
    public void deleteById(String id){
        if (!jadwalPraktikumRespositoy.existsById(id)) {
            throw new EntityNotFoundException(id + " id tidak ditemukan");
        }
        jadwalPraktikumRespositoy.deleteById(id);
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
