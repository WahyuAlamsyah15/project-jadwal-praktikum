package com.projekLepkom.Lepkom.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "praktikum",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nama"}) 
    } 
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Praktikum extends BaseEntity {
    @Column(name = "nama", nullable = false)
    private String nama;

}
