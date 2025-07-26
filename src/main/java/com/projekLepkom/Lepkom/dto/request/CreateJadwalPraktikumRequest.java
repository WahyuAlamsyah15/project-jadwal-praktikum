package com.projekLepkom.Lepkom.dto.request;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateJadwalPraktikumRequest {
    private String asistenId;
    private String kelasId;
    private String ruanganId;
    private String praktikumId;
    private ZonedDateTime tanggalPraktikum;
}
