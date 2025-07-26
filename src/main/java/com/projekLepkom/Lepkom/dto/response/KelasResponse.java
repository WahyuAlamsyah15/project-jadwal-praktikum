package com.projekLepkom.Lepkom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KelasResponse extends BaseEntityResponse {

    private String Id;
    private String nama;
}
