package com.projekLepkom.Lepkom.dto.response;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JadwalPraktikumResponse extends BaseEntityResponse{
    private String asistenId;
    private String asistenNama;
    private String asistenNpm;
    private String asistenEmail;
    private String kelasId;
    private String kelasNama;
    private String ruanganId;
    private String ruanganNama;
    private String praktikumId;
    private String praktikumNama;
    private ZonedDateTime tanggalPraktikum;
}
