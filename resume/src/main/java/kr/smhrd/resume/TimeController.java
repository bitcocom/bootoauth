package kr.smhrd.resume;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.smhrd.model.McourseVO;
import kr.smhrd.model.ResumeDAO;
import kr.smhrd.model.ResumeVO;
import kr.smhrd.model.TimeVO;
import kr.smhrd.service.TimeService;

@Controller
public class TimeController {

	@Autowired
	private ResumeDAO resumeDAO;
	
	@Autowired
	private TimeService service;
	
	@RequestMapping(value = "/career2", method = RequestMethod.GET)
    public String team1(String lcode, String mnum, Model model) {
	    
		//List<TimeVO> list=service.timeLoad(lcode, mnum);
		String mname=resumeDAO.getName(lcode, mnum);
		McourseVO vo=resumeDAO.getName1(lcode, mnum);
		//model.addAttribute("list", list);
		
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		model.addAttribute("mname", mname);
		model.addAttribute("mday", vo.getMday());
		model.addAttribute("master", vo.getMmaster());
		model.addAttribute("mta", vo.getMta());
		
	    return "career2";
    }
	
	@RequestMapping(value = "/timeLoad", method = RequestMethod.GET)
    public String timeLoad(String lcode, String mnum , Model model, HttpServletRequest request) {
       
		McourseVO vo=resumeDAO.getName1(lcode, mnum);
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		model.addAttribute("mname", vo.getMname());
		model.addAttribute("mday", vo.getMday());
		model.addAttribute("istime", vo.getIstime());
		if(!vo.getIstime().equals("NO")) {		
		  List<TimeVO> list=service.timeLoad(lcode, mnum, request);
		  model.addAttribute("list", list);
		}
		
        return "jsonView";
    }
	
	@RequestMapping(value = "/timeFileUpload", method = RequestMethod.GET)
    public String timeFileUpload(String lcode, String mnum , Model model, HttpServletRequest request) {
       
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		
        return "timeFileUpload";
    }
	
	@RequestMapping(value = "/uploadExcelFile1", method = RequestMethod.POST)
    public String uploadExcelFile1(MultipartHttpServletRequest request, Model model, HttpServletRequest request1) {
        MultipartFile file = null;
        Iterator<String> iterator = request.getFileNames();
        if(iterator.hasNext()) {
            file = request.getFile(iterator.next());
           // System.out.println(file.getOriginalFilename());
        }
        String lcode=request.getParameter("lcode");
        String mnum=request.getParameter("mnum");
        service.uploadExcelFile(file, lcode, mnum, request1);
        // System.out.println(list.toString());
        //model.addAttribute("list", list);
    	model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		
        return "jsonView";
    }
	
	@RequestMapping(value = "/courseWrite", method = RequestMethod.POST)
    public String courseWrite(String lcode, String mnum, String mday, TimeVO vo, String endKey, HttpServletRequest request) throws Exception {
		//System.out.println(vo);
		service.courseWrite(lcode, mnum, mday, endKey, vo, request);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/courseDown", method = RequestMethod.GET)
    public String courseDown(String lcode, String mnum, Model model, HttpServletRequest request, HttpServletResponse response) {
			
				model.addAttribute("lcode", lcode);
				model.addAttribute("mnum", mnum);
				//파일명 가져오기	
				String fileName=null;
				fileName=service.excelFileDownloadProcess1(lcode, mnum, request);
				model.addAttribute("fileName", fileName);
			    return "courseDown";
    }
}
