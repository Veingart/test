package com.turchanova.testProject.service.converters;

import com.turchanova.testProject.dto.AuthorDto;
import com.turchanova.testProject.dto.PaintingDto;
import com.turchanova.testProject.model.Author;
import com.turchanova.testProject.model.Painting;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaintingConverter {
    AuthorConverter authorConverter = new AuthorConverter();
    GalleryConverter galleryConverter = new GalleryConverter();

    public Painting fromPaintingDtoToPainting(PaintingDto paintingDto){

        List <Author> authorList = new ArrayList<>();
        List<AuthorDto> authorDtoList = new ArrayList<>();

        authorDtoList = paintingDto.getAuthorDtoList();
        for(int i = 0; i<authorDtoList.size(); i++){
            AuthorDto authorDto = authorDtoList.get(i);
            authorList.add(authorConverter.fromAuthorDtoToAuthor(authorDto));
        }
        return Painting.builder()
                .id(paintingDto.getId())
                .title(paintingDto.getTitle())
                .year(paintingDto.getYear())
                .gallery(galleryConverter.fromGalleryDtoToGallery(paintingDto.getGalleryDto()))
                .authorList(authorList)
                .build();
    }

    public PaintingDto fromPaintingToPaintingDto(Painting painting){

        List <Author> authorList = new ArrayList<>();
        List<AuthorDto> authorDtoList = new ArrayList<>();

        authorList = painting.getAuthorList();
        for(int i = 0; i<authorList.size(); i++){
            Author author = authorList.get(i);
            authorDtoList.add(authorConverter.fromAuthorToAuthorDto(author));
        }
        return PaintingDto.builder()
                .id(painting.getId())
                .title(painting.getTitle())
                .year(painting.getYear())
                .galleryDto(galleryConverter.frmGalleryToGalleryDto(painting.getGallery()))
                .authorDtoList(authorDtoList)
                .build();
    }
}
