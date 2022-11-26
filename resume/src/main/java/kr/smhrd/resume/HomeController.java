package kr.smhrd.resume;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.org.apache.xml.internal.security.utils.XalanXPathFactory;

import kr.smhrd.model.CareerVO;
import kr.smhrd.model.LcourseVO;
import kr.smhrd.model.LoginVO;
import kr.smhrd.model.McourseVO;
import kr.smhrd.model.ResumeDAO;
import kr.smhrd.model.ResumeVO;
import kr.smhrd.model.SangdamVO;
import kr.smhrd.model.TeamVO;
import kr.smhrd.service.ExcelService;
import sun.print.resources.serviceui;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private ResumeDAO resumeDAO;
	@Autowired
	private ExcelService service;
	@RequestMapping(value = "/uploadExcelFile", method = RequestMethod.POST)
    public String uploadExcelFile(MultipartHttpServletRequest request, Model model, HttpServletRequest request1) {
        MultipartFile file = null;
        Iterator<String> iterator = request.getFileNames();
        if(iterator.hasNext()) {
            file = request.getFile(iterator.next());
            //System.out.println(file.getOriginalFilename());
        }
        String lcode=request.getParameter("lcode");
        String mnum=request.getParameter("mnum");
        List<ResumeVO> list = service.uploadExcelFile(file, lcode, mnum, request1);
       // System.out.println(list.toString());
        model.addAttribute("list", list);
        return "jsonView";
    }
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String uploadForm(String lcode, String mnum, Model model) {
       
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		
        return "excelUpload";
    }
	
/*	@RequestMapping(value = "/cardFile", method = RequestMethod.POST)
    public String cardFile(String lcode, String mnum) {
       
		service.makeCard(lcode, mnum);
		
        return "jsonView";
    }*/
	
	@RequestMapping(value = "/studentLoad", method = RequestMethod.GET)
    public String studentLoad(String lcode, String mnum , Model model) {
       
		List<ResumeVO> list=service.studentLoad(lcode, mnum);
		model.addAttribute("list", list);
		McourseVO vo=resumeDAO.getName1(lcode, mnum);
		model.addAttribute("mname", vo.getMname());
        return "jsonView";
    }
	
	@RequestMapping(value = "/lcourse", method = RequestMethod.GET)
    public String lcourse(Model model) {
		//System.out.println("OK");
		List<LcourseVO> list=service.lcourseLoad();
		 model.addAttribute("list", list);	
			
        return "jsonView";
    }
	
	@RequestMapping(value = "/mcourse", method = RequestMethod.POST)
    public String mcourse(String lcode, Model model) {
		//System.out.println(lcode);
		List<McourseVO> list=service.mcourseLoad(lcode);
		if(list.size()!=0){
			  model.addAttribute("list", list);
		}else {
			  model.addAttribute("list", "not");	
		}
		//System.out.println(list.toString());
        return "jsonView";
    }
	@RequestMapping(value = "/mcourselist", method = RequestMethod.POST)
    public String mcourselist(int mnum, Model model) {
		//System.out.println(mnum);
		List<McourseVO> list=service.mcourselist(mnum);
		if(list.size()!=0){
			  model.addAttribute("list", list);
		}else {
			  model.addAttribute("list", "not");	
		}
		//System.out.println(list.toString());
        return "jsonView";
    }
	// 수료 ON_OFF
	@RequestMapping(value = "/state1update", method = RequestMethod.POST)
    public String state1update(int num,String state1, int mnum, Model model) {
	   // System.out.println(num+":"+state1+":"+mnum);
		service.state1update(num, state1);		
		if(state1.equals("YES")) {
			service.state1updateYES(mnum);
		}else {
			service.state1updateNO(mnum);
		}
		// 수료율 갱신
		service.state1updateYESNO(mnum);
		
		return "jsonView";
    }	
	// 취업 ON_OFF
	@RequestMapping(value = "/state2update", method = RequestMethod.POST)
    public String state2update(int num,String state2, int mnum, Model model) {
	    //System.out.println(num+":"+state2+":"+mnum);
		service.state2update(num, state2);
		if(state2.equals("YES")) {
			service.state2updateYES(mnum);
			CareerVO vo=service.careerState2updateYES(num);
			if(vo==null || vo.getCsave().equals("0")) {
				model.addAttribute("save", "nosave");
			}
		}else {
			service.state2updateNO(mnum);
		}
		// 취업률 갱신
		service.state2updateYESNO(mnum);
		
	    return "jsonView";
    }	
	
	@RequestMapping(value = "/imagein", method = RequestMethod.GET)
    public String imagein(int num,String lcode, String mnum, String name, Model model) {
		//System.out.println(num);
		model.addAttribute("num", num);
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		model.addAttribute("name", name);
		return "imagein";		   
    }		
	
	@RequestMapping(value = "/imageupload", method = RequestMethod.POST)
    public String imageupload(MultipartHttpServletRequest request, HttpServletRequest request1) throws Exception {
		MultipartFile file = null;
        Iterator<String> iterator = request.getFileNames();
        if(iterator.hasNext()) {
            file = request.getFile(iterator.next());
            //System.out.println(file.getOriginalFilename());
        }
        String num=request.getParameter("num");
        String lcode=request.getParameter("lcode");
        String mnum=request.getParameter("mnum");
        service.uploadImageFile(file, num, lcode, mnum, request1);
       // System.out.println(list.toString());        
        return "jsonView";
    }
	
	@RequestMapping(value = "/sangdam", method = RequestMethod.POST)
    public String sangdam(int num, Model model) {
		//System.out.println(num);
		List<SangdamVO> list=service.sangdam(num);
		if(list.size()!=0) {
		   model.addAttribute("list", list);
		}else {
		   model.addAttribute("list", "not");
		}
		return "jsonView";		   
    }	
	
	@RequestMapping(value = "/sanginsert", method = RequestMethod.POST)
    public String sanginsert(SangdamVO vo) {
		//System.out.println(num);
		//System.out.println(vo);
		if(vo.getCert()==null || vo.getCert()=="") {
			vo.setCert("-");
		}
		service.sanginsert(vo);		
		return "jsonView";		   
    }	
	
	@RequestMapping(value = "/datadelete", method = RequestMethod.POST)
    public String datadelete(String lcode, String mnum, HttpServletRequest request) {
		service.datadelete(lcode, mnum, request);		
		return "jsonView";		   
    }
	
	/*@RequestMapping(value = "/sangdamno", method = RequestMethod.POST)
    public String sangdamno(String lcode, String mnum) {
		service.sangdamno(lcode, mnum);		
		return "jsonView";		   
    }*/
	
	@RequestMapping(value = "/sangdamsave", method = RequestMethod.POST)
    public String sangdamsave(String num, String lcode, String mnum, HttpServletRequest request) throws Exception {
		service.sangdamsave(num, lcode, mnum, request);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/downloadExcelFile", method = RequestMethod.POST)
    public String downloadExcelFile(String num, String lcode, String mnum, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String fileName=service.excelFileDownloadProcess(num, lcode, mnum, request);
        SXSSFWorkbook workbook = new SXSSFWorkbook(new XSSFWorkbook(fileName));
        //model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("fileName", fileName);
        model.addAttribute("workbook", workbook);
        //model.addAttribute("workbookName", "과일표");
        
        return "downloadView";
    }

	@RequestMapping(value = "/download", method = RequestMethod.GET)
    public String sangdamdown(String num,String lcode, String mnum, Model model, HttpServletRequest request, HttpServletResponse response) {
	
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		//파일명 가져오기	
		String fileName=null;
		fileName=service.excelFileDownloadProcess(num , lcode, mnum, request);
		model.addAttribute("fileName", fileName);
	    return "sangdamload";
    }
	
	@RequestMapping(value = "/fileDown", method = RequestMethod.GET)
    public String fileDown(String lcode, String mnum, String filecol, Model model, HttpServletRequest request) {
		String mname=resumeDAO.getName(lcode, mnum);
		//String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+mname;
		
		String saveDir = request.getSession().getServletContext().getRealPath("/resources/"+mname); 
		File file = new File(saveDir + "/" + filecol);
		
		//File file = new File(folder, filecol);
		model.addAttribute("file", file);
	    return "downloadView";
    }
	
	@RequestMapping(value = "/makeImgList", method = RequestMethod.POST)
    public String makeImgList(String lcode, String mnum, HttpServletRequest request) throws Exception {
		service.makeImgList(lcode, mnum, request);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/imageList", method = RequestMethod.GET)
    public String imageList(String lcode, String mnum, Model model, HttpServletRequest request, HttpServletResponse response) {
	
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		//파일명 가져오기	
		String fileName=null;
		fileName=service.excelFileDownloadProcess(lcode, mnum, request);
		model.addAttribute("fileName", fileName);
	    return "imageList";
    }
	
	@RequestMapping(value = "/certInsert", method = RequestMethod.POST)
    public String certInsert(ResumeVO vo) {
		//System.out.println(num);
		//System.out.println(vo);		
		service.certInsert(vo);		
		return "jsonView";		   
    }	
	
	@RequestMapping(value = "/sangdeletenum", method = RequestMethod.POST)
    public String sangdeletenum(SangdamVO vo) {
		service.sangdeletenum(vo);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
			
		return "main";		   
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(LoginVO vo, HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(vo.getId().equals("admin") && vo.getPwd().equals("admin")) {	
			session.setAttribute("admin", vo.getId());
		}else {
			session.setAttribute("admin", "not");
		}
		return "redirect:/main";		   
    }
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
    public String index(LoginVO vo, HttpServletRequest request) {
		
		return "index";		   
    }
	
	@RequestMapping(value = "/courseinsert", method = RequestMethod.POST)
    public String courseinsert(McourseVO vo) {
		//System.out.println(vo);
		service.courseinsert(vo);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/mdelete", method = RequestMethod.GET)
    public String mdelete(McourseVO vo, HttpServletRequest reuest) {
		service.mdelete(vo, reuest);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/memupdate", method = RequestMethod.POST)
    public String memupdate(McourseVO vo) {
		service.memupdate(vo);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/isisdata", method = RequestMethod.GET)
    public String isisdata(int num, int mnum) {
		service.isisdata(num, mnum);		
		return "jsonView";		   
    }
	// 교육운영과정 엑셀에저장
	@RequestMapping(value = "/courseexcel", method = RequestMethod.GET)
    public String courseexcel(HttpServletRequest request) throws Exception {
		service.courseexcel(request);		
		return "jsonView";		   
    }
	
	// 교육운영과정 엑셀다운로드
	@RequestMapping(value = "/courseList", method = RequestMethod.GET)
    public String courseList(Model model, HttpServletRequest request, HttpServletResponse response) {
		//파일명 가져오기	
		String fileName=null;
		fileName=service.excelCourseDownloadProcess();
		model.addAttribute("fileName", fileName);
	    return "courseList";
    }
	
	@RequestMapping(value = "/fileCourseDown", method = RequestMethod.GET)
    public String fileCourseDown(String filecol, Model model, HttpServletRequest request) throws Exception {		
		//String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+filecol;
		
		//String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/file");
		//URL uri=new URL("http://127.0.0.1:8081/resume/resources/"+filecol);
		//System.out.println(uri.getFile());
		//String folder="http:\\127.0.0.1:8081\\resume\\resources\\"+filecol;
		String saveDir = request.getSession().getServletContext().getRealPath("/resources"); 
		//String fileName = "20190223-223005277_939.jpg"; 
		File file = new File(saveDir + "/" + filecol);
		
		//File file = new File(folder);
		//request.
		//System.out.println(config.getServletContext().getRealPath(request.getRequestURI()));
		model.addAttribute("file", file);
	    return "downloadView";
    }

	@RequestMapping(value = "/team", method = RequestMethod.GET)
    public String team(String lcode, String mnum, Model model) {
	    
		
		List<ResumeVO> list=service.studentLoad(lcode, mnum);
		String mname=resumeDAO.getName(lcode, mnum);
		model.addAttribute("list", list);
		
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		model.addAttribute("mname", mname);
	    return "team";
    }
	
	@RequestMapping(value = "/teamInsert", method = RequestMethod.POST)
    public String teamInsert(TeamVO vo, Model model) {
	  
		service.teamInsert(vo);
		
	    return "jsonView";
    }
	
	@RequestMapping(value = "/teamDelete", method = RequestMethod.POST)
    public String teamDelete(int num) {
	  
		service.teamDelete(num);
		
	    return "jsonView";
    }
	
	@RequestMapping(value = "/teamAllDelete", method = RequestMethod.POST)
    public String teamAllDelete(TeamVO vo, Model model) {
	  
		
		service.teamAllDelete(vo);
		
	    return "jsonView";
    }
	
	// TEAM 엑셀에저장
	@RequestMapping(value = "/teamExcel", method = RequestMethod.POST)
    public String teamExcel(TeamVO vo, HttpServletRequest request) throws Exception {
		service.teamExcel(vo, request);		
		return "jsonView";		   
    }
	
	@RequestMapping(value = "/teamDownload", method = RequestMethod.GET)
    public String teamDownload(String lcode, String mnum, Model model, HttpServletRequest request, HttpServletResponse response) {
	
		model.addAttribute("lcode", lcode);
		model.addAttribute("mnum", mnum);
		//파일명 가져오기	
		String fileName=null;
		fileName=service.teamDownloadProcess(lcode, mnum, request);
		model.addAttribute("fileName", fileName);
	    return "teamDownload";
    }
	
	@RequestMapping(value = "/mentor", method = RequestMethod.GET)
    public String mentor(String lcode, String mnum, Model model) {
	    
	
	    return "mentor";
    }
	
	@RequestMapping(value = "/jsondata", method = RequestMethod.GET)
    public String jsondata(Model model) {
       
		model.addAttribute("lcode", "aaa");
				
        return "jsonView";
    }
}
