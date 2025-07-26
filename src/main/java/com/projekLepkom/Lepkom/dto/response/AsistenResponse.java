package com.projekLepkom.Lepkom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsistenResponse extends BaseEntityResponse {
    private String nama;
    private String npm;
    private String email;
    
}
