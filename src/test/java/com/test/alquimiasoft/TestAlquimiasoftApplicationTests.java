package com.test.alquimiasoft;

import com.test.alquimiasoft.data.MockData;
import com.test.alquimiasoft.model.Customer;
import com.test.alquimiasoft.repository.CustomerRepository;
import com.test.alquimiasoft.repository.SubsidiaryRepository;
import com.test.alquimiasoft.service.AlquimiaSoftService;
import com.test.alquimiasoft.service.AlquimiaSoftServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestAlquimiasoftApplicationTests {


    CustomerRepository customerRepository;

    SubsidiaryRepository subsidiaryRepository;

    AlquimiaSoftService alquimiaSoftService;


    @BeforeEach
    void setUp() {

        customerRepository = mock(CustomerRepository.class);
        subsidiaryRepository = mock(SubsidiaryRepository.class);
        alquimiaSoftService = new AlquimiaSoftServiceImpl(customerRepository, subsidiaryRepository);
    }

    @Test
    void testRelationshipCustomer() {

        List<Customer> customers = List.of(MockData.CUSTOMER_1, MockData.CUSTOMER_2, MockData.CUSTOMER_3, MockData.CUSTOMER_4);

        when(customerRepository.fetchCustomer("")).thenReturn(customers);

        assertEquals(1, (int) alquimiaSoftService.findAllCutomers("")
                .stream()
                .filter(item -> item.getNumberIdentification().equalsIgnoreCase("1723774640")).count());


        int countSubsidiaries = alquimiaSoftService.findAllCutomers("")
                .stream()
                .filter(item -> item.getNumberIdentification().equalsIgnoreCase("1723774640"))
                .collect(Collectors.toList()).get(0).getSubsidiaries().size();

        assertEquals(0, countSubsidiaries);

        MockData.CUSTOMER_1.setSubsidiaries(List.of(MockData.SUBSIDIARY_1, MockData.SUBSIDIARY_2));

        int newSizeSub = alquimiaSoftService.findAllCutomers("")
                .stream()
                .filter(item -> item.getNumberIdentification().equalsIgnoreCase("1723774640"))
                .collect(Collectors.toList()).get(0).getSubsidiaries().size();

        assertEquals(2, newSizeSub);

        assertEquals("Azuay", alquimiaSoftService.findAllCutomers("")
                .stream()
                .filter(item -> item.getNumberIdentification().equalsIgnoreCase("1723774640"))
                .collect(Collectors.toList()).get(0).getSubsidiaries().get(0).getProvince());


    }


}
