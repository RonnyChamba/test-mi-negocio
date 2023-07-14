package com.test.alquimiasoft.controller;

import com.test.alquimiasoft.messages.request.CustomerAtrributeDTO;
import com.test.alquimiasoft.messages.request.CustomerRequestDTO;
import com.test.alquimiasoft.messages.request.SubsidiaryRequestDTO;
import com.test.alquimiasoft.messages.response.CustomerFullResponse;
import com.test.alquimiasoft.service.AlquimiaSoftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Test API - Controller", description = "Example management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AlquimiaSoftController {


    private final AlquimiaSoftService alquimiaSoftService;

    @Operation(
            summary = "Retrieve all Customers by searchValue",
            description = "Get all Customer object by specifying  by searchValue param.")
    @GetMapping("/customers")
    public ResponseEntity<?> findAllCustomers(
            @RequestParam(defaultValue = "") String searchValue
    ) {

        return ResponseEntity.ok(alquimiaSoftService.findAllCutomers(searchValue));
    }

    @Operation(
            summary = "Save a Customer",
            description = "Save a Customer object by specifying  by requestDTO param."
    )
    @PostMapping("/customers")
    public ResponseEntity<?> saveCustomers(
            @Valid @RequestBody CustomerRequestDTO requestDTO
    ) {

        CustomerFullResponse response = alquimiaSoftService.saveCustomer(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Update a Customer",
            description = "Save a Customer object by specifying  by requestDTO param.")
    @PutMapping("/customers/{ide}")
    public ResponseEntity<?> updateCustomers(
            @Valid @RequestBody CustomerAtrributeDTO requestDTO,
            @PathVariable Integer ide
    ) {

        CustomerAtrributeDTO response = alquimiaSoftService.updateCustomer(
                ide,
                requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete a Customer by ide",
            description = "Delete a Customer object by specifying  by ide param")
    @DeleteMapping("/customers/{ide}")
    public ResponseEntity<?> deleteCustomers(
            @PathVariable Integer ide
    ) {
        alquimiaSoftService.deleteCustomer(ide);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Add a Subsidiary to Customer",
            description = "Save a Subsidiary for Customer.")
    @PostMapping("/customers/{ide}/subsidiary")
    public ResponseEntity<?> saveSubsidiaryToCustomers(
            @PathVariable Integer ide,
            @Valid @RequestBody SubsidiaryRequestDTO requestDTO
    ) {
        SubsidiaryRequestDTO response = alquimiaSoftService.addSubsidiaryForCustomer(ide, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Retrieve all Subsidiary to Customer",
            description = "Get all Subsidiary object by specifying  by ide param.")
    @GetMapping("/customers/{ide}/subsidiary")
    public ResponseEntity<?> findAllSubsidiaryToCustomers(
            @PathVariable Integer ide
    ) {

        return ResponseEntity.ok(alquimiaSoftService.findAllSubsidiariesForCustomer(ide));
    }


}
