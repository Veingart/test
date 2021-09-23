package com.turchanova.testProject.service.impl;

import com.github.javafaker.Faker;
import com.turchanova.testProject.dto.PaintingDto;
import com.turchanova.testProject.model.Author;
import com.turchanova.testProject.model.Gallery;
import com.turchanova.testProject.model.Painting;
import com.turchanova.testProject.repository.AuthorRepository;
import com.turchanova.testProject.repository.GalleryRepository;
import com.turchanova.testProject.repository.PaintingRepository;
import com.turchanova.testProject.service.converters.PaintingConverter;
import com.turchanova.testProject.service.interfaces.PaintingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaintingServiceImpl implements PaintingService {
    private static final Logger log = LoggerFactory.getLogger(GalleryServiceImpl.class);
    private final PaintingConverter paintingConverter;
    private final PaintingRepository paintingRepository;
    private final GalleryRepository galleryRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public PaintingServiceImpl(PaintingConverter paintingConverter, PaintingRepository paintingRepository, GalleryRepository galleryRepository, AuthorRepository authorRepository) {
        this.paintingConverter = paintingConverter;
        this.paintingRepository = paintingRepository;
        this.galleryRepository = galleryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public PaintingDto savePainting(PaintingDto paintingDto) {
        log.info("Add painting ", paintingDto);
        Painting savedPainting = paintingRepository.save(paintingConverter.fromPaintingDtoToPainting(paintingDto));
        return paintingConverter.fromPaintingToPaintingDto(savedPainting);
    }

    @Override
    public void deletePainting(long id) {
        log.info("Delete painting ", id);
        paintingRepository.deleteById(id);
    }

    @Override
    public List<PaintingDto> findAll() {
        log.info("Get painting list");
        List<Painting> paintingList = paintingRepository.findAll();
        List<PaintingDto> paintingDtos = new ArrayList<>();
        for (int i = 0; i < paintingList.size(); i++) {
            paintingDtos.add(paintingConverter.fromPaintingToPaintingDto(paintingList.get(i)));
        }
        return paintingDtos;
    }

    @Override
    public PaintingDto findById(long id) {
        log.info("Get painting by id ", id);
        return paintingConverter.fromPaintingToPaintingDto(paintingRepository.getById(id));
    }

    @PostConstruct
    public void fakerPainting() {
        Faker faker = new Faker();
        for (int i = 0; i < 500; i++) {
            Painting painting = new Painting();
            List<Author> authorList = new ArrayList<>();
            int a = 1 + (int) (Math.random() * 3);
            for (int b = 1; b <= a; b++) {
                Author author = new Author();
                author.setName(faker.name().firstName());
                author.setSurname(faker.name().lastName());
                authorList.add(authorRepository.save(author));
            }
            painting.setAuthorList(authorList);

            int c = 1 + (int) (Math.random() * 500);
            painting.setGallery(galleryRepository.findById((long) c).orElse(new Gallery()));
            painting.setTitle(faker.lorem().fixedString(20));
            painting.setYear(1 + (int) (Math.random() * 2020));
            paintingRepository.save(painting);
        }
    }
}
