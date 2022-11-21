package kr.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import kr.smhrd.entity.Board;
import kr.smhrd.entity.Member;

// JDBC -> MyBatis->Spring
@Repository
@Mapper
public interface BoardMapper {
	// 전체리스트가져오기 메서드
	public List<Board> getList(); // 추상메서드 ---> SQL(Mapper XML)	
	// 글 저장 메서드
	public void insert(Board vo); // SQL~
	// 특정(idx) 게시물 하나를 가져오기
	public Board read(int idx);
	
	public int delete(int idx);
	public void update(Board vo);
	
	@Update("update tblBoard set count=count+1 where idx=#{idx}")
	public void countUpdate(int idx);
	
	@Select("select * from tblMember where memId=#{memId} and memPwd=#{memPwd}")
	public Member login(Member vo);
	
}