package com.example.repository.impl;

import javax.persistence.EntityManager;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book, Integer>
                                 implements BookRepository{

	public BookRepositoryImpl(EntityManager em) {
		super(Book.class, em);
		// TODO Auto-generated constructor stub
	}
}
