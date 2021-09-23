package com.turchanova.testProject.controller;

import com.turchanova.testProject.dto.AddressDto;
import com.turchanova.testProject.dto.GalleryDto;
import com.turchanova.testProject.dto.GalleryForm;
import com.turchanova.testProject.service.interfaces.AddressService;
import com.turchanova.testProject.service.interfaces.GalleryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name="Галереи", description="Взаимодействие с галлереями")
public class GalleryController {
    private static final Logger log = LoggerFactory.getLogger(GalleryController.class);
    private final GalleryService galleryService;
    private final AddressService addressService;

    @Autowired
    public GalleryController(GalleryService galleryService, AddressService addressService) {
        this.galleryService = galleryService;
        this.addressService = addressService;
    }

    @Operation(summary = "Просмотр всех галерей")
    @GetMapping("/galleries")
    List<GalleryDto> all() {
        log.info("Go to gallery list in controller");
        return galleryService.findAll();
    }

    @Operation(summary = "Добавление новой галереи")
    @PostMapping("/galleries")
    GalleryDto newGallery(@RequestBody GalleryForm galleryForm) {
        AddressDto newAddress;
        if (addressService.findByLocation(galleryForm.getLocation()) == null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setLocation(galleryForm.getLocation());
            newAddress = addressService.saveAddress(addressDto);

        } else {
            newAddress = addressService.findByLocation(galleryForm.getLocation());
        }
        log.info("Save address", newAddress);
        GalleryDto galleryDto = new GalleryDto();
        galleryDto.setName(galleryForm.getName());
        galleryDto.setAddressDto(newAddress);
        log.info("Save gallery ", galleryDto);
        return galleryService.saveGallery(galleryDto);
    }

    @Operation(summary = "Просмотр информации о галерее")
    @GetMapping("/galleries/{id}")
    GalleryDto getGalleryById(@PathVariable Long id) {
        log.info("Go to gallery ", id);
        return galleryService.findById(id);
    }

    @Operation(summary = "Удаление галереи")
    @DeleteMapping("/galleries/{id}")
    void deleteGallery(@PathVariable Long id) {
        log.info("delete gallery ", id);
        galleryService.deleteGallery(id);
    }

    @Operation(summary = "Редактирование информации о галерее")
    @PutMapping("/galleries/{id}")
    GalleryDto updateGallery(@RequestBody GalleryForm galleryForm, @PathVariable long id){
        log.info("update gallery ", id);
        GalleryDto galleryDto = galleryService.findById(id);
        galleryDto.setName(galleryForm.getName());
        AddressDto addressDto = galleryDto.getAddressDto();
        addressDto.setLocation(galleryForm.getLocation());
        galleryDto.setAddressDto(addressService.saveAddress(addressDto));
        return galleryService.saveGallery(galleryDto);
    }

}
