package com.turchanova.testProject.service.impl;

import com.turchanova.testProject.dto.AddressDto;
import com.turchanova.testProject.model.Address;
import com.turchanova.testProject.repository.AddressRepository;
import com.turchanova.testProject.service.converters.AddressConverter;
import com.turchanova.testProject.service.interfaces.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressConverter addressConverter) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }


    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        log.info("Add address", addressDto);
        Address savedAddress = addressRepository.save(addressConverter.fromAddressDtoToAddress(addressDto));
        return addressConverter.fromAddressToAddressDto(savedAddress);
    }

    @Override
    public void deleteAddress(long id) {
        log.info("Delete address", id);
        addressRepository.deleteById(id);
    }

    @Override
    public List<AddressDto> findAll() {
        log.info("Get address list");
        List<Address> addresses = addressRepository.findAll();
        List<AddressDto> addressDtos = new ArrayList<>();
        for (int i = 0; i < addresses.size(); i++) {
            addressDtos.add(addressConverter.fromAddressToAddressDto(addresses.get(i)));
        }
        return addressDtos;
    }

    @Override
    public AddressDto findById(long id) {
        log.info("get address by id", id);
        return addressConverter.fromAddressToAddressDto(addressRepository.getById(id));
    }

    @Override
    public AddressDto findByLocation(String location) {
        if (addressRepository.findByLocation(location) != null) {
            AddressDto addressDto = new AddressDto();
            addressDto = addressConverter.fromAddressToAddressDto(addressRepository.findByLocation(location));
            return addressDto;
        } else return null;

    }

}
