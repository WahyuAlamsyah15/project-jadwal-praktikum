package com.projekLepkom.Lepkom.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projekLepkom.Lepkom.model.JadwalPraktikum;

@Repository
public interface JadwalPraktikumRespositoy extends JpaRepository<JadwalPraktikum, String>{
    
    boolean existsByAsistenIdAndTanggalPraktikum(String asistenId, ZonedDateTime tanggalPraktikum);
    boolean existsByKelasIdAndTanggalPraktikum(String kelasId, ZonedDateTime tanggalPraktikum);
    boolean existsByRuanganIdAndTanggalPraktikum(String ruanganId, ZonedDateTime tanggalPraktikum);
    List<JadwalPraktikum> findAllByOrderByTanggalPraktikumDesc();
    
}
