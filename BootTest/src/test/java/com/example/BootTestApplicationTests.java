package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.parkmaeil;
import com.example.mapper.BoardMapper;

@SpringBootTest
class BootTestApplicationTests {

	@Autowired
	BoardMapper mapper;
	
	@Test
	void contextLoads() {
		parkmaeil vo=new parkmaeil();
		vo.setTitle("게시판 연습");
		vo.setWriter("관리자");
		vo.setContent("게시판 연습");
		mapper.boardInsert(vo);
		System.out.println("저장성공");
	}

}
