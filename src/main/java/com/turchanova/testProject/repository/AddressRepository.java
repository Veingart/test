package com.turchanova.testProject.repository;

import com.turchanova.testProject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByLocation(String location);
}
