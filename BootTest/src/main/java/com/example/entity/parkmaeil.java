package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity  // Table
@Data
public class parkmaeil { // Object --R---> Table : ORM

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx; // PK, 자동증가컬럼
	private String title;
	private String writer;
	private String content;
	
	@Column(columnDefinition ="datetime default now()")
	private Date indate; // now()
	@Column(columnDefinition = "int default 0")
	private Long count;
	
}
