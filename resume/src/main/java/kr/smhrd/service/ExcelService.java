package kr.smhrd.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.smhrd.model.CareerVO;
import kr.smhrd.model.LcourseVO;
import kr.smhrd.model.McourseVO;
import kr.smhrd.model.MentorVO;
import kr.smhrd.model.ResumeCareerVO;
import kr.smhrd.model.ResumeDAO;
import kr.smhrd.model.ResumeVO;
import kr.smhrd.model.SangdamVO;
import kr.smhrd.model.TeamVO;
import sun.font.CreatedFontTracker;

@Service
public class ExcelService {
	  @Autowired
	  private ResumeDAO resumeDAO;
	  public List<ResumeVO> uploadExcelFile(MultipartFile excelFile, String lcode, String mnum, HttpServletRequest request){
	        List<ResumeVO> list = new ArrayList<ResumeVO>();
	        try {
	            OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
	            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
	            
	            // 첫번째 시트 불러오기
	            XSSFSheet sheet = workbook.getSheetAt(0);
	            
	            for(int i=2; i<sheet.getLastRowNum() + 1; i++) {
	                ResumeVO fruit = new ResumeVO();
	                XSSFRow row = sheet.getRow(i);
	                //System.out.println(sheet.getSheetName());
	                // 행이 존재하기 않으면 패스
	                if(null == row) {
	                    continue;
	                }	                
	                // 행의 두번째 열(이름부터 받아오기) 
	                XSSFCell cell = row.getCell(4);
	                if(null != cell) fruit.setName(cell.getStringCellValue());
	                //System.out.println(cell.getStringCellValue());
	                // 행의 세번째 열 받아오기
	                cell = row.getCell(5);
	                if(null != cell) fruit.setSsn(cell.getStringCellValue());
	                // 행의 네번째 열 받아오기
	                //System.out.println(cell.getStringCellValue());
	                
	                cell = row.getCell(7);
	                if(null != cell) fruit.setTel(cell.getStringCellValue());
	                //System.out.println(fruit);
	                //System.out.println(cell.getStringCellValue());
	                
	                cell = row.getCell(8);
	                if(null != cell) fruit.setEmail(cell.getStringCellValue());
	                //System.out.println(fruit);
	                //System.out.println(cell.getStringCellValue());

	                cell = row.getCell(9);
	                if(null != cell) fruit.setSex(cell.getStringCellValue());
	                //System.out.println(fruit);
	                //System.out.println(cell.getStringCellValue());
		            
	                cell = row.getCell(10);
		            if(null != cell) fruit.setBirth((int)cell.getNumericCellValue()+"");
		                //System.out.println(fruit);
		                //System.out.println(cell.getStringCellValue());
		            cell = row.getCell(11);
			        if(null != cell) fruit.setCollege(cell.getStringCellValue());
			                //System.out.println(fruit);
			                //System.out.println(cell.getStringCellValue());
			        cell = row.getCell(12);
			        if(null != cell) fruit.setMajor(cell.getStringCellValue());
			                //System.out.println(fruit);
			                //System.out.println(cell.getStringCellValue());
			        fruit.setLcode(lcode);
			        fruit.setMnum(mnum);
			        resumeDAO.memberInsert(fruit);
			        // 과정명으로 디렉토리 만들기
			        //System.out.println(mname);
			        String mname=resumeDAO.getName(lcode, mnum);
			        
			        //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
			        //File uploadPath=new File(folder, mname);
			         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
				 	 File uploadPath = new File(folder,mname);
			        
			        if(uploadPath.exists()==false) {
			        	uploadPath.mkdirs();
			        }
			        list.add(fruit);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    public void makeCard(String lcode, String mnum, HttpServletRequest request) {
	      try {
	    	  // 과정명 폴더만들기
	            String mname=resumeDAO.getName(lcode, mnum);
		        //System.out.println(mname);
		        //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
		        //File uploadPath=new File(folder, mname);
		        String folder = request.getSession().getServletContext().getRealPath("/resources"); 
			 	 File uploadPath = new File(folder,mname);
			 	 
		        if(uploadPath.exists()==false) {
		        	uploadPath.mkdirs();
		        }
		        
	    	    /* ClassPathResource resource = new ClassPathResource("상담일지원본.xlsx");	    	  
	    	     List<ResumeVO> list=resumeDAO.memberList(lcode, mnum);
	    	     for(ResumeVO vo: list) {
	    		  XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
		    	  XSSFSheet sheet = workbook.getSheetAt(0);
		    	  System.out.println(sheet.getSheetName());
		    	  XSSFRow row=sheet.getRow(0);
		    	  XSSFCell cell=row.getCell(2);
		    	  cell.setCellValue(mname);	 
		    	  
		    	  row=sheet.getRow(3);
	    		  cell=row.getCell(4);
	    		  cell.setCellValue(vo.getName());	    		  
	    		  cell=row.getCell(7);
	    		  cell.setCellValue(vo.getSsn());
	    		  
	    		  row=sheet.getRow(4);
	    		  cell=row.getCell(4);
	    		  cell.setCellValue(vo.getCollege());
	    		  cell=row.getCell(7);
	    		  cell.setCellValue(vo.getTel());
	    		  
	    		  row=sheet.getRow(5);
	    		  cell=row.getCell(4);
	    		  cell.setCellValue(vo.getMajor());	    		  
	    		  
	    		// 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
	    	      Date date = new Date();
	    	      SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
	    	      String day = dayformat.format(date);
	    	      String fileName =vo.getName()+ "_" + vo.getSsn() + ".xlsx";
	    	      FileOutputStream fos=new FileOutputStream(uploadPath+"\\"+fileName);
	    	      workbook.write(fos);
	    	      System.out.println("생성");
	    	      workbook.close();
	    	      
	    	      resumeDAO.makeCard(mnum);  
	    	  }	 */   	  
	    	  
		   } catch (Exception e) {
			   e.printStackTrace();		
	     }
	    }
	      public List<ResumeVO> studentLoad(String lcode, String mnum){
	    	  	    	  
	    	 List<ResumeVO> list=resumeDAO.memberList(lcode, mnum);
	    	  
	    	 return list;
	      }
		public List<LcourseVO> lcourseLoad() {
			List<LcourseVO> list=resumeDAO.lcourseLoad();
			return list;
		}
		public List<McourseVO> mcourseLoad(String lcode) {
			List<McourseVO> list=null;
			if(lcode.equals("0")) {
				list=resumeDAO.mcourseLoadall(lcode);
			}else {
				list=resumeDAO.mcourseLoad(lcode);
			}			
			return list;
		}
		public List<McourseVO> mcourselist(int mnum) {
			List<McourseVO> list=resumeDAO.mcourselist(mnum);            			
			return list;
		}
		public void state1update(int num, String state1) {
			ResumeVO vo=new ResumeVO();
			vo.setNum(num);
			vo.setState1(state1);
			resumeDAO.state1update(vo);
		}
		public void state2update(int num, String state2) {
			ResumeVO vo=new ResumeVO();
			vo.setNum(num);
			vo.setState2(state2);
			resumeDAO.state2update(vo);
			
		}
		public void uploadImageFile(MultipartFile file, String num, String lcode, String mnum, HttpServletRequest request) throws Exception {
			 String originalFileName=file.getOriginalFilename();
			 //originalFileName=new String(originalFileName.getBytes("EUC-kr"),"8859_1");
			 String path=resumeDAO.getName(lcode, mnum);
			 try {
			String saveDir = request.getSession().getServletContext().getRealPath("/resources/"+path); 
					//String fileName = "20190223-223005277_939.jpg"; 
			File f = new File(saveDir + "/" + originalFileName);	 
				 
			 //File f=new File("C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+path+"\\"+originalFileName);
			 f.delete();
			 if(file.getSize()!=0) {
				 if(!f.exists()) {
					 if(f.getParentFile().mkdirs()) {
						 f.createNewFile();
					 }
				 }
				 //file.transferTo(new File("C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+path+"\\"+originalFileName));
				 file.transferTo(new File(saveDir + "/" + originalFileName));
			 }
			 resumeDAO.uploadImageFile(path+"/"+originalFileName, num);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }	
		}
		public List<SangdamVO> sangdam(int num) {
			
			List<SangdamVO> list=resumeDAO.sangdam(num);			
			
			return list;
		}
		public void sanginsert(SangdamVO vo) {
			resumeDAO.sanginsert(vo);			
		}
		
		//교육생 미등록 상태
		public void datadelete(String lcode, String mnum, HttpServletRequest request) {
			McourseVO vo=new McourseVO();
			vo.setLcode(lcode);
			vo.setMnum(Integer.parseInt(mnum));
			
			String path=resumeDAO.getName(lcode, mnum);
			//System.out.println("삭제시:"+ path);
			try {
				//File f=new File("C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+path);
				 String uploadPath = request.getSession().getServletContext().getRealPath("/resources/"+path); 
				 File folder = new File(uploadPath);
				//File folder = new File("C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+path);
				try {
				    while(folder.exists()) {
					File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
							
					for (int j = 0; j < folder_list.length; j++) {
						folder_list[j].delete(); //파일 삭제 
						//System.out.println("파일이 삭제되었습니다.");								
					}							
					if(folder_list.length == 0 && folder.isDirectory()){ 
						folder.delete(); //대상폴더 삭제
						//System.out.println("폴더가 삭제되었습니다.");
					}
			      }
				 } catch (Exception e) {
					e.getStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			resumeDAO.datadelete(vo);
		}
	/*	public void sangdamno(String lcode, String mnum) {
			McourseVO vo=new McourseVO();
			vo.setLcode(lcode);
			vo.setMnum(Integer.parseInt(mnum));
			
			String path=resumeDAO.getName(lcode, mnum);
			try {
				//File f=new File("C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+path);
				File folder = new File("C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources\\"+path);
				try {
				    while(folder.exists()) {
					File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
							
					for (int j = 0; j < folder_list.length; j++) {
						folder_list[j].delete(); //파일 삭제 
						System.out.println("파일이 삭제되었습니다.");								
					}							
					if(folder_list.length == 0 && folder.isDirectory()){ 
						folder.delete(); //대상폴더 삭제
						System.out.println("폴더가 삭제되었습니다.");
					}
			      }
				 } catch (Exception e) {
					e.getStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			resumeDAO.sangdamno(vo);
		}*/
		public void sangdamsave(String num, String lcode, String mnum, HttpServletRequest request) throws Exception {
			   try {
			    	    // 과정명 폴더만들기
			             String mname=resumeDAO.getName(lcode, mnum);
				         //System.out.println(mname);
				         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
				         //File uploadPath=new File(folder, mname);
				         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
					 	 File uploadPath = new File(folder,mname);
					 	 
				         ResumeCareerVO vo=resumeDAO.getStudentByNum(num);
				         
			    	     ClassPathResource resource = new ClassPathResource("상담일지원본.xlsx");	    	  
			    	  
		     		      
			    	      XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
			    	      XSSFSheet sheet = workbook.getSheetAt(0);
				    	  System.out.println(sheet.getSheetName());
				    	  XSSFRow row=sheet.getRow(0);
				    	  XSSFCell cell=row.getCell(2);
				    	  cell.setCellValue(mname);	 
				    	  
			    	      // 엑셀에 이미지 저장하기
				    	  if(!vo.getImage().equals("NO")) {
				    	  int pictureIdx=0;
				    	  InputStream is=new FileInputStream(folder+"/"+vo.getImage());
					      byte[] bytes=IOUtils.toByteArray(is);
					      String type=vo.getImage().substring(vo.getImage().lastIndexOf(".")+1);
					      if(type.toLowerCase().equals("png")) {
					    	  pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
					      }else if(type.toLowerCase().equals("jpg")){
					    	  pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG); 
					      }else if(type.toLowerCase().equals("gif")){
						      pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_GIF); 
						  }
					      is.close();
					      
					      CreationHelper helper=workbook.getCreationHelper();
					      Drawing drawing=sheet.createDrawingPatriarch();
					      
					      ClientAnchor anchor=helper.createClientAnchor();
					      anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
					      
					      anchor.setCol1(0);
					      anchor.setRow1(3);
					      anchor.setCol2(2);
					      anchor.setRow2(7);
					      anchor.setDx1(0);
					      anchor.setDx2(0); //정확한 값 기준은 모르겠지만 Dx2, Dy2 1000으로 하면 셀 크기에 딱맞게 줄여져서 나옴. 하단에 pict.resize()는 하면 안됨.
					      anchor.setDy1(0);
					      anchor.setDy2(0);
					      
					      Picture pict=drawing.createPicture(anchor, pictureIdx);
					      //pict.resize();					      
				    	  }
					     /* XSSFCell imgcell=sheet.getRow(3).getCell(0);
					      int w=20*256;
					      //sheet.setColumnWidth(1, w);
					      short h=5*20;
					      imgcell.getRow().setHeight(h);*/
					    
				    	  row=sheet.getRow(3);
			    		  cell=row.getCell(4);
			    		  cell.setCellValue(vo.getName());	    		  
			    		  cell=row.getCell(7);
			    		  cell.setCellValue(vo.getBirth()+"("+getAge(vo.getSsn())+"세)");
			    		  
			    		  row=sheet.getRow(5);
			    		  cell=row.getCell(4);
			    		  cell.setCellValue(vo.getCollege());
			    		  cell=row.getCell(7);
			    		  cell.setCellValue(vo.getTel());
			    		  
			    		  row=sheet.getRow(6);
			    		  cell=row.getCell(4);
			    		  cell.setCellValue(vo.getMajor());	    		  
			    		  cell=row.getCell(7);
			    		  cell.setCellValue(vo.getCert());			    		  
			    		  
			    		  List<SangdamVO> list=resumeDAO.sangdam(Integer.parseInt(num));
			    		  int i=9;
			              for(SangdamVO dto: list) {
			               row=sheet.getRow(i);
			               cell=row.getCell(0);
			               cell.setCellValue(dto.getSdate());   
			              
			               
			               cell=row.getCell(1);
			               cell.setCellValue(dto.getScontent());
			               cell=row.getCell(8);
			               cell.setCellValue(dto.getSname());
			               i++;          
			              }
			    		// 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
			    	      Date date = new Date();
			    	      SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
			    	      String day = dayformat.format(date);
			    	      String fileName =vo.getName()+ "_" + vo.getSsn() + ".xlsx";
			    	      FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
			    	      workbook.write(fos);
			    	      //System.out.println("생성");
			    	      workbook.close();
			    	      fos.close();
			    	      
				   } catch (Exception e) {
					   e.printStackTrace();		
			 }
		}
		public String excelFileDownloadProcess(String num, String lcode, String mnum, HttpServletRequest request) {
			
		 	 // 과정명 폴더만들기
             String mname=resumeDAO.getName(lcode, mnum);
	         //System.out.println(mname);
	         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
	         //File uploadPath=new File(folder, mname);
	         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
		 	 File uploadPath = new File(folder,mname);
	         //http://172.30.1.59:8081/resume/resources/이름_941205-2667416.xlsx
	         ResumeCareerVO vo=resumeDAO.getStudentByNum(num);
	         String fileName=vo.getName()+ "_" + vo.getSsn() + ".xlsx";
	         //System.out.println(fileName);
	         return fileName;
		}
		public String excelFileDownloadProcess(String lcode, String mnum, HttpServletRequest request) {
			
		 	 // 과정명 폴더만들기
            String mname=resumeDAO.getName(lcode, mnum);
	        // System.out.println(mname);
	         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
	         //File uploadPath=new File(folder, mname);
	         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
		 	 File uploadPath = new File(folder,mname);
	         //http://172.30.1.59:8081/resume/resources/이름_941205-2667416.xlsx
	         //ResumeVO vo=resumeDAO.getStudentByNum(num);
	         String fileName=mname + ".xlsx";
	         //System.out.println(fileName);
	         return fileName;
		}
		
		public String excelFileDownloadProcess1(String num, String lcode, String mnum, HttpServletRequest request) {
			
		 	 // 과정명 폴더만들기
            String mname=resumeDAO.getName(lcode, mnum);
	        // System.out.println(mname);
	         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
	         //File uploadPath=new File(folder, mname);
	         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
		 	 File uploadPath = new File(folder,mname);
	         //http://172.30.1.59:8081/resume/resources/이름_941205-2667416.xlsx
	         ResumeCareerVO vo=resumeDAO.getStudentByNum(num);
	         String fileName=vo.getName()+ "_" + vo.getSsn() + "(취업관리카드).xlsx";
	        // System.out.println(fileName);
	         return fileName;
		}
		
		public int getAge(String str) {
		 char[] a = str.toCharArray();
	        int year,age;
	        if(a[7] == '1' || a[7] == '2' ){
	            year=1900+((a[0]-48)*10+(a[1]-48));
	        }else{
	            year = 2000+((a[0]-48)*10+(a[1]-48));
	        }
	        age = 2020-year+1;
	        return age; 
		}
		public void makeImgList(String lcode, String mnum, HttpServletRequest request) {
			//엑셀원본을 가지고 오고 -> 학생정보가지고와서->반복하면서 엑셀에 저장하기.
            try {				
           	    // 과정명 폴더만들기
	             String mname=resumeDAO.getName(lcode, mnum);
		        // System.out.println(mname);
		         
		         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
  		 		 File uploadPath = new File(folder,mname);
		         
		         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
		         //File uploadPath=new File(folder, mname);
		        		         
	    	      ClassPathResource resource = new ClassPathResource("사진LIST.xlsx");	    	  
	    	      XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
	    	      XSSFSheet sheet = workbook.getSheetAt(0);
		    	  System.out.println(sheet.getSheetName());
		    	  List<ResumeVO> list=resumeDAO.memberList(lcode, mnum);
		    	  
		    	  XSSFRow row=sheet.getRow(0);
		    	  XSSFCell cell=row.getCell(2);
		    	  cell.setCellValue(mname);
	    	
		          int col=1;
		          int r=1;
		    	  for(int i=0;i<list.size();i++) {
		    		  ResumeVO v=list.get(i);
		    		  
				     if(i==10){col=1;  r=10;}
				     if(i==20){ col=1;  r=19;}
				     
                     // 엑셀에 이미지 저장하기
			    	  if(!v.getImage().equals("NO")) {
			    	  int pictureIdx=0;
			    	  InputStream is=new FileInputStream(folder+"/"+v.getImage());
				      byte[] bytes=IOUtils.toByteArray(is);
				      
				      String type=v.getImage().substring(v.getImage().lastIndexOf(".")+1);
				      if(type.toLowerCase().equals("png")) {
				    	  pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
				      }else if(type.toLowerCase().equals("jpg")){
				    	  pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG); 
				      }else if(type.toLowerCase().equals("gif")){
					      pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_GIF); 
					  }
				      is.close();
				      
				      CreationHelper helper=workbook.getCreationHelper();
				      Drawing drawing=sheet.createDrawingPatriarch();
				      
				      ClientAnchor anchor=helper.createClientAnchor();
				      anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
				      anchor.setCol1(col);
				      anchor.setRow1(r);// (0+1)+9(1-1)=1
				      anchor.setCol2(col+1);
				      anchor.setRow2(r+5);// (0+1)+9(1-1)=1
				      //anchor.setCol1(2);
				      //anchor.setRow1(6);
				      anchor.setDx1(0);
				      anchor.setDx2(0); //정확한 값 기준은 모르겠지만 Dx2, Dy2 1000으로 하면 셀 크기에 딱맞게 줄여져서 나옴. 하단에 pict.resize()는 하면 안됨.
				      anchor.setDy1(0);
				      anchor.setDy2(0);
				    	
				      Picture pict=drawing.createPicture(anchor, pictureIdx);
				      //pict.resize();
				      
				      // 이름
			    	  row=sheet.getRow(r+5);  // 0+6*1=6
		    		  cell=row.getCell(col);
		    		  cell.setCellValue(v.getName()+"("+getAge(v.getSsn())+")");
		    		  
		    		  row=sheet.getRow(r+6);  // 0+7*1=7
		    		  cell=row.getCell(col);
		    		  cell.setCellValue(v.getCollege());
		    		  
		    		  row=sheet.getRow(r+7);  // 0+8*1=8
		    		  cell=row.getCell(col);
		    		  cell.setCellValue(v.getMajor());
		    		  
		    		  row=sheet.getRow(r+8);  // 0+9*1=9
		    		  cell=row.getCell(col);
		    		  cell.setCellValue(v.getTel().substring(4));
		    		  
				      col++;
			    	  }			    	  
	               }//_for_j
		    	// 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
	    	      Date date = new Date();
	    	      SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
	    	      String day = dayformat.format(date);
	    	      String fileName =mname + ".xlsx";
	    	      FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
	    	      workbook.write(fos);
	    	     // System.out.println("생성");
	    	      workbook.close();
	    	      fos.close();
	    	      
			} catch (Exception e) {
               e.printStackTrace();
			}
		}
		public void certInsert(ResumeVO vo) {
			resumeDAO.certInsert(vo);			
		}
		public void sangdeletenum(SangdamVO vo) {
			resumeDAO.sangdeletenum(vo);			
		}
		public void courseinsert(McourseVO vo) {
			resumeDAO.courseinsert(vo);
		}
		public void mdelete(McourseVO vo, HttpServletRequest request) {
			datadelete(vo.getLcode(), vo.getMnum()+"", request);
			resumeDAO.mdelete(vo);			
		}
		public void state1updateYES(int mnum) {
			resumeDAO.state1updateYES(mnum);
			
		}
		public void state1updateNO(int mnum) {
			resumeDAO.state1updateNO(mnum);
			
		}
		public void state2updateYES(int mnum) {
			resumeDAO.state2updateYES(mnum);
			
		}
		public void state2updateNO(int mnum) {
			resumeDAO.state2updateNO(mnum);
			
		}
		public void state1updateYESNO(int mnum) {
			resumeDAO.state1updateYESNO(mnum);
			
		}
		public void state2updateYESNO(int mnum) {
			resumeDAO.state2updateYESNO(mnum);
		}
		public void memupdate(McourseVO vo) {
			resumeDAO.memupdate(vo);
		}
		public void isisdata(int num, int mnum) {
			resumeDAO.isisdata(num, mnum);			
		}
		public void courseexcel(HttpServletRequest request) {
			//엑셀원본을 가지고 오고 -> 학생정보가지고와서->반복하면서 엑셀에 저장하기.
            try {				
           	     // 전체과정 가지고오기
	             List<McourseVO> list=resumeDAO.getAllCourse();
		         String mname="전체교육운영과정";
		         
		         String saveDir = request.getSession().getServletContext().getRealPath("/resources"); 
		 		//String fileName = "20190223-223005277_939.jpg"; 
		 		 File uploadPath = new File(saveDir);
		 		
		         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
		         //File uploadPath=new File(folder);
		        		         
	    	      ClassPathResource resource = new ClassPathResource("교육운영과정.xlsx");	  
	    	     
	    	      XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
	    	      XSSFSheet sheet = workbook.getSheetAt(0);
		    	  
		    	  XSSFRow row=sheet.getRow(0);
		    	  XSSFCell cell=row.getCell(2);
		    	  cell.setCellValue(mname);
	    	
		    	  
		    	  int colend=row.getLastCellNum();
		    	  int rowstart=2;
		    	  for(int i=0;i<list.size();i++) {
		    		 row=sheet.getRow(rowstart+i);  
		    		 McourseVO vo=list.get(i);
		    		  // 구분		
		    		 String part=null;
		    		  if(vo.getLcode().equals("1")) {
		    			  part="혁신";
		    		  }else if(vo.getLcode().equals("2")) {
		    			  part="4차";
		    		  }else if(vo.getLcode().equals("3")) {
		    			  part="청취";
		    		  }else if(vo.getLcode().equals("4")) {
		    			  part="K-디지털";
		    		  }
		    		  cell=row.getCell(0);
		    		  cell.setCellValue(part);
		    		  
		    		  // 과정명
		    		  cell=row.getCell(1);
		    		  cell.setCellValue(vo.getMname());
		    		  
		    		  //교육기간
		    		  cell=row.getCell(2);
		    		  cell.setCellValue(vo.getMday());
		    		  
		    	      //담임
		    		  cell=row.getCell(3);
		    		  cell.setCellValue(vo.getMmaster());
		    		  
		    		   //TA
		    		  cell=row.getCell(4);
		    		  cell.setCellValue(vo.getMta());
				 
		    		   //교육실
		    		  cell=row.getCell(5);
		    		  cell.setCellValue(vo.getMlocation());
		    		  
		    		  // 승인인원
		    		  cell=row.getCell(6);
		    		  cell.setCellValue(vo.getMold());
		       	    }

		    	  // 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
	    	      String fileName =mname + ".xlsx";
	    	      FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
	    	      workbook.write(fos);
	    	    //  System.out.println("생성");
	    	      workbook.close();
	    	      fos.close();
	    	      
			} catch (Exception e) {
               e.printStackTrace();
			}
			
		}
		public String excelCourseDownloadProcess() {
			 // 과정명 폴더만들기
			 String mname="전체교육운영과정";
             //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
	         //File uploadPath=new File(folder);
	         //http://172.30.1.59:8081/resume/resources/이름_941205-2667416.xlsx
	         //ResumeVO vo=resumeDAO.getStudentByNum(num);
	         String fileName=mname + ".xlsx";
	      //   System.out.println(fileName);
	         return fileName;
		}
		public void teamInsert(TeamVO vo) {
			resumeDAO.teamInsert(vo);			
		}
		public void teamDelete(int num) {
			resumeDAO.teamDelete(num);			
		}
		public void teamAllDelete(TeamVO vo) {
			resumeDAO.teamAllDelete(vo);	
			
		}
		public void teamExcel(TeamVO vo, HttpServletRequest request) {
			
			//엑셀원본을 가지고 오고 -> 학생정보가지고와서->반복하면서 엑셀에 저장하기.
            try {				
           	     // 팀정보가져오기 
	             List<TeamVO> list=resumeDAO.getAllTeam(vo);
	             
	             // 과정명 폴더만들기
	             String mname=resumeDAO.getName(vo.getLcode(), vo.getMnum()+"");
	             
		        // String mname="전체교육운영과정";
		         
		         String saveDir = request.getSession().getServletContext().getRealPath("/resources"); 
		 		//String fileName = "20190223-223005277_939.jpg"; 
		 		 File uploadPath = new File(saveDir, mname);
		 		
		         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
		         //File uploadPath=new File(folder);
		        		         
	    	      ClassPathResource resource = new ClassPathResource("TEAM1.xlsx");	  
	    	      XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
	    	      XSSFSheet sheet = workbook.getSheetAt(0);
		    	  
		    	  XSSFRow row=sheet.getRow(0);
		    	  XSSFCell cell=row.getCell(1);
		    	  cell.setCellValue(mname);
	  
		    	  int colend=row.getLastCellNum();
		    	  int rowstart=2;
		    	  int team1=2;
		    	  int team2=2;
		    	  int team3=2;
		    	  int team4=2;
		    	  int team5=2;
		    	  int team6=2;
		    	  for(int i=0;i<list.size();i++) {
		        		 TeamVO voo=list.get(i);
			    		  System.out.println(voo);
			    	      // 팀구분
			    		  if(voo.getTeam()==1) { // 1팀
			    			  row=sheet.getRow(1);
			    			  cell=row.getCell(0);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  
				    		  row=sheet.getRow(12);
			    			  cell=row.getCell(0);
				    		  cell.setCellFormula("COUNTA(A3:A12)");
				    		  	  
				    		  row=sheet.getRow(15);
			    			  cell=row.getCell(0);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  cell=row.getCell(6);
				    		  cell.setCellFormula("A3");
				    		  if(team1==2) {
				    			  row=sheet.getRow(team1++);  
				    			  cell=row.getCell(0);
					    		  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")"+"\n("+voo.getCollege()+")");
				    		  }else {
				    			  row=sheet.getRow(team1++);  
				    			  cell=row.getCell(0);
					    		  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")");
				    		  }
			    		  }else if(voo.getTeam()==2) { // 2팀
			    			  row=sheet.getRow(1);
			    			  cell=row.getCell(1);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  
				    		  row=sheet.getRow(12);
			    			  cell=row.getCell(1);
				    		  cell.setCellFormula("COUNTA(B3:B12)");
				    		  
				    		  row=sheet.getRow(16);
			    			  cell=row.getCell(0);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  cell=row.getCell(6);
				    		  cell.setCellFormula("B3");
				    		  if(team2==2) {
				    			  row=sheet.getRow(team2++);
				    			  cell=row.getCell(1);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")"+"\n("+voo.getCollege()+")");
				    		  }else{
				    			  row=sheet.getRow(team2++);
				    			  cell=row.getCell(1);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")");
				    		  }
			    		  }else if(voo.getTeam()==3) { // 3팀
			    			  row=sheet.getRow(1);
			    			  cell=row.getCell(2);
				    		  cell.setCellValue(voo.getTeam()+"팀");
			    			  
				    		  row=sheet.getRow(12);
			    			  cell=row.getCell(2);
				    		  cell.setCellFormula("COUNTA(C3:C12)");
				    		  
				    		  row=sheet.getRow(17);
			    			  cell=row.getCell(0);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  cell=row.getCell(6);
				    		  cell.setCellFormula("C3");
				    		  if(team3==2) {
				    			  row=sheet.getRow(team3++);
				    			  cell=row.getCell(2);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")"+"\n("+voo.getCollege()+")");
				    		  }else {
				    			  row=sheet.getRow(team3++);
				    			  cell=row.getCell(2);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")");
				    		  }
			    		  }else if(voo.getTeam()==4) { // 4팀
			    			  row=sheet.getRow(1);
			    			  cell=row.getCell(3);
				    		  cell.setCellValue(voo.getTeam()+"팀");
			    			  
				    		  row=sheet.getRow(12);
			    			  cell=row.getCell(3);
				    		  cell.setCellFormula("COUNTA(D3:D12)");
				    		  
				    		  row=sheet.getRow(18);
			    			  cell=row.getCell(0);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  cell=row.getCell(6);
				    		  cell.setCellFormula("D3");
				    		  if(team4==2) {
				    			  row=sheet.getRow(team4++);
				    			  cell=row.getCell(3);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")"+"\n("+voo.getCollege()+")");
				    		  }else {
				    			  row=sheet.getRow(team4++);
				    			  cell=row.getCell(3);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")");
				    		  }
			    		  }else if(voo.getTeam()==5) { // 4팀
			    			  row=sheet.getRow(1);
			    			  cell=row.getCell(4);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  
				    		  row=sheet.getRow(12);
			    			  cell=row.getCell(4);
				    		  cell.setCellFormula("COUNTA(E3:E12)");
				    		  
				    		  row=sheet.getRow(19);
			    			  cell=row.getCell(0);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  cell=row.getCell(6);
				    		  cell.setCellFormula("E3");
				    		  if(team5==2) {
				    			  row=sheet.getRow(team5++);
				    			  cell=row.getCell(4);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")"+"\n("+voo.getCollege()+")");
				    		  }else {
				    			  row=sheet.getRow(team5++);
				    			  cell=row.getCell(4);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")");
				    		  }
			    			  
			    		  }else if(voo.getTeam()==6) { // 4팀
			    			  row=sheet.getRow(1);
			    			  cell=row.getCell(5);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  
				    		  row=sheet.getRow(12);
			    			  cell=row.getCell(5);
				    		  cell.setCellFormula("COUNTA(F3:F12)");
				    		  
				    		  row=sheet.getRow(20);
			    			  cell=row.getCell(0);
				    		  cell.setCellValue(voo.getTeam()+"팀");
				    		  cell=row.getCell(6);
				    		  cell.setCellFormula("F3");
				    		  if(team6==2) {
				    			  row=sheet.getRow(team6++);
				    			  cell=row.getCell(5);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")"+"\n("+voo.getCollege()+")");
				    		  }else {
				    			  row=sheet.getRow(team6++);
				    			  cell=row.getCell(5);
				    			  cell.setCellValue(voo.getName()+"("+getAge(voo.getSsn())+")");
				    		  }
			    		  }	
		       	    }
		    	  
		    	 /* int colend=row.getLastCellNum();
		    	  int rowstart=2;
		    	  int team1=1;
		    	  int team2=1;
		    	  int team3=1;
		    	  int team4=1;
		    	  int team5=1;
		    	  int team6=1;
		    	  for(int i=0;i<list.size();i++) {
		        		 TeamVO voo=list.get(i);
			    		  System.out.println(voo);
			    	      // 팀구분
			    		  if(voo.getTeam()==1) { // 1팀
			    			  row=sheet.getRow(2);  
			    			  cell=row.getCell(team1++);
				    		  cell.setCellValue(voo.getName());
			    		  }else if(voo.getTeam()==2) { // 2팀
			    			  row=sheet.getRow(3);
			    			  cell=row.getCell(team2++);
			    			  cell.setCellValue(voo.getName());
			    		  }else if(voo.getTeam()==3) { // 3팀
			    			  row=sheet.getRow(4);
			    			  cell=row.getCell(team3++);
			    			  cell.setCellValue(voo.getName());
			    		  }else if(voo.getTeam()==4) { // 4팀
			    			  row=sheet.getRow(5);
			    			  cell=row.getCell(team4++);
			    			  cell.setCellValue(voo.getName());
			    		  }else if(voo.getTeam()==5) { // 4팀
			    			  row=sheet.getRow(6);
			    			  cell=row.getCell(team5++);
			    			  cell.setCellValue(voo.getName());
			    		  }else if(voo.getTeam()==6) { // 4팀
			    			  row=sheet.getRow(7);
			    			  cell=row.getCell(team6++);
			    			  cell.setCellValue(voo.getName());
			    		  }	
		       	    }
*/
		    	  // 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
	    	      String fileName =mname + "(TEAM).xlsx";
	    	      FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
	    	      workbook.write(fos);
	    	    //  System.out.println("생성");
	    	      workbook.close();
	    	      fos.close();
	    	      
			} catch (Exception e) {
               e.printStackTrace();
			}
		}
		
		public String teamDownloadProcess(String lcode, String mnum, HttpServletRequest request) {
			
		 	 // 과정명 폴더만들기
             String mname=resumeDAO.getName(lcode, mnum);
	       //  System.out.println(mname);
	         //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
	         //File uploadPath=new File(folder, mname);
	         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
		 	 File uploadPath = new File(folder,mname);
	         //http://172.30.1.59:8081/resume/resources/이름_941205-2667416.xlsx
	         //ResumeVO vo=resumeDAO.getStudentByNum(num);
	         String fileName=mname + "(TEAM).xlsx";
	      //   System.out.println(fileName);
	         return fileName;
		}
		public void mentorinsert(MentorVO vo) {
			resumeDAO.mentorinsert(vo);			
		}
		public int dbnamecheck(String mtname) {
			int cnt=resumeDAO.dbnamecheck(mtname);
			return cnt;
		}
		public ResumeCareerVO getStudentByNum(int num) {
			ResumeCareerVO vo=resumeDAO.getStudentByNum(num+"");
			return vo;
		}
		
		public ResumeCareerVO getStudentByNum1(int num) {
			ResumeCareerVO vo=resumeDAO.getStudentByNum1(num+"");
			return vo;
		}
		public void cpart(CareerVO vo) {
			resumeDAO.cpartBynum(vo);
		}
		public void cpartcancel(CareerVO vo) {
			resumeDAO.cpartcancel(vo);
			
		}	
		public void cpart1(CareerVO vo) {
			resumeDAO.cpartBynum1(vo);
		}
		public void cpartcancel1(CareerVO vo) {
			resumeDAO.cpartcancel1(vo);			
		}
		public void cpart2(CareerVO vo) {
			resumeDAO.cpartBynum2(vo);
		}
		public void cpartcancel2(CareerVO vo) {
			resumeDAO.cpartcancel2(vo);			
		}
		public void skillcontent(CareerVO vo) {
			resumeDAO.skillcontent(vo);	
			
		}
		public void careerExcel(String num, String lcode, String mnum, HttpServletRequest request) {
			   try {
		    	      // 과정명 폴더만들기
		              McourseVO mvo=resumeDAO.getName1(lcode, mnum);
			          //System.out.println(mname);
			          //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
			          //File uploadPath=new File(folder, mname);
			          String folder = request.getSession().getServletContext().getRealPath("/resources"); 
				 	  File uploadPath = new File(folder,mvo.getMname());
				 	 
			          ResumeCareerVO vo=resumeDAO.getStudentByNum1(num);
			         
		    	      ClassPathResource resource = new ClassPathResource("교육생카드.xlsx");
		    	      XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
		    	      XSSFSheet sheet = workbook.getSheetAt(0);
			    	  System.out.println(sheet.getSheetName());
			    	  //과정명
			    	  XSSFRow row=sheet.getRow(2);
			    	  XSSFCell cell=row.getCell(1);
			    	  cell.setCellValue(mvo.getMname());
			    	  
			    	  // No. 번호
			    	  row=sheet.getRow(0);
			    	  cell=row.getCell(7);
			    	  cell.setCellValue("No."+vo.getNum());
			    	  
			    	  // 담당
			    	  row=sheet.getRow(1);
			    	  cell=row.getCell(8);
			    	  cell.setCellValue(mvo.getMmaster()+"/"+mvo.getMta());
			    	  
			    	  //교육기간
			    	  row=sheet.getRow(3);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(mvo.getMday());
			    	  //성명
			    	  row=sheet.getRow(4);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getName());
			    	  
			    	  //생년월일
			    	  row=sheet.getRow(4);
			    	  cell=row.getCell(4);
			    	  cell.setCellValue(vo.getBirth()+"("+getAge(vo.getSsn())+"세)");
			    	  
 			    	  //주소
			    	  row=sheet.getRow(5);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getAddr());
			    	  
			    	  //연락처
			    	  row=sheet.getRow(6);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getTel());
			    	  
			    	  //E-mail
			    	  row=sheet.getRow(6);
			    	  cell=row.getCell(4);
			    	  cell.setCellValue(vo.getEmail());
			    	  
			    	  //최종학력
			    	  row=sheet.getRow(7);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getCollege()+" (대학교) "+vo.getMajor()+ "(학과)");
			    	  
			    	  // 자격증
			    	  row=sheet.getRow(8);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getCert());
			    	  
		    	     // 엑셀에 이미지 저장하기
			    	  if(!vo.getImage().equals("NO")) {
			    	  int pictureIdx=0;
			    	  InputStream is=new FileInputStream(folder+"/"+vo.getImage());
				      byte[] bytes=IOUtils.toByteArray(is);
				      String type=vo.getImage().substring(vo.getImage().lastIndexOf(".")+1);
				      if(type.toLowerCase().equals("png")) {
				    	  pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
				      }else if(type.toLowerCase().equals("jpg")){
				    	  pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG); 
				      }else if(type.toLowerCase().equals("gif")){
					      pictureIdx=workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_GIF); 
					  }
				      is.close();
				      
				      CreationHelper helper=workbook.getCreationHelper();
				      Drawing drawing=sheet.createDrawingPatriarch();
				      
				      ClientAnchor anchor=helper.createClientAnchor();
				      anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
				      
				      anchor.setCol1(7);
				      anchor.setRow1(2);
				      anchor.setCol2(9);
				      anchor.setRow2(6);
				      anchor.setDx1(0);
				      anchor.setDx2(0); //정확한 값 기준은 모르겠지만 Dx2, Dy2 1000으로 하면 셀 크기에 딱맞게 줄여져서 나옴. 하단에 pict.resize()는 하면 안됨.
				      anchor.setDy1(0);
				      anchor.setDy2(0);
				      
				      Picture pict=drawing.createPicture(anchor, pictureIdx);
				      //pict.resize();					      
			    	  }
				      XSSFCell imgcell=sheet.getRow(3).getCell(7);
				      int w=20*256;
				      //sheet.setColumnWidth(1, w);
				      short h=30*20;
				      imgcell.getRow().setHeight(h);
				     
				      //희망분야
				      String part=vo.getCpart();
				      if(!part.equals("NO")) {
				    	  if(part.indexOf("1")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(1);
					    	  cell.setCellValue("■BigData/AI");
				    	  }
				    	  if(part.indexOf("2")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(2);
					    	  cell.setCellValue("■SW개발자");
				    	  }
				    	  if(part.indexOf("3")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(3);
					    	  cell.setCellValue("■시스템운영관리"); 
				    	  }
				    	  if(part.indexOf("4")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(4);
					    	  cell.setCellValue("■DB/기획");
				    	  }
				    	  if(part.indexOf("5")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(5);
					    	  cell.setCellValue("■기타");
				    	  }
				      }
				    
				      //희망지역
				      String cloc=vo.getCloc();
				      if(!cloc.equals("NO")) {
				    	  if(cloc.indexOf("1")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(1);
					    	  cell.setCellValue("■지역무관");
				    	  }
				    	  if(cloc.indexOf("2")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(2);
					    	  cell.setCellValue("■광주");
				    	  }
				    	  if(cloc.indexOf("3")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(3);
					    	  cell.setCellValue("■전남"); 
				    	  }
				    	  if(cloc.indexOf("4")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(4);
					    	  cell.setCellValue("■수도권/서울");
				    	  }
				    	  if(cloc.indexOf("5")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(5);
					    	  cell.setCellValue("■기타");
				    	  }
				      }				      
				      
				      //기술수준
				      String skill=vo.getCskill();
				      if(!skill.equals("NO")) {
				    	  if(skill.indexOf("1")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(1);
					    	  cell.setCellValue("○");
				    	  }
				    	  if(skill.indexOf("2")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(2);
					    	  cell.setCellValue("○");
				    	  }
				    	  if(skill.indexOf("3")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(3);
					    	  cell.setCellValue("○"); 
				    	  }
				    	  if(skill.indexOf("4")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(4);
					    	  cell.setCellValue("○");
				    	  }
				    	  if(skill.indexOf("5")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(5);
					    	  cell.setCellValue("○");
				    	  }
				    	  if(skill.indexOf("6")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(6);
					    	  cell.setCellValue("○");
				    	  }
				    	  if(skill.indexOf("7")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(7);
					    	  cell.setCellValue("○");
				    	  }
				    	  if(skill.indexOf("8")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(8);
					    	  cell.setCellValue("○");
				    	  }
				      }				      
				      				      
				      // 연계내용
			    	  row=sheet.getRow(15);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getCcontent());
				      
			    	  
			    	// 기업명
			    	  row=sheet.getRow(22);
			    	  cell=row.getCell(2);
			    	  cell.setCellValue(vo.getCsave());
			    	  
			    	// 근무시작일
			    	  row=sheet.getRow(22);
			    	  cell=row.getCell(5);
			    	  cell.setCellValue(vo.getCstart());
			    	  
			    	// 4대보험가입일
			    	  row=sheet.getRow(22);
			    	  cell=row.getCell(8);
			    	  cell.setCellValue(vo.getCinsur());
				      /*
			    	  row=sheet.getRow(3);
		    		  cell=row.getCell(4);
		    		  cell.setCellValue(vo.getName());	    		  
		    		  cell=row.getCell(7);
		    		  cell.setCellValue(vo.getBirth()+"("+getAge(vo.getSsn())+"세)");
		    		  
		    		  row=sheet.getRow(5);
		    		  cell=row.getCell(4);
		    		  cell.setCellValue(vo.getCollege());
		    		  cell=row.getCell(7);
		    		  cell.setCellValue(vo.getTel());
		    		  
		    		  row=sheet.getRow(6);
		    		  cell=row.getCell(4);
		    		  cell.setCellValue(vo.getMajor());	    		  
		    		  cell=row.getCell(7);
		    		  cell.setCellValue(vo.getCert());			    		  
		    		  
		    		  List<SangdamVO> list=resumeDAO.sangdam(Integer.parseInt(num));
		    		  int i=9;
		              for(SangdamVO dto: list) {
		               row=sheet.getRow(i);
		               cell=row.getCell(0);
		               cell.setCellValue(dto.getSdate());   
		              
		               
		               cell=row.getCell(1);
		               cell.setCellValue(dto.getScontent());
		               cell=row.getCell(8);
		               cell.setCellValue(dto.getSname());
		               i++;          
		              } */
		    		  // 겹치는 파일 이름 중복을 피하기 위해 시간을 이용해서 파일 이름에 추
		    	      Date date = new Date();
		    	      SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
		    	      String day = dayformat.format(date);
		    	      String fileName =vo.getName()+ "_" + vo.getSsn() + "(취업관리카드).xlsx";
		    	      FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
		    	      workbook.write(fos);
		    	    //  System.out.println("훈련생취업관리카드생성");
		    	      workbook.close();
		    	      fos.close();
		    	      
			   } catch (Exception e) {
				   e.printStackTrace();		
		 }
		}
		public void csave(CareerVO vo) {
			resumeDAO.csave(vo);
		}
		public void cstart(CareerVO vo) {
			resumeDAO.cstart(vo);
		}
		public void cinsur(CareerVO vo) {
			resumeDAO.cinsur(vo);
		}
		public void addr(ResumeVO vo) {
			resumeDAO.addr(vo);
		}
		public CareerVO careerState2updateYES(int num) {
			CareerVO vo=resumeDAO.careerState2updateYES(num);
			return vo;
		}
}
