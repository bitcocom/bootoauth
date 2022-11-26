package kr.smhrd.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TableDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void table1(Table1VO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("table1", vo);
			session.commit();		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}				
	}

	public List<SuperVO> getStudentByNumTable1(String num) {
		List<SuperVO> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			list=session.selectList("getStudentByNumTable1", num);						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public void table1Del(int tnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("table1Del", tnum);
			session.commit();		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	public void table2(Table2VO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("table2", vo);
			session.commit();		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	public void table2Del(int tnum2) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("table2Del", tnum2);
			session.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}	
	}

	public List<SuperVO3> getStudentByNumTable3(String num) {
		List<SuperVO3> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			list=session.selectList("getStudentByNumTable3", num);						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<SuperVO4> getStudentByNumTable4(String num) {
		List<SuperVO4> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			list=session.selectList("getStudentByNumTable4", num);						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	public List<SuperVO5> getStudentByNumTable5(String num) {
		List<SuperVO5> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			list=session.selectList("getStudentByNumTable5", num);						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	public List<SuperVO6> getStudentByNumTable6(String num) {
		List<SuperVO6> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			list=session.selectList("getStudentByNumTable6", num);						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	public List<SuperVO7> getStudentByNumTable7(String num) {
		List<SuperVO7> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			list=session.selectList("getStudentByNumTable7", num);						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	public List<SuperVO2> getStudentByNumTable2(String num) {
		List<SuperVO2> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			list=session.selectList("getStudentByNumTable2", num);						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public void table3(Table3VO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("table3", vo);
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	public void table4(Table4VO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("table4", vo);
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	public void table5(Table5VO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("table5", vo);
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	public void table6(Table6VO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("table6", vo);
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	
	// 참고 table7
	/*   <select id="cpartBynum2" resultType="CareerVO" parameterType="CareerVO">
           select * from career where cnum=${cnum}
         </select>*/
	public void skillcontent(CareerVO voo) {
		SqlSession session=sqlSessionFactory.openSession();
		CareerVO vo=null;
		int cnt=-1;
		try {
			vo=session.selectOne("cpartBynum2", voo);
			//System.out.println(vo);
			if(vo!=null) {
				// update
				cnt=session.update("skillcontent1", voo);
			}else {
				// insert
				cnt=session.insert("skillcontent2", voo);								
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void table7(Table7VO voo) {
		SqlSession session=sqlSessionFactory.openSession();
		Table7VO vo=null;
		int cnt=-1;
		try {
			vo=session.selectOne("table7Bynum", voo);  // ?
			if(vo!=null) {
				cnt=session.update("table7_1", voo);	   // ?
			}else {
				cnt=session.insert("table7", voo);	
			}
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	
	
	
	public void table3Del(int tnum3) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("table3Del", tnum3);
			session.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}	
		
	}	
	public void table4Del(int tnum4) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("table4Del", tnum4);
			session.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}	
		
	}	
	public void table5Del(int tnum5) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("table5Del", tnum5);
			session.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}	
		
	}	
	public void table6Del(int tnum6) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("table6Del", tnum6);
			session.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}	
	
	public void table7Del(int tnum7) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("table7Del", tnum7);
			session.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}	
	
}
