package com.test.alquimiasoft.messages.request;

import com.test.alquimiasoft.util.TypeIdentification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAtrributeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "El tipo de identificación es obligatorio")
    private TypeIdentification typeIdentification;

    @NotBlank(message = "El número de identificación es obligatorio")
    @Size(min = 10, max = 15, message = "El número de identificación debe tener entre 10 y 15 caracteres")
    private String numberIdentification;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "El correo electrónico no es válido")
    private String email;

    private String cellphone;

}
