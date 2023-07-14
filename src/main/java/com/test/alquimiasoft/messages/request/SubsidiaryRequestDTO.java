package com.test.alquimiasoft.messages.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubsidiaryRequestDTO implements Serializable {

    private Integer id;
    @NotBlank(message = "La provincia es obligatoria")
    private String province;
    @NotBlank(message = "La ciudad es obligatoria")
    private String city;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    private Boolean main;

}
