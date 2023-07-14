package com.test.alquimiasoft.messages.response;

import com.test.alquimiasoft.messages.request.SubsidiaryRequestDTO;
import com.test.alquimiasoft.util.TypeIdentification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFullResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String typeIdentification;

    private String numberIdentification;

    private String name;

    private String email;

    private String cellphone;

    // fixed later
    private List<SubsidiaryRequestDTO> subsidiaries;

    public CustomerFullResponse(String id, String typeIdentification, String numberIdentification, String name, String email, String cellphone) {
        this.id = id;
        this.typeIdentification = typeIdentification;
        this.numberIdentification = numberIdentification;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
    }
}
