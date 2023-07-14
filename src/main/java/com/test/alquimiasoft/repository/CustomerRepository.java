package com.test.alquimiasoft.repository;

import com.test.alquimiasoft.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @Query("SELECT c FROM Customer c " +
            "JOIN  FETCH   c.subsidiaries su " +
            "WHERE c.numberIdentification LIKE %:searchValue% " +
            "OR c.name LIKE %:searchValue% " +
            "AND su.main = true " +
            "")
    List<Customer> fetchCustomer(
            @Param("searchValue") String identificationOrName
    );

    boolean existsByNumberIdentification(String numberIdentification);

    boolean existsByNumberIdentificationAndIdNot(String numberIdentification, Integer id);

}
