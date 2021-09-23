package com.turchanova.testProject.controller;

import com.turchanova.testProject.dto.*;
import com.turchanova.testProject.service.interfaces.AuthorService;
import com.turchanova.testProject.service.interfaces.GalleryService;
import com.turchanova.testProject.service.interfaces.PaintingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name="Картины", description="Взаимодействие с картинами")
public class PaintingController {
    private static final Logger log = LoggerFactory.getLogger(PaintingController.class);
    private final GalleryService galleryService;
    private final PaintingService paintingService;
    private final AuthorService authorService;

    @Autowired
    public PaintingController(GalleryService galleryService, PaintingService paintingService, AuthorService authorService) {
        this.galleryService = galleryService;
        this.paintingService = paintingService;
        this.authorService = authorService;
    }

    @Operation(summary = "Просмотр всех картин")
    @GetMapping("/paintings")
    List<PaintingDto> all() {
        log.info("Go to painting list in controller");
        return paintingService.findAll();
    }

    @Operation(summary = "Добавление картины")
    @PostMapping("/paintings")
    PaintingDto newPainting(@RequestBody PaintingForm paintingForm) {
        AuthorDto newAuthor;
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for(int i = 0; i<paintingForm.getAuthorFormList().size(); i++) {
            AuthorForm authorForm = paintingForm.getAuthorFormList().get(i);
            if (authorService.findByNameAndSurname(authorForm.getName(), authorForm.getSurname()) == null) {
                AuthorDto author = new AuthorDto();
                author.setName(authorForm.getName());
                author.setSurname(authorForm.getSurname());
                newAuthor = authorService.saveAuthor(author);
            } else {
                newAuthor = authorService.findByNameAndSurname(authorForm.getName(), authorForm.getSurname());
            }
            authorDtoList.add(newAuthor);
        }
        log.info("Save authors", authorDtoList);
        PaintingDto paintingDto = new PaintingDto();
        paintingDto.setTitle(paintingForm.getTitle());
        paintingDto.setYear(paintingForm.getYear());
        paintingDto.setAuthorDtoList(authorDtoList);
        GalleryDto galleryDto = galleryService.findById(paintingForm.getGallery_id());
        paintingDto.setGalleryDto(galleryDto);
        log.info("Save painting ", paintingDto);
        return paintingService.savePainting(paintingDto);
    }

    @Operation(summary = "Просмотр информации о картине")
    @GetMapping("/paintings/{id}")
    PaintingDto getPaintingById(@PathVariable Long id) {
        log.info("Go to painting ", id);
        return paintingService.findById(id);
    }

    @Operation(summary = "Удаление картины")
    @DeleteMapping("/paintings/{id}")
    void deletePainting(@PathVariable Long id) {
        log.info("delete painting ", id);
        paintingService.deletePainting(id);
    }

    @Operation(summary = "Редактирование информации о картине")
    @PutMapping("/paintings/{id}")
    PaintingDto updatePainting(@RequestBody PaintingForm paintingForm, @PathVariable long id){
        log.info("update painting ", id);
        PaintingDto paintingDto = paintingService.findById(id);
        paintingDto.setTitle(paintingForm.getTitle());
        paintingDto.setYear(paintingForm.getYear());
        AuthorDto newAuthor;
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for(int i = 0; i<paintingForm.getAuthorFormList().size(); i++) {
            AuthorForm authorForm = paintingForm.getAuthorFormList().get(i);
            if (authorService.findByNameAndSurname(authorForm.getName(), authorForm.getSurname()) == null) {
                AuthorDto author = new AuthorDto();
                author.setName(authorForm.getName());
                author.setSurname(authorForm.getSurname());
                newAuthor = authorService.saveAuthor(author);
            } else {
                newAuthor = authorService.findByNameAndSurname(authorForm.getName(), authorForm.getSurname());
            }
            authorDtoList.add(newAuthor);
        }
        paintingDto.setAuthorDtoList(authorDtoList);
        GalleryDto galleryDto = galleryService.findById(paintingForm.getGallery_id());
        paintingDto.setGalleryDto(galleryDto);
        return paintingService.savePainting(paintingDto);
    }
}
