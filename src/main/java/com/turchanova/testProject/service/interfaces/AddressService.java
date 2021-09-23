package com.turchanova.testProject.service.interfaces;

import com.turchanova.testProject.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto saveAddress(AddressDto addressDto);

    void deleteAddress(long id);

    List<AddressDto> findAll();

    AddressDto findById(long id);

    AddressDto findByLocation(String location);
}
