package com.test.alquimiasoft.service;

import com.test.alquimiasoft.exception.BadRequestException;
import com.test.alquimiasoft.exception.DuplicatedResourceException;
import com.test.alquimiasoft.exception.NotFoundException;
import com.test.alquimiasoft.mappers.MapperCustomer;
import com.test.alquimiasoft.messages.request.CustomerAtrributeDTO;
import com.test.alquimiasoft.messages.request.CustomerRequestDTO;
import com.test.alquimiasoft.messages.request.SubsidiaryRequestDTO;
import com.test.alquimiasoft.messages.response.CustomerFullResponse;
import com.test.alquimiasoft.model.Customer;
import com.test.alquimiasoft.model.Subsidiary;
import com.test.alquimiasoft.repository.CustomerRepository;
import com.test.alquimiasoft.repository.SubsidiaryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlquimiaSoftServiceImpl implements AlquimiaSoftService {

    private static final Logger LOG = LoggerFactory.getLogger(AlquimiaSoftServiceImpl.class);


    private final CustomerRepository customerRepository;

    private final SubsidiaryRepository subsidiaryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CustomerFullResponse> findAllCutomers(String searchValue) {

        LOG.info("Value  searchValue: {}", searchValue);

        List<Customer> customers = customerRepository.fetchCustomer(searchValue);

        LOG.info("Value  size customers: {}", customers.size());

        return customers
                .stream()
                .map(MapperCustomer.INSTANCE::customerToCustomerFullResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerFullResponse saveCustomer(CustomerRequestDTO customerRequestDTO) {

        LOG.info("Value customerRequestDTO: {}", customerRequestDTO);

        if (customerRepository.existsByNumberIdentification(customerRequestDTO.getNumberIdentification())) {

            throw new DuplicatedResourceException("The customer already exists");
        }

        try {

            Customer customer = MapperCustomer.INSTANCE.customerRequestDTOToCustomer(customerRequestDTO);
            Subsidiary subsidiary = MapperCustomer.INSTANCE.subsidiaryRequestDTOToSubsidiary(customerRequestDTO.getSubsidiary());

            // when the customer is created, the first subsidiary is created as a matrix
            subsidiary.setMain(true);
            customer.addSubsidiary(subsidiary);


            Customer customerSaveToDB = customerRepository.save(customer);

            LOG.info("Value Ide customerSaveToDB: {}", customerSaveToDB.getId());

            return MapperCustomer.INSTANCE
                    .customerToCustomerFullResponse(customerSaveToDB);
        } catch (DataAccessException e) {
            LOG.error("Error saveCustomer: {}", e.getMessage());
            throw new RuntimeException("Error to save customer in Data Base");
        }
    }

    @Override
    @Transactional
    public CustomerAtrributeDTO updateCustomer(Integer id,
                                               CustomerAtrributeDTO customerRequestDTO) {

        LOG.info("Value ide  customer: {}", id);

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The customer does not exist"));
        try {

            // verify if the number identification is already registered in another customer and is different to the current customer
            if (customerRepository.existsByNumberIdentificationAndIdNot(
                    customerRequestDTO.getNumberIdentification(), customer.getId())) {
                throw new DuplicatedResourceException("The number identification is already registered in another customer");
            }

            // update customer
            customer.setNumberIdentification(customerRequestDTO.getNumberIdentification());
            customer.setCellphone(customerRequestDTO.getCellphone());
            customer.setName(customerRequestDTO.getName());
            customer.setEmail(customerRequestDTO.getEmail());
            customer.setTypeIdentification(customerRequestDTO.getTypeIdentification().toString());

            return MapperCustomer.INSTANCE
                    .customerToCustomerRequestDTO(customerRepository.save(customer));

        } catch (DataAccessException e) {
            LOG.error("Error updateCustomer: {}", e.getMessage());
            throw new RuntimeException("Error to update customer in Data Base");
        }

    }

    @Override
    @Transactional
    public void deleteCustomer(Integer id) {


        LOG.info("Value ide  customer: {}", id);

        try {
            if (!customerRepository.existsById(id)) {
                throw new NotFoundException("The customer does not exist");
            }
            customerRepository.deleteById(id);

        } catch (DataAccessException e) {
            LOG.error("Error deleteCustomer: {}", e.getMessage());
            throw new RuntimeException("Error to delete customer in Data Base");
        }

    }

    @Override
    public SubsidiaryRequestDTO addSubsidiaryForCustomer(Integer id, SubsidiaryRequestDTO requestDTO) {

        LOG.info("Value ide  customer: {}", id);

        try {

            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("The customer does not exist"));

            if (
                    subsidiaryRepository.existsByProvinceIgnoreCaseAndCityIgnoreCaseAndAddressIgnoreCaseAndCustomerId(
                            requestDTO.getProvince(), requestDTO.getCity(), requestDTO.getAddress(), customer.getId()
                    )
            ) {
                throw new DuplicatedResourceException("The subsidiary already exists for this customer");
            }

            Subsidiary subsidiary = MapperCustomer.INSTANCE.subsidiaryRequestDTOToSubsidiary(requestDTO);

            // from the second subsidiary, it is not a matrix
            subsidiary.setMain(false);
            subsidiary.setCustomer(customer);

            return MapperCustomer.INSTANCE.subsidiaryToSubsidiaryRequestDTO(subsidiaryRepository.save(subsidiary));

        } catch (DataAccessException e) {
            LOG.error("Error addSubsidiaryToCustomer: {}", e.getMessage());
            throw new RuntimeException("Error to add Subsidiary to customer in Data Base");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<SubsidiaryRequestDTO> findAllSubsidiariesForCustomer(Integer id) {

        LOG.info("Value ide  customer: {}", id);

        try {

            return subsidiaryRepository.findAllByCustomerId(id)
                    .stream()
                    .map(MapperCustomer.INSTANCE::subsidiaryToSubsidiaryRequestDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            LOG.error("Error findAllSubsidiaryForCustomer", e);
            throw new RuntimeException("Error to list Subsidiary to customer in Data Base");
        }
    }
}
