package com.turchanova.testProject.service.converters;

import com.turchanova.testProject.dto.AddressDto;
import com.turchanova.testProject.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address fromAddressDtoToAddress(AddressDto addressDto){
        return Address.builder()
                .id(addressDto.getId())
                .location(addressDto.getLocation())
                .build();
    }

    public AddressDto fromAddressToAddressDto(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .location(address.getLocation())
                .build();
    }
}
