package com.turchanova.testProject.service.interfaces;

import com.turchanova.testProject.dto.GalleryDto;

import java.util.List;

public interface GalleryService {
    GalleryDto saveGallery(GalleryDto galleryDto);

    void deleteGallery(long id);

    List<GalleryDto> findAll();

    GalleryDto findById(long id);
}
