package com.example.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;



import com.example.dto.AuthorStatistic;
import com.example.entity.Author;
import com.example.repository.AuthorRepository;
import com.querydsl.core.types.Projections;

public class AuthorRepositoryImpl extends BaseRepositoryImpl<Author, Integer>
                                 implements AuthorRepository{

	public AuthorRepositoryImpl(EntityManager em) {
		super(Author.class, em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<Author> findAuthorByEmail(String email) {		
		return Optional.ofNullable(
				jpaQueryFactory.select(author)
				.from(author)
				.where(author.email.equalsIgnoreCase(email))
				.fetchFirst());
	}

	@Override
	public List<AuthorStatistic> getAuthorStatistic() {
		// TODO Auto-generated method stub
		return jpaQueryFactory
				.from(author)
				.innerJoin(author.books, book)
				.groupBy(author.name)
				.select(Projections.constructor(AuthorStatistic.class,
						author.name, book.count())).fetch();
	}

	@Override
	public List<Author> getAuthors() {
		// TODO Auto-generated method stub
		return jpaQueryFactory
				.select(author)
				.distinct()
				.from(author)
				.innerJoin(author.books, book)
				.fetchJoin().fetch();
	}

}
