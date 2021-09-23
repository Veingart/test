package com.turchanova.testProject.service.impl;

import com.github.javafaker.Faker;
import com.turchanova.testProject.dto.AuthorDto;
import com.turchanova.testProject.model.Author;
import com.turchanova.testProject.repository.AuthorRepository;
import com.turchanova.testProject.service.converters.AuthorConverter;
import com.turchanova.testProject.service.interfaces.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        log.info("Add author ", authorDto);
        Author savedAuthor = authorRepository.save(authorConverter.fromAuthorDtoToAuthor(authorDto));
        return authorConverter.fromAuthorToAuthorDto(savedAuthor);
    }

    @Override
    public void deleteAuthor(long id) {
        log.info("Delete author by id", id);
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDto> findAll() {
        log.info("Get author list");
        List<Author> authorList = authorRepository.findAll();
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (int i=0; i<authorList.size(); i++){
            authorDtoList.add(authorConverter.fromAuthorToAuthorDto(authorList.get(i)));
        }
        return authorDtoList;
    }

    @Override
    public AuthorDto findById(long id) {
        log.info("Get author by id", id);
        return authorConverter.fromAuthorToAuthorDto(authorRepository.getById(id));
    }

    @Override
    public AuthorDto findByNameAndSurname(String name, String surname){
        if(authorRepository.findByNameAndSurname(name, surname)!=null){
            AuthorDto authorDto = new AuthorDto();
            authorDto = authorConverter.fromAuthorToAuthorDto(authorRepository.findByNameAndSurname(name, surname));
            return authorDto;
        } else return null;

    }

    public void fakerAuthor(){
        Faker faker = new Faker();
        Author author = new Author();
        author.setName(faker.name().firstName());
        author.setSurname(faker.name().lastName());
        authorRepository.save(author);
    }
}
