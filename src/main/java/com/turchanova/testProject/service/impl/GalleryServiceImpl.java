package com.turchanova.testProject.service.impl;

import com.github.javafaker.Faker;
import com.turchanova.testProject.dto.GalleryDto;
import com.turchanova.testProject.model.Address;
import com.turchanova.testProject.model.Gallery;
import com.turchanova.testProject.repository.AddressRepository;
import com.turchanova.testProject.repository.GalleryRepository;
import com.turchanova.testProject.service.converters.GalleryConverter;
import com.turchanova.testProject.service.interfaces.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {
    private static final Logger log = LoggerFactory.getLogger(GalleryServiceImpl.class);
    private final GalleryRepository galleryRepository;
    private final GalleryConverter galleryConverter;
    private final AddressRepository addressRepository;

    @Autowired
    public GalleryServiceImpl(GalleryRepository galleryRepository, GalleryConverter galleryConverter, AddressRepository addressRepository) {
        this.galleryRepository = galleryRepository;
        this.galleryConverter = galleryConverter;
        this.addressRepository = addressRepository;
    }


    @Override
    public GalleryDto saveGallery(GalleryDto galleryDto) {
        log.info("Add gallery ", galleryDto);
        Gallery savedGallery = galleryRepository.save(galleryConverter.fromGalleryDtoToGallery(galleryDto));
        return galleryConverter.frmGalleryToGalleryDto(savedGallery);
    }

    @Override
    public void deleteGallery(long id) {
        log.info("Delete gallery ", id);
        galleryRepository.deleteById(id);
    }

    @Override
    public List<GalleryDto> findAll() {
        log.info("Get gallery list");
        List<Gallery> galleries = galleryRepository.findAll();
        List<GalleryDto> galleryDtos = new ArrayList<>();
        for (int i = 0; i < galleries.size(); i++) {
            galleryDtos.add(galleryConverter.frmGalleryToGalleryDto(galleries.get(i)));
        }
        return galleryDtos;
    }

    @Override
    public GalleryDto findById(long id) {
        log.info("Get gallery by id ", id);
        return galleryConverter.frmGalleryToGalleryDto(galleryRepository.getById(id));
    }

    @PostConstruct
    public void fakerGallery() {
        Faker faker = new Faker();
        for (int i = 0; i < 500; i++) {
            Gallery gallery = new Gallery();
            Address address = new Address();
            address.setLocation(faker.address().fullAddress());
            gallery.setName(faker.university().name());
            gallery.setAddress(addressRepository.save(address));
            galleryRepository.save(gallery);
        }
    }
}
