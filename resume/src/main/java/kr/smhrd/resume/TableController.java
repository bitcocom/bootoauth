package kr.smhrd.resume;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.smhrd.model.ResumeCareerVO;
import kr.smhrd.model.ResumeDAO;
import kr.smhrd.model.SangdamVO;
import kr.smhrd.model.SuperVO;
import kr.smhrd.model.SuperVO2;
import kr.smhrd.model.SuperVO3;
import kr.smhrd.model.SuperVO4;
import kr.smhrd.model.SuperVO5;
import kr.smhrd.model.SuperVO6;
import kr.smhrd.model.SuperVO7;
import kr.smhrd.model.Table1VO;
import kr.smhrd.model.Table2VO;
import kr.smhrd.model.Table3VO;
import kr.smhrd.model.Table4VO;
import kr.smhrd.model.Table5VO;
import kr.smhrd.model.Table6VO;
import kr.smhrd.model.Table7VO;
import kr.smhrd.model.TableDAO;
import kr.smhrd.model.TimeVO;
import kr.smhrd.service.ExcelService;
import kr.smhrd.service.TableService;

@Controller
public class TableController {

	@Autowired
	private TableDAO getStudentByNumTable1;
	@Autowired
	private TableService service;
	
	@RequestMapping(value = "/table1", method = RequestMethod.POST)
    public String table1(Table1VO vo) {
		//System.out.println(num);
		String date=vo.getT1Sdate()+"~"+vo.getT1Edate();
		vo.setSdate(date);
		service.table1(vo);		
		//System.out.println(vo);
		return "jsonView";		   
    }	
	@RequestMapping(value = "/table2", method = RequestMethod.POST)
    public String table2(Table2VO vo) {
		//System.out.println(num);
		String date=vo.getT2Sdate()+"~"+vo.getT2Edate();
		vo.setSdate2(date);
		service.table2(vo);		
		//System.out.println(vo);
		return "jsonView";		   
    }	
	@RequestMapping(value = "/table3", method = RequestMethod.POST)
    public String table3(Table3VO vo) {
		//System.out.println(num);
		String date=vo.getT3Sdate()+"~"+vo.getT3Edate();
		vo.setSdate3(date);
		service.table3(vo);		
		//System.out.println(vo);
		return "jsonView";		   
    }	
	@RequestMapping(value = "/table4", method = RequestMethod.POST)
    public String table4(Table4VO vo) {
		//System.out.println(num);
		//String date=vo.getT3Sdate()+"~"+vo.getT3Edate();
		//vo.setSdate3(date);
		service.table4(vo);		
		//System.out.println(vo);
		return "jsonView";		   
    }	
	@RequestMapping(value = "/table5", method = RequestMethod.POST)
    public String table5(Table5VO vo) {
		//System.out.println(num);
		//String date=vo.getT3Sdate()+"~"+vo.getT3Edate();
		//vo.setSdate3(date);
		service.table5(vo);		
		//System.out.println(vo);
		return "jsonView";		   
    }
	@RequestMapping(value = "/table6", method = RequestMethod.POST)
    public String table6(Table6VO vo) {
		//System.out.println(num);
		String date=vo.getT6Sdate()+"~"+vo.getT6Edate();
		vo.setSdate6(date);
		service.table6(vo);		
		//System.out.println(vo);
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/table7", method = RequestMethod.POST)
    public String table7(Table7VO vo) {
		//System.out.println(num);
		//String date=vo.getT6Sdate()+"~"+vo.getT6Edate();
		//vo.setSdate6(date);
		service.table7(vo);		
		//System.out.println(vo);
		return "jsonView";		   
    }
	
	// dbnamecheck
		@RequestMapping(value = "/getStudentByNumTable1", method = RequestMethod.GET)
	    public String getStudentByNumTable1(String lcode, String mnum, int num,  Model model) {
		    List<SuperVO> list=service.getStudentByNumTable1(num);
			model.addAttribute("list",list);
			//String mname=getStudentByNumTable1.getName(lcode, mnum);
			//model.addAttribute("mname", mname);
			return "jsonView";	
	    }	
		
		// dbnamecheck
		@RequestMapping(value = "/table1Del", method = RequestMethod.GET)
		    public String table1Del(int tnum, Model model) {
				    service.table1Del(tnum);
					//String mname=getStudentByNumTable1.getName(lcode, mnum);
					//model.addAttribute("mname", mname);
					return "jsonView";	
			 }	
		@RequestMapping(value = "/table2Del", method = RequestMethod.GET)
	    public String table2Del(int tnum2, Model model) {
			    service.table2Del(tnum2);
				//String mname=getStudentByNumTable1.getName(lcode, mnum);
				//model.addAttribute("mname", mname);
				return "jsonView";	
		 }
		@RequestMapping(value = "/table3Del", method = RequestMethod.GET)
	    public String table3Del(int tnum3, Model model) {
			    service.table3Del(tnum3);
				//String mname=getStudentByNumTable1.getName(lcode, mnum);
				//model.addAttribute("mname", mname);
				return "jsonView";	
		 }
		@RequestMapping(value = "/table4Del", method = RequestMethod.GET)
	    public String table4Del(int tnum4, Model model) {
			    service.table4Del(tnum4);
				//String mname=getStudentByNumTable1.getName(lcode, mnum);
				//model.addAttribute("mname", mname);
				return "jsonView";	
		 }
		@RequestMapping(value = "/table5Del", method = RequestMethod.GET)
	    public String table5Del(int tnum5, Model model) {
			    service.table5Del(tnum5);
				//String mname=getStudentByNumTable1.getName(lcode, mnum);
				//model.addAttribute("mname", mname);
				return "jsonView";	
		 }
		@RequestMapping(value = "/table6Del", method = RequestMethod.GET)
	    public String table6Del(int tnum6, Model model) {
			    service.table6Del(tnum6);
				//String mname=getStudentByNumTable1.getName(lcode, mnum);
				//model.addAttribute("mname", mname);
				return "jsonView";	
		 }
		
		@RequestMapping(value = "/table7Del", method = RequestMethod.GET)
	    public String table7Del(int tnum7, Model model) {
			    service.table7Del(tnum7);
				//String mname=getStudentByNumTable1.getName(lcode, mnum);
				//model.addAttribute("mname", mname);
				return "jsonView";	
		 }
		
		// dbnamecheck
		@RequestMapping(value = "/getStudentByNumTable2", method = RequestMethod.GET)
		public String getStudentByNumTable2(String lcode, String mnum, int num,  Model model) {
				    List<SuperVO2> list=service.getStudentByNumTable2(num);
					model.addAttribute("list",list);
					//String mname=getStudentByNumTable1.getName(lcode, mnum);
					//model.addAttribute("mname", mname);
					return "jsonView";	
		}	
		
		@RequestMapping(value = "/getStudentByNumTable3", method = RequestMethod.GET)
		public String getStudentByNumTable3(String lcode, String mnum, int num,  Model model) {
				    List<SuperVO3> list=service.getStudentByNumTable3(num);
					model.addAttribute("list",list);
					//String mname=getStudentByNumTable1.getName(lcode, mnum);
					//model.addAttribute("mname", mname);
					return "jsonView";	
		}	
		@RequestMapping(value = "/getStudentByNumTable4", method = RequestMethod.GET)
		public String getStudentByNumTable4(String lcode, String mnum, int num,  Model model) {
				    List<SuperVO4> list=service.getStudentByNumTable4(num);
					model.addAttribute("list",list);
					//String mname=getStudentByNumTable1.getName(lcode, mnum);
					//model.addAttribute("mname", mname);
					return "jsonView";	
		}	
		@RequestMapping(value = "/getStudentByNumTable5", method = RequestMethod.GET)
		public String getStudentByNumTable5(String lcode, String mnum, int num,  Model model) {
				    List<SuperVO5> list=service.getStudentByNumTable5(num);
					model.addAttribute("list",list);
					//String mname=getStudentByNumTable1.getName(lcode, mnum);
					//model.addAttribute("mname", mname);
					return "jsonView";	
		}	
		@RequestMapping(value = "/getStudentByNumTable6", method = RequestMethod.GET)
		public String getStudentByNumTable6(String lcode, String mnum, int num,  Model model) {
				    List<SuperVO6> list=service.getStudentByNumTable6(num);
					model.addAttribute("list",list);
					//String mname=getStudentByNumTable1.getName(lcode, mnum);
					//model.addAttribute("mname", mname);
					return "jsonView";	
		}	
		
		@RequestMapping(value = "/getStudentByNumTable7", method = RequestMethod.GET)
		public String getStudentByNumTable7(String lcode, String mnum, int num,  Model model) {
				    List<SuperVO7> list=service.getStudentByNumTable7(num);
					model.addAttribute("list",list);
					//String mname=getStudentByNumTable1.getName(lcode, mnum);
					//model.addAttribute("mname", mname);
					return "jsonView";	
		}	
		
		
		@RequestMapping(value = "/careerExcel2", method = RequestMethod.GET)
	    public String careerExcel2(String num, String lcode, String mnum, HttpServletRequest request) throws Exception {
			service.careerExcel2(num, lcode, mnum, request);		
			return "jsonView";		   
	    }
		@RequestMapping(value = "/careerExcelDown2", method = RequestMethod.GET)
	    public String careerExcelDown2(String num, String lcode, String mnum, Model model, HttpServletRequest request, HttpServletResponse response) {
				
					model.addAttribute("lcode", lcode);
					model.addAttribute("mnum", mnum);
					//파일명 가져오기	
					String fileName=null;
					fileName=service.excelFileDownloadProcess1(num, lcode, mnum, request);
					model.addAttribute("fileName", fileName);
				    return "careerExcel2";
	    }	
		
}
