package kr.smhrd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.smhrd.entity.Board;
import kr.smhrd.mapper.BoardMapper;
import kr.smhrd.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	// mybatis
	@Autowired	
	private BoardMapper boardMapper;
	   
	//전체리스트를 가져오는 메서드
	public List<Board> getList1(){
		   // 비즈니스로직을 처리하기위해서...
		   // if. for~~~~
		   List<Board> list=boardMapper.getList();
		   return list;
	}
	
	public List<Board> getList(){
		List<Board> list=boardRepository.findAll();
		return list;
	}
	
	public void boardInsert(Board vo) {
		boardRepository.save(vo);
	}
}
