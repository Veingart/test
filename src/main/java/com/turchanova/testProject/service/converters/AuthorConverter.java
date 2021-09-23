package com.turchanova.testProject.service.converters;

import com.turchanova.testProject.dto.AuthorDto;
import com.turchanova.testProject.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public Author fromAuthorDtoToAuthor(AuthorDto authorDto){
        return Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .surname(authorDto.getSurname())
                .build();
    }

    public  AuthorDto fromAuthorToAuthorDto(Author author){
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}
