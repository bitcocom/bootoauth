package kr.smhrd.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.smhrd.model.McourseVO;
import kr.smhrd.model.ResumeCareerVO;
import kr.smhrd.model.ResumeDAO;
import kr.smhrd.model.ResumeVO;
import kr.smhrd.model.TableDAO;
import kr.smhrd.model.TimeDAO;
import kr.smhrd.model.TimeVO;

@Service
public class TimeService {
	
	  @Autowired
	  private TimeDAO timeDAO;
	 
	  @Autowired
	  private ResumeDAO resumeDAO; 
	  public List<TimeVO> timeLoad(String lcode, String mnum,  HttpServletRequest request) {
		
		    List<TimeVO> list = new ArrayList<TimeVO>();
	        try {
	    	    // 과정명 폴더만들기
	             String mname=resumeDAO.getName(lcode, mnum);
		       //  System.out.println(mname);
		         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
		         //File uploadPath=new File(folder, mname);
		         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
			 	 File uploadPath = new File(folder,mname);
			 	 
		         //ResumeCareerVO vo=resumeDAO.getStudentByNum(num);
		         //C:\Users\SMHRD\Dropbox\훈련일지시간표_2021\K디지털 IoT 2차(정형)\진행예정\
	    	     //ClassPathResource resource = new ClassPathResource("file:C:/Users/SMHRD/Dropbox/훈련일지시간표_2021/K디지털 IoT 2차(정형)/진행예정/지능형 IoT 융합SW 개발자 과정(NCS).xlsx");	    	  
	    	     //FileSystemResource fsr=new FileSystemResource("C:\\Users\\SMHRD\\Dropbox\\훈련일지시간표_2021\\LMS시간표\\"+mname+".xlsx");
			 	 McourseVO mvo=resumeDAO.getName1(lcode, mnum);
			 	// ClassPathResource resource = new ClassPathResource(uploadPath+"/"+mvo.getIstime());	 
			 	  FileSystemResource fsr=new FileSystemResource(uploadPath+"/"+mvo.getIstime());
	    	      XSSFWorkbook workbook = new XSSFWorkbook(fsr.getInputStream());
	    	      int sheetNum = workbook.getNumberOfSheets();
	    	      XSSFSheet sheet = workbook.getSheetAt(sheetNum-1);
	    	      
	    	      int endRow=sheet.getLastRowNum();
	    	    //  System.out.println(endRow);
	    	     
	    	      for(int i=8; i< endRow; i++) {
	    	    	TimeVO vo = new TimeVO();     
	                XSSFRow row = sheet.getRow(i);
	             // System.out.println(sheet.getSheetName());
	                // 행이 존재하기 않으면 패스
	                if(null == row) {
	                    continue;
	                }	                
	                // 행의 두번째 열(이름부터 받아오기) 
	                XSSFCell cell = row.getCell(0);
	                if(null != cell) {
	                	vo.setTday(cell.getStringCellValue()); //구분
	                //System.out.println(cell.getStringCellValue());
	                }else {
	                	break;
	                }
	                // 행의 세번째 열 받아오기
	                //String.valueOf(cell.getDateCellValue());

	                cell = row.getCell(1);
	                if(null != cell) vo.setTdate(cell.getDateCellValue());//요일
	                	
	                cell = row.getCell(2);
	                if(null != cell) {
	                	
	                	vo.setTmon(cell.getStringCellValue());//요일
	                	
	                }
	                //System.out.println(fruit);
	                //System.out.println(cell.getStringCellValue());
	                
	                cell = row.getCell(3);
	                if(null != cell) vo.setTstep(cell.getStringCellValue());//교시
	                //System.out.println(fruit);
	                //System.out.println(cell.getStringCellValue());

	                cell = row.getCell(4);
	                if(null != cell) vo.setTtime(cell.getStringCellValue()); // 시간
	                //System.out.println(fruit);
	                //System.out.println(cell.getStringCellValue());
		            
	                cell = row.getCell(5);
		            if(null != cell) vo.setTcourse(cell.getStringCellValue());//과목명
		                //System.out.println(fruit);
		                //System.out.println(cell.getStringCellValue());
		            cell = row.getCell(6);
			        if(null != cell) vo.setTteach(cell.getStringCellValue());//강사명
			                //System.out.println(fruit);
			                //System.out.println(cell.getStringCellValue());
			        cell = row.getCell(7);
			        if(null != cell) vo.setTreal(cell.getStringCellValue()); // 장소
			                //System.out.println(fruit);
			                //System.out.println(cell.getStringCellValue());
			        
			        cell = row.getCell(8);
			        if(null != cell) vo.setTinc(cell.getStringCellValue()); // 누적시간
			                //System.out.println(fruit);
			                //System.out.println(cell.getStringCellValue()); 
			      			       
			        list.add(vo);
			       
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	          return list;		  
	 }

	public void courseWrite(String lcode, String mnum, String mday, String endKey, TimeVO vo, HttpServletRequest request) {
		 
		//엑셀원본을 가지고 오고 -> 학생정보가지고와서->반복하면서 엑셀에 저장하기.
        try {				
       	     String mname=resumeDAO.getName(lcode, mnum);
             	         
	         String saveDir = request.getSession().getServletContext().getRealPath("/resources"); 
	 		//String fileName = "20190223-223005277_939.jpg"; 
	 		 File uploadPath = new File(saveDir, mname);
	 		
	         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
	         //File uploadPath=new File(folder);
	        		         
    	      ClassPathResource resource = new ClassPathResource("훈련일지.xlsx");	  
    	     
    	      XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
    	      XSSFSheet sheet = workbook.getSheetAt(0);
	    	  
	    	  XSSFRow row=sheet.getRow(1);
	    	  XSSFCell cell=row.getCell(1);
	    	  cell.setCellValue(mname);
    	
	    	  row=sheet.getRow(2);
	    	  cell=row.getCell(1);
	    	  cell.setCellValue(mday+"("+endKey+")");
    	
	    	  row=sheet.getRow(3);
	    	  cell=row.getCell(0);
	    	  cell.setCellValue(vo.getTdate());
	    	  
	    	  for(int i=0;i<=7;i++) {
	    		  row=sheet.getRow(i+9);
		    	  cell=row.getCell(1);
		    	  cell.setCellValue(vo.getTcourse().split("#")[i]);	
		    	  
		    	  cell=row.getCell(4);
		    	  cell.setCellValue(vo.getTteach().split("#")[i]);	
		    	  
		    	  cell=row.getCell(6);
		    	  cell.setCellValue(vo.getTcourse().split("#")[i]);	
	    	  }
	    	      	
	    	  // 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
    	      String fileName =mname + "(훈련일지).xlsx";
    	      FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
    	      workbook.write(fos);
    	    //  System.out.println("생성");
    	      workbook.close();
    	      fos.close();
    	      
		} catch (Exception e) {
           e.printStackTrace();
		}		
	}

	public String excelFileDownloadProcess1(String lcode, String mnum, HttpServletRequest request) {
			
		 	 // 과정명 폴더만들기
            String mname=resumeDAO.getName(lcode, mnum);
	       //  System.out.println(mname);
	         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
	         //File uploadPath=new File(folder, mname);
	         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
		 	 File uploadPath = new File(folder,mname);
	         //http://172.30.1.59:8081/resume/resources/이름_941205-2667416.xlsx
	         String fileName=mname+"(훈련일지).xlsx";
	       //  System.out.println(fileName);
	         return fileName;
		}

	  public void uploadExcelFile(MultipartFile excelFile, String lcode, String mnum, HttpServletRequest request){
	     
		            String fileName=excelFile.getOriginalFilename();
			        resumeDAO.updateTime(lcode, mnum, fileName);
			        // 과정명으로 디렉토리 만들기
			        //System.out.println(mname);
			        String mname=resumeDAO.getName(lcode, mnum);			        
			        //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
			        //File uploadPath=new File(folder, mname);
			        String folder = request.getSession().getServletContext().getRealPath("/resources/"+mname); 
				 	File uploadPath = new File(folder,fileName);			        
				 	try {
						excelFile.transferTo(uploadPath);
					} catch (Exception e) {						
						e.printStackTrace();
					}       
	    }
}
