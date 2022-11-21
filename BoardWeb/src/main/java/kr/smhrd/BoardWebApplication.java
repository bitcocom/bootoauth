package kr.smhrd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootConfiguration - @Configuration  : 환경설정클래스의미
//@EnableAutoConfiguration : 등록되어있는(spring.factories) 자동설정 클래스들을 처리한다.
//WebMvcAutoConfiguration 클래스 : 웹 애플리케이션 개발과 관련된 자동 설정 클래스
//@ComponentScan - @Configuration,@Repository,@Service,@Controller,@RestController
//package com.example; 하위패키지가 스캔된다.

@SpringBootApplication
public class BoardWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardWebApplication.class, args);
	}

}
