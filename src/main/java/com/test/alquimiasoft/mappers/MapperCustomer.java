package com.test.alquimiasoft.mappers;

import com.test.alquimiasoft.messages.request.CustomerAtrributeDTO;
import com.test.alquimiasoft.messages.request.CustomerRequestDTO;
import com.test.alquimiasoft.messages.request.SubsidiaryRequestDTO;
import com.test.alquimiasoft.messages.response.CustomerFullResponse;
import com.test.alquimiasoft.model.Customer;
import com.test.alquimiasoft.model.Subsidiary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MapperCustomer {

    MapperCustomer INSTANCE = Mappers.getMapper(MapperCustomer.class);

    Customer customerRequestDTOToCustomer(CustomerRequestDTO request);

    Subsidiary subsidiaryRequestDTOToSubsidiary(SubsidiaryRequestDTO request);

    CustomerFullResponse customerToCustomerFullResponse(Customer customer);

    CustomerAtrributeDTO customerToCustomerRequestDTO(Customer customer);

    SubsidiaryRequestDTO subsidiaryToSubsidiaryRequestDTO(Subsidiary subsidiary);

}
