package com.turchanova.testProject.service.interfaces;

import com.turchanova.testProject.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto saveAuthor(AuthorDto authorDto);

    void deleteAuthor(long id);

    List<AuthorDto> findAll();

    AuthorDto findById(long id);

    AuthorDto findByNameAndSurname(String name, String surname);
}
