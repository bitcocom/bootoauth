package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.AuthorStatistic;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;

@Service
public class AuthBookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Author> saveAuthorsWithBooks(List<Author> authors){
        return authorRepository.saveAll(authors);
    }

    //  will find N+1 problem in hibernate/jpa
    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<Author> findAuthorByEmail(String email){
        return authorRepository.findAuthorByEmail(email);
    }

    public List<AuthorStatistic> getAuthorStatistic(){
        return authorRepository.getAuthorStatistic();
    }
    //to avoid N+1 problem in hibernate/jpa
    public List<Author> getAuthorsWithFetchJoin(){
        return authorRepository.getAuthors();
    }

}
