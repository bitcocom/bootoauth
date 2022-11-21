package kr.smhrd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.smhrd.entity.Board;

@Repository //생략
public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
