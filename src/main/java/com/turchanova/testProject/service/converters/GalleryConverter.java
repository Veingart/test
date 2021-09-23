package com.turchanova.testProject.service.converters;

import com.turchanova.testProject.dto.GalleryDto;
import com.turchanova.testProject.model.Gallery;
import org.springframework.stereotype.Component;

@Component
public class GalleryConverter {
    AddressConverter addressConverter = new AddressConverter();

    public Gallery fromGalleryDtoToGallery(GalleryDto galleryDto){
        return Gallery.builder()
                .id(galleryDto.getId())
                .name(galleryDto.getName())
                .address(addressConverter.fromAddressDtoToAddress(galleryDto.getAddressDto()))
                .build();
    }

    public GalleryDto frmGalleryToGalleryDto(Gallery gallery){
        return GalleryDto.builder()
                .id(gallery.getId())
                .name(gallery.getName())
                .addressDto(addressConverter.fromAddressToAddressDto(gallery.getAddress()))
                .build();
    }
}
