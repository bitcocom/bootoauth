package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dto.AuthorStatistic;
import com.example.entity.Author;


public interface AuthorRepository extends BaseRepository<Author,Integer>{

	    public Optional<Author> findAuthorByEmail(String email);

	    public List<AuthorStatistic> getAuthorStatistic();

	    public List<Author> getAuthors();
}
