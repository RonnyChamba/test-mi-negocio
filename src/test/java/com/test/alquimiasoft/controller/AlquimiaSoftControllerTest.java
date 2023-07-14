package com.test.alquimiasoft.controller;

import com.google.gson.Gson;
import com.test.alquimiasoft.messages.request.CustomerRequestDTO;
import com.test.alquimiasoft.messages.request.SubsidiaryRequestDTO;
import com.test.alquimiasoft.messages.response.CustomerFullResponse;
import com.test.alquimiasoft.service.AlquimiaSoftService;
import com.test.alquimiasoft.util.TypeIdentification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AlquimiaSoftController.class)
class AlquimiaSoftControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AlquimiaSoftService alquimiaSoftService;

    @Test
    void shouldFindAllCustomers() throws Exception {

        List<CustomerFullResponse> toDoList = new ArrayList<>();
        toDoList.add(new CustomerFullResponse("1", "CEDULA", "123456789", "Juan", "", "", List.of()));
        toDoList.add(new CustomerFullResponse("2", "CEDULA", "987654321", "Pedro", "", "", List.of()));
        when(alquimiaSoftService.findAllCutomers("")).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andDo(print())
                .andExpect((jsonPath("$[0].numberIdentification").value("123456789")));
    }


    @Test
    void shouldProcessRequestSuccessfullySaveCustomer() throws Exception {

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setId(null);
        customerRequestDTO.setTypeIdentification(TypeIdentification.CEDULA);
        customerRequestDTO.setNumberIdentification("1723774640");
        customerRequestDTO.setName("Ronny");
        customerRequestDTO.setEmail("ronnychamba96@gmail.com");
        customerRequestDTO.setCellphone("0981806210");

        SubsidiaryRequestDTO subsidiaryRequestDTO = new SubsidiaryRequestDTO();
        subsidiaryRequestDTO.setId(null);
        subsidiaryRequestDTO.setAddress("Calle Larga");
        subsidiaryRequestDTO.setProvince("Azuay");
        subsidiaryRequestDTO.setCity("Cuenca");
        customerRequestDTO.setSubsidiary(subsidiaryRequestDTO);

        when(alquimiaSoftService.saveCustomer(customerRequestDTO)).thenReturn(
                new CustomerFullResponse(null,
                        "CEDULA", "1723774640",
                        "Ronny",
                        "ronnychamba96@gmail.com",
                        "0981806210"));

        String dataJson = new Gson().toJson(customerRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberIdentification").value("1723774640"))
                .andExpect(jsonPath("$.typeIdentification").value("CEDULA"))
                .andExpect(jsonPath("$.name").value("Ronny"))
                .andExpect(jsonPath("$.email").value("ronnychamba96@gmail.com"))
                .andExpect(jsonPath("$.cellphone").value("0981806210"))
                .andDo(print());
    }

    @Test
    void shouldProcessRequestSuccessfully() throws Exception {

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setId(null);
        customerRequestDTO.setTypeIdentification(TypeIdentification.CEDULA);
        customerRequestDTO.setNumberIdentification("1723774640");
        customerRequestDTO.setName("Ronny");
        customerRequestDTO.setEmail("ronnychamba96@gmail.com");
        customerRequestDTO.setCellphone("0981806210");

        SubsidiaryRequestDTO subsidiaryRequestDTO = new SubsidiaryRequestDTO();
        subsidiaryRequestDTO.setId(null);
        subsidiaryRequestDTO.setAddress("Calle Larga");
        subsidiaryRequestDTO.setProvince("Azuay");
        subsidiaryRequestDTO.setCity("Cuenca");
        customerRequestDTO.setSubsidiary(subsidiaryRequestDTO);

        when(alquimiaSoftService.saveCustomer(customerRequestDTO)).thenReturn(
                new CustomerFullResponse(null,
                        "CEDULA", "1723774640",
                        "Ronny",
                        "ronnychamba96@gmail.com",
                        "0981806210",
                        List.of(new SubsidiaryRequestDTO(null, "Azuay", "Cuenca", "Calle Larga", true))));

        String dataJson = new Gson().toJson(customerRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberIdentification").value("1723774640"))
                .andExpect(jsonPath("$.typeIdentification").value("CEDULA"))
                .andExpect(jsonPath("$.name").value("Ronny"))
                .andExpect(jsonPath("$.email").value("ronnychamba96@gmail.com"))
                .andExpect(jsonPath("$.cellphone").value("0981806210"))
                .andExpect(jsonPath("$.subsidiaries", hasSize(1)))
                .andExpect(jsonPath("$.subsidiaries[0].province").value("Azuay"))
                .andExpect(jsonPath("$.subsidiaries[0].city").value("Cuenca"))
                .andExpect(jsonPath("$.subsidiaries[0].address").value("Calle Larga"))
                .andExpect(jsonPath("$.subsidiaries[0].main").value(true))
                .andDo(print());
    }

    @Test
    void shouldReturnBadRequestOnInvalidInput() throws Exception {

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setId(null);
        customerRequestDTO.setTypeIdentification(TypeIdentification.CEDULA);
        customerRequestDTO.setNumberIdentification(null);
        customerRequestDTO.setName(null);
        customerRequestDTO.setEmail("ronnychamba96@gmail.com");
        customerRequestDTO.setCellphone("0981806210");
        customerRequestDTO.setSubsidiary(null);

        String dataJson = new Gson().toJson(customerRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.code").value("400"))
                .andDo(print());
    }

    @Test
    void shouldDeleteCustomer() throws Exception {

        doNothing().when(alquimiaSoftService).deleteCustomer(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNoContent())
                .andDo(print());

        verify(alquimiaSoftService, times(1)).deleteCustomer(1);


    }

}