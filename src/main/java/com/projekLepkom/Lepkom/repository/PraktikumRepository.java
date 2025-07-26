package com.projekLepkom.Lepkom.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.projekLepkom.Lepkom.model.Praktikum;

@Repository
public interface PraktikumRepository extends JpaRepository<Praktikum, String> {
    boolean existsByNama(String nama);
}