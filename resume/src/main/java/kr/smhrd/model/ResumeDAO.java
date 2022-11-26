package kr.smhrd.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public int memberInsert(ResumeVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("memberInsert", vo);
			session.commit();
			
			cnt=session.update("memberisdata", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
		return cnt;
	}
	
	public List<ResumeVO> memberList(String lcode, String mnum) {
		ResumeVO vo=new ResumeVO();
		vo.setLcode(lcode);
		vo.setMnum(mnum);
		SqlSession session=sqlSessionFactory.openSession();
		List<ResumeVO> list=null;
		List<ResumeVO> list1=null;
		try {
			list=session.selectList("memberList", vo); //SQL
			list1=session.selectList("count", vo);
			for(int i=0;i<list1.size();i++) {
			     vo=list1.get(i);
			     for(int j=0;j<list.size();j++) {
			    	 ResumeVO vo1=list.get(j);
			    	 if(vo.getNum()==vo1.getNum()) {
			    		 vo1.setCnt(vo.getCnt());
			    		 list.set(j, vo1);
			    	 }
			     }
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public List<LcourseVO> lcourseLoad() {
		SqlSession session=sqlSessionFactory.openSession();
		List<LcourseVO> list=null;
		try {
			list=session.selectList("lcourseLoad"); //SQL
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public List<McourseVO> mcourseLoad(String lcode) {
		SqlSession session=sqlSessionFactory.openSession();
		List<McourseVO> list=null;
		try {
			list=session.selectList("mcourseLoad", lcode); //SQL
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public List<McourseVO> mcourselist(int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		List<McourseVO> list=null;
		try {
			list=session.selectList("mcourselist", mnum); //SQL
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public void makeCard(String mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			
			cnt=session.update("memberismeet", mnum);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public void state1update(ResumeVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			
			cnt=session.update("state1update", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void state2update(ResumeVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			
			cnt=session.update("state2update", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public String getName(String lcode, String mnum) {
		McourseVO vo=new McourseVO();
		vo.setLcode(lcode);
		vo.setMnum(Integer.parseInt(mnum));
		//System.out.println(vo.toString());
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			vo=session.selectOne("getName", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo.getMname();
	}
	
	public McourseVO getName1(String lcode, String mnum) {
		McourseVO vo=new McourseVO();
		vo.setLcode(lcode);
		vo.setMnum(Integer.parseInt(mnum));
		//System.out.println(vo.toString());
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			vo=session.selectOne("getName", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}	

	public void uploadImageFile(String imagename, String num) {
		ResumeVO vo=new ResumeVO();
		vo.setImage(imagename);
		vo.setNum(Integer.parseInt(num));
		System.out.println(vo.toString());
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			session.update("uploadImageFile", vo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}	
	}
	public List<SangdamVO> sangdam(int num) {
		SqlSession session=sqlSessionFactory.openSession();
		List<SangdamVO> list=null;
		try {
			list=session.selectList("sangdam", num); //SQL
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public void sanginsert(SangdamVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("sanginsert", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	public void datadelete(McourseVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			// 중분류 YES->NO
			cnt=session.update("dataupdate", vo);
			session.commit();
			
			// 학생데이터삭제(DB)
			cnt=session.delete("datadelete", vo);
			session.commit();
			
			// 상담내용삭제
			cnt=session.delete("sangdamdelete", vo);
			session.commit(); 
			
			// 취업내용삭제
			cnt=session.delete("careerdelete", vo);
			session.commit(); 
						
			// 이력서 관련
			cnt=session.delete("table1delete", vo);
			session.commit(); 
						
			cnt=session.delete("table2delete", vo);
			session.commit(); 
			
			cnt=session.delete("table3delete", vo);
			session.commit(); 
			
			cnt=session.delete("table4delete", vo);
			session.commit(); 
			
			cnt=session.delete("table5delete", vo);
			session.commit(); 
			
			cnt=session.delete("table6delete", vo);
			session.commit(); 
						
			cnt=session.delete("table7delete", vo);
			session.commit();
			
			// 중탈인원+교육인원=현인원
			int mnum=vo.getMnum();
			cnt=session.update("misismsu", mnum);
			session.commit(); 
			
			// 수료인원, 취업인원, 수료율, 취업률 ->0
			cnt=session.update("isisinital",  mnum);
			session.commit();
			
			cnt=session.delete("teamAllDelete",  vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

/*	public void sangdamno(McourseVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			// 중분류 YES->NO
			cnt=session.update("sangdamno", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}*/

	public ResumeCareerVO getStudentByNum(String num) {
		ResumeCareerVO vo=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			vo=session.selectOne("getStudentByNum", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}

	public void certInsert(ResumeVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("certInsert", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void sangdeletenum(SangdamVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("sangdeletenum", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}	
	}

	public void courseinsert(McourseVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("courseinsert", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	public List<McourseVO> mcourseLoadall(String lcode) {
		SqlSession session=sqlSessionFactory.openSession();
		List<McourseVO> list=null;
		try {
			list=session.selectList("mcourseLoadall"); //SQL
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;	
	}

	public void mdelete(McourseVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			// mcourse를 지울때
			cnt=session.delete("mdelete", vo);
			session.commit();
			
			// 학생데이터삭제(DB)
			cnt=session.delete("datadelete", vo);
			session.commit();
						
			// 상담내용삭제
			cnt=session.delete("sangdamdelete", vo);
			session.commit(); 
			
			// 취업내용삭제
			cnt=session.delete("careerdelete", vo);
			session.commit(); 
						
			// 이력서 관련
			cnt=session.delete("table1delete", vo);
			session.commit(); 
						
			cnt=session.delete("table2delete", vo);
			session.commit(); 
			
			cnt=session.delete("table3delete", vo);
			session.commit(); 
			
			cnt=session.delete("table4delete", vo);
			session.commit(); 
			
			cnt=session.delete("table5delete", vo);
			session.commit(); 
			
			cnt=session.delete("table6delete", vo);
			session.commit(); 
						
			cnt=session.delete("table7delete", vo);
			session.commit();
			
			cnt=session.delete("teamAllDelete", vo);
			session.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void state1updateYES(int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("state1updateYES", mnum);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void state1updateNO(int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("state1updateNO", mnum);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void state2updateYES(int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("state2updateYES", mnum);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void state2updateNO(int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("state2updateNO", mnum);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public void state1updateYESNO(int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		McourseVO vo=null;
		try {
			vo=session.selectOne("msucount", mnum);
			cnt=session.update("state1updateYESNO", mnum);
			if(vo.getMsu1()!=0) {
				 cnt=session.update("state2updateYESNO", mnum);
			}
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public void state2updateYESNO(int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		McourseVO vo=null;
		try {
			// 먼저 수료인원이 이있는지를 판단 쿼리전송
			vo=session.selectOne("msucount", mnum);
			if(vo.getMsu1()!=0) {
			   // 취업률 계산 가능																																																								````````````````
			   cnt=session.update("state2updateYESNO", mnum);
			}
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	public void memupdate(McourseVO vo) {
		SqlSession session=sqlSessionFactory.openSession();	
		int cnt=0;
		try {
			session.update("memupdate" ,vo);	
			session.commit();			
			vo=session.selectOne("msucount", vo.getMnum());
			cnt=session.update("state1updateYESNO", vo.getMnum());
			if(vo.getMsu1()!=0) {
				 cnt=session.update("state2updateYESNO", vo.getMnum());
			}			
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public void isisdata(int num, int mnum) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("isisdata", num);
			// 교육인원 1명 감소
			cnt=session.update("mcoursesuminus", mnum);
			// 중탈인원 증가
			cnt=session.update("mcoursesumsu", mnum);
			session.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public List<McourseVO> getAllCourse() {
		SqlSession session=sqlSessionFactory.openSession();
		List<McourseVO> list=null;
		try {
			list=session.selectList("mcourseLoadall"); //SQL
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	public void teamInsert(TeamVO dto) {
		SqlSession session=sqlSessionFactory.openSession();
		ResumeVO vo=null;
		try {
			vo=session.selectOne("resumedata", dto);
			dto.setNum(vo.getNum());
			dto.setLcode(vo.getLcode());
			dto.setMnum(Integer.parseInt(vo.getMnum()));
			dto.setName(vo.getName());			
			dto.setCollege(vo.getCollege());
			dto.setSsn(vo.getSsn());
			
			session.insert("teamInsert", dto);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}		
	}

	public void teamDelete(int num) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("teamDelete", num);
			session.commit(); 
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void teamAllDelete(TeamVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.delete("teamAllDelete", vo);
			session.commit(); 
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public List<TeamVO> getAllTeam(TeamVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		List<TeamVO> list=null;
		try {
			list=session.selectList("getAllTeam", vo); //SQL
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;	
	}

	public void mentorinsert(MentorVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.insert("mentorinsert", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public int dbnamecheck(String mtname) {
		SqlSession session=sqlSessionFactory.openSession();
		Integer cnt=-1;
		try {
			cnt=session.selectOne("dbnamecheck", mtname);
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt.intValue();
	}

	public int cpartBynum(CareerVO voo) {
		SqlSession session=sqlSessionFactory.openSession();
		CareerVO vo=null;
		int cnt=-1;
		try {
			vo=session.selectOne("cpartBynum", voo);
			System.out.println(vo);
			if(vo!=null) {
				// update
				cnt=session.update("cpartBynumUpdate", voo);
			}else {
				// insert
				cnt=session.insert("cpartBynumInsert", voo);								
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;	
	}

	public ResumeCareerVO getStudentByNum1(String num) {
		ResumeCareerVO vo=null;
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			CareerVO voo=new CareerVO();
			voo.setCnum(Integer.parseInt(num));
			voo=session.selectOne("cpartBynum", voo);
			if(voo!=null) {
				vo=session.selectOne("getStudentByNum1", num);	
			}else {
				vo=session.selectOne("getStudentByNum", num);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}

	public void cpartcancel(CareerVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("cpartcancel", vo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}				
	}
	
	public int cpartBynum1(CareerVO voo) {
		SqlSession session=sqlSessionFactory.openSession();
		CareerVO vo=null;
		int cnt=-1;
		try {
			vo=session.selectOne("cpartBynum1", voo);
			System.out.println(vo);
			if(vo!=null) {
				// update
				cnt=session.update("cpartBynumUpdate1", voo);
			}else {
				// insert
				cnt=session.insert("cpartBynumInsert1", voo);								
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;	
	}
	
	public void cpartcancel1(CareerVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("cpartcancel1", vo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}				
	}
	
	public int cpartBynum2(CareerVO voo) {
		SqlSession session=sqlSessionFactory.openSession();
		CareerVO vo=null;
		int cnt=-1;
		try {
			vo=session.selectOne("cpartBynum2", voo);
			System.out.println(vo);
			if(vo!=null) {
				// update
				cnt=session.update("cpartBynumUpdate2", voo);
			}else {
				// insert
				cnt=session.insert("cpartBynumInsert2", voo);								
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;	
	}
	
	public void cpartcancel2(CareerVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("cpartcancel2", vo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}				
	}

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

	public void csave(CareerVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("csave", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	
	public void cstart(CareerVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("cstart", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}	
	

	public void cinsur(CareerVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("cinsur", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	public void addr(ResumeVO vo) {
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("addr", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	public CareerVO careerState2updateYES(int num) {
		SqlSession session=sqlSessionFactory.openSession();
		CareerVO vo=null;
		try {
			vo=session.selectOne("careerState2updateYES", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}

	public void updateTime(String lcode, String mnum, String fileName) {
		McourseVO vo=new McourseVO();
		vo.setLcode(lcode);
		vo.setMnum(Integer.parseInt(mnum));
		vo.setIstime(fileName);
		
		SqlSession session=sqlSessionFactory.openSession();
		int cnt=-1;
		try {
			cnt=session.update("updateTime", vo);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}			
	}	
}








