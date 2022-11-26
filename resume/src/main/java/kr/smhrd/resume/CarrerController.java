package kr.smhrd.resume;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.smhrd.model.CareerVO;
import kr.smhrd.model.McourseVO;
import kr.smhrd.model.MentorVO;
import kr.smhrd.model.ResumeCareerVO;
import kr.smhrd.model.ResumeDAO;
import kr.smhrd.model.ResumeVO;
import kr.smhrd.service.ExcelService;

@Controller
public class CarrerController {
	@Autowired
	private ResumeDAO resumeDAO;
	@Autowired
	private ExcelService service;

	@RequestMapping(value = "/career", method = RequestMethod.GET)
    public String team(String lcode, String mnum, Model model) {
	    
		
		List<ResumeVO> list=service.studentLoad(lcode, mnum);
		String mname=resumeDAO.getName(lcode, mnum);
		McourseVO vo=resumeDAO.getName1(lcode, mnum);
		model.addAttribute("list", list);
		
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		model.addAttribute("mname", mname);
		model.addAttribute("mday", vo.getMday());
		model.addAttribute("master", vo.getMmaster());
		model.addAttribute("mta", vo.getMta());
		
	    return "career";
    }	
	
	@RequestMapping(value = "/career1", method = RequestMethod.GET)
    public String team1(String lcode, String mnum, Model model) {
	    
		
		List<ResumeVO> list=service.studentLoad(lcode, mnum);
		String mname=resumeDAO.getName(lcode, mnum);
		McourseVO vo=resumeDAO.getName1(lcode, mnum);
		model.addAttribute("list", list);
		
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		model.addAttribute("mname", mname);
		model.addAttribute("mday", vo.getMday());
		model.addAttribute("master", vo.getMmaster());
		model.addAttribute("mta", vo.getMta());
		
	    return "career1";
    }
	
	// dbnamecheck
	@RequestMapping(value = "/getStudentByNum1", method = RequestMethod.GET)
    public String getStudentByNum1(String lcode, String mnum, int num,  Model model) {
	    ResumeCareerVO vo=service.getStudentByNum1(num);
		model.addAttribute("vo",vo);
		String mname=resumeDAO.getName(lcode, mnum);
		//model.addAttribute("mname", mname);
		return "jsonView";	
    }	
	// dbnamecheck
	@RequestMapping(value = "/cpart", method = RequestMethod.GET)
	public String cpart(CareerVO vo,  Model model) {
		    //System.out.println(vo);
		    service.cpart(vo);			
			return "jsonView";	
	}	
	@RequestMapping(value = "/cpartcancel", method = RequestMethod.GET)
	public String cpartcancel(CareerVO vo,  Model model) {
		    //System.out.println(vo);
		    service.cpartcancel(vo);			
			return "jsonView";	
	}
	// dbnamecheck
		@RequestMapping(value = "/cpart1", method = RequestMethod.GET)
		public String cpart1(CareerVO vo,  Model model) {
			    //System.out.println(vo);
			    service.cpart1(vo);			
				return "jsonView";	
		}	
		@RequestMapping(value = "/cpartcancel1", method = RequestMethod.GET)
		public String cpartcancel1(CareerVO vo,  Model model) {
			    //System.out.println(vo);
			    service.cpartcancel1(vo);			
				return "jsonView";	
		}
		// dbnamecheck
		@RequestMapping(value = "/cpart2", method = RequestMethod.GET)
		public String cpart2(CareerVO vo,  Model model) {
					    //System.out.println(vo);
					    service.cpart2(vo);			
						return "jsonView";	
		}	
		@RequestMapping(value = "/cpartcancel2", method = RequestMethod.GET)
		public String cpartcancel2(CareerVO vo,  Model model) {
					    //System.out.println(vo);
					    service.cpartcancel2(vo);			
						return "jsonView";	
		}
		@RequestMapping(value = "/skillcontent", method = RequestMethod.POST)
		public String skillcontent(CareerVO vo,  Model model) {
			    //System.out.println(vo);
			    service.skillcontent(vo);			
				return "jsonView";	
		}
		
		@RequestMapping(value = "/careerExcel", method = RequestMethod.GET)
	    public String careerExcel(String num, String lcode, String mnum, HttpServletRequest request) throws Exception {
			service.careerExcel(num, lcode, mnum, request);		
			return "jsonView";		   
	    }
		
		// dbnamecheck
		@RequestMapping(value = "/csave", method = RequestMethod.POST)
		public String csave(CareerVO vo, Model model) {
					   // System.out.println(vo);
					    service.csave(vo);			
						return "jsonView";	
		}	
		// dbnamecheck
		@RequestMapping(value = "/cstart", method = RequestMethod.POST)
		public String cstart(CareerVO vo, Model model) {
							   // System.out.println(vo);
							    service.cstart(vo);			
								return "jsonView";	
		}	
				
				// dbnamecheck
		@RequestMapping(value = "/cinsur", method = RequestMethod.POST)
		public String cinsur(CareerVO vo, Model model) {
							   // System.out.println(vo);
							    service.cinsur(vo);			
								return "jsonView";	
		}
				
		@RequestMapping(value = "/careerExcelDown", method = RequestMethod.GET)
	    public String imageList(String num, String lcode, String mnum, Model model, HttpServletRequest request, HttpServletResponse response) {
				
					model.addAttribute("lcode", lcode);
					model.addAttribute("mnum", mnum);
					//파일명 가져오기	
					String fileName=null;
					fileName=service.excelFileDownloadProcess1(num, lcode, mnum, request);
					model.addAttribute("fileName", fileName);
				    return "careerExcel";
	    }	
		
		@RequestMapping(value = "/addr", method = RequestMethod.POST)
		public String addr(ResumeVO vo, Model model) {
					//System.out.println(vo);
		            service.addr(vo);			
					return "jsonView";	
		}
		
		@RequestMapping(value = "/careerForm", method = RequestMethod.GET)
		public String careerForm() {
						
					return "careerForm";	
		}
}