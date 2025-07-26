package com.projekLepkom.Lepkom.model;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "jadwal_praktikum",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ruangan_id, tanggal_praktikum"}),
        @UniqueConstraint (columnNames = {"asisten_id, tanggal_praktikum"}),
        @UniqueConstraint (columnNames = {"kelas_id, tanggal_praktikum"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JadwalPraktikum extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "asisten_id", nullable = false)
    private Asisten asisten;

    @ManyToOne
    @JoinColumn(name = "ruangan_id", nullable = false)
    private Ruangan ruangan;

    @ManyToOne
    @JoinColumn(name = "kelas_id", nullable = false)
    private Kelas kelas;

    @OneToOne
    @JoinColumn(name = "praktikum_id", nullable = false)
    private Praktikum praktikum;

    @Column(name = "tanggal_praktikum", nullable = false)
    private ZonedDateTime tanggalPraktikum;
}
