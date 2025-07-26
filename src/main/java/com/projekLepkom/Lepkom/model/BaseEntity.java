package com.projekLepkom.Lepkom.model;

import java.time.ZonedDateTime;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @UuidGenerator
    private String id;

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;
    
    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;
    
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
}
