package com.projekLepkom.Lepkom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projekLepkom.Lepkom.model.Kelas;

@Repository
public interface KelasRepository extends JpaRepository<Kelas, String>{
    boolean existsByNama(String nama);
}
