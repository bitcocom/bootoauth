package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.parkmaeil;

@Repository
public interface BoardRepository extends JpaRepository<parkmaeil, Long>{
   
	@Query("select u from parkmaeil u where u.title LIKE %:title% and u.content LIKE %:content%")
	public List<parkmaeil> findLike(@Param("title") String title, @Param("content") String content);
	
	
}
