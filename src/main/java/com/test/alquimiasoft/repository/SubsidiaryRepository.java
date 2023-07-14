package com.test.alquimiasoft.repository;

import com.test.alquimiasoft.messages.request.SubsidiaryRequestDTO;
import com.test.alquimiasoft.model.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Integer> {

    boolean existsByProvinceIgnoreCaseAndCityIgnoreCaseAndAddressIgnoreCaseAndCustomerId(
            String province,
            String city,
            String address,
            Integer customerId
    );

    List<Subsidiary> findAllByCustomerId(Integer customerId);
}
