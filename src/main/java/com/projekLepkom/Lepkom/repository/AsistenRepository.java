package com.projekLepkom.Lepkom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projekLepkom.Lepkom.model.Asisten;

@Repository
public interface AsistenRepository extends JpaRepository<Asisten, String>{
    boolean existsByNpm(String npm);
}
