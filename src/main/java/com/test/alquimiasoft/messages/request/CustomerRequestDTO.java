package com.test.alquimiasoft.messages.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO extends CustomerAtrributeDTO {
    @NotNull(message = "La sucursal es obligatoria")
    private SubsidiaryRequestDTO subsidiary;

}
