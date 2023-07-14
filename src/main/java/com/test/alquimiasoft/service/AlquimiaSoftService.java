package com.test.alquimiasoft.service;

import com.test.alquimiasoft.messages.request.CustomerAtrributeDTO;
import com.test.alquimiasoft.messages.request.CustomerRequestDTO;
import com.test.alquimiasoft.messages.request.SubsidiaryRequestDTO;
import com.test.alquimiasoft.messages.response.CustomerFullResponse;

import java.util.List;

public interface AlquimiaSoftService {

    List<CustomerFullResponse> findAllCutomers(String searchValue);

    CustomerFullResponse saveCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerAtrributeDTO updateCustomer(
            Integer id,
            CustomerAtrributeDTO customerAtrributeDTO);

    void deleteCustomer(Integer id);

    SubsidiaryRequestDTO addSubsidiaryForCustomer(Integer id,
                                  SubsidiaryRequestDTO requestDTO);


    List<SubsidiaryRequestDTO> findAllSubsidiariesForCustomer(Integer id);


}
