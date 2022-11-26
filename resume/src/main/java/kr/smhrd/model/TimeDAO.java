package kr.smhrd.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TimeDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public List<TimeVO> timeLoad(String lcode, String mnum) {
		
		
		return null;
	}
}
