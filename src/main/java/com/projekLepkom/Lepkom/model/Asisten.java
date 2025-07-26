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
    name = "asisten",
    uniqueConstraints = {
		@UniqueConstraint(columnNames = {"npm"})
	}
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asisten extends BaseEntity {
    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "npm", nullable = false)
    private String npm;

    @Column(name = "email", nullable = false)
    private String email;


}
