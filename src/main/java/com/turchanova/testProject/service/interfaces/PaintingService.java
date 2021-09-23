package com.turchanova.testProject.service.interfaces;

import com.turchanova.testProject.dto.PaintingDto;

import java.util.List;

public interface PaintingService {
    PaintingDto savePainting(PaintingDto paintingDto);

    void deletePainting(long id);

    List<PaintingDto> findAll();

    PaintingDto findById(long id);
}
