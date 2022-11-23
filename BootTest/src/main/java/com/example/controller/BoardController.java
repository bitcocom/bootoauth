package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.parkmaeil;
import com.example.mapper.BoardMapper;
import com.example.repository.BoardRepository;

@Controller
public class BoardController {

	@Autowired
	BoardMapper mapper;
	
	@Autowired
	BoardRepository repository;
	
	@RequestMapping("/jsp")
	public String jsp() {
		return "main";
	}
	@RequestMapping("/th")
	public String th() {
		return "th/main";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<parkmaeil> list=mapper.getLists();
		model.addAttribute("list", list);
		return "board/list"; // templates/board/list.html
	}
	
	@RequestMapping("/list1")
	public String list1(Model model) {
		//List<parkmaeil> list=repository.findAll();
		List<parkmaeil> list=repository.findLike("게", "게");
		model.addAttribute("list", list);
		return "board/list"; // templates/board/list.html
	}
}
