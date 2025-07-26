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
public class BaseEntityResponse {
    private String id;
	private String createdBy;
    private ZonedDateTime createdAt;
    private String updatedBy;
    private ZonedDateTime updatedAt;
}
