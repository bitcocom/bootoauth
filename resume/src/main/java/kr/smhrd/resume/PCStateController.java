package kr.smhrd.resume;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.smhrd.model.McourseVO;
import kr.smhrd.model.MentorVO;
import kr.smhrd.model.ResumeDAO;
import kr.smhrd.service.ExcelService;

@Controller
public class PCStateController {
	@Autowired
	private ResumeDAO resumeDAO;
	@Autowired
	private ExcelService service;

	@RequestMapping(value = "/mentorinsert", method = RequestMethod.POST)
    public String mentorinsert(MentorVO vo, HttpServletRequest request) {
		//System.out.println(vo);
		String[] mtpart = request.getParameterValues("mtpart");
		String mtpartArr="";
		for(int i=0;i<mtpart.length;i++) {
			mtpartArr+=mtpart[i]+"|";
		}
		int endpos=mtpartArr.lastIndexOf("|");
		mtpartArr=mtpartArr.substring(0, endpos);
		vo.setMtpart(mtpartArr);
		//System.out.println(mtpartArr);
		service.mentorinsert(vo);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/dbnamecheck", method = RequestMethod.POST)
    public String dbnamecheck(String mtname,Model model) {
		
		int cnt=service.dbnamecheck(mtname);
		model.addAttribute("cnt", cnt);
		return "jsonView";		   
    }
	
	// dbnamecheck
}
