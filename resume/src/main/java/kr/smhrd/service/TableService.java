package kr.smhrd.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import kr.smhrd.model.McourseVO;
import kr.smhrd.model.ResumeCareerVO;
import kr.smhrd.model.ResumeDAO;
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

@Service
public class TableService {

	  @Autowired
	  private TableDAO tableDAO;
	  @Autowired
	  private ResumeDAO resumeDAO;

	public void table1(Table1VO vo) {
		tableDAO.table1(vo);		
	}


	public List<SuperVO> getStudentByNumTable1(int num) {
		List<SuperVO> list=tableDAO.getStudentByNumTable1(num+"");
		return list;
	}


	public void table1Del(int tnum) {
		tableDAO.table1Del(tnum);
	}


	public void table2(Table2VO vo) {
		tableDAO.table2(vo);
	}


	public void table2Del(int tnum2) {
		tableDAO.table2Del(tnum2);
		
	}
	public List<SuperVO2> getStudentByNumTable2(int num) {
		List<SuperVO2> list=tableDAO.getStudentByNumTable2(num+"");
		return list;
	}

	public List<SuperVO3> getStudentByNumTable3(int num) {
		List<SuperVO3> list=tableDAO.getStudentByNumTable3(num+"");
		return list;
	}

	public List<SuperVO4> getStudentByNumTable4(int num) {
		List<SuperVO4> list=tableDAO.getStudentByNumTable4(num+"");
		return list;
	}
	public List<SuperVO5> getStudentByNumTable5(int num) {
		List<SuperVO5> list=tableDAO.getStudentByNumTable5(num+"");
		return list;
	}
	public List<SuperVO6> getStudentByNumTable6(int num) {
		List<SuperVO6> list=tableDAO.getStudentByNumTable6(num+"");
		return list;
	}
	
	public List<SuperVO7> getStudentByNumTable7(int num) {
		List<SuperVO7> list=tableDAO.getStudentByNumTable7(num+"");
		System.out.println(list.get(0).toString());
		return list;
	}
	
	public void table3(Table3VO vo) {
		tableDAO.table3(vo);		
	}

	public void table4(Table4VO vo) {
		tableDAO.table4(vo);		
	}
	public void table5(Table5VO vo) {
		tableDAO.table5(vo);		
	}
	public void table6(Table6VO vo) {
		tableDAO.table6(vo);		
	}

	public void table7(Table7VO vo) {
		tableDAO.table7(vo);		
	}
	
	public void table3Del(int tnum3) {
		tableDAO.table3Del(tnum3);
		
	}
	public void table4Del(int tnum4) {
		tableDAO.table4Del(tnum4);
		
	} 
	public void table5Del(int tnum5) {
		tableDAO.table5Del(tnum5);
		
	} 
	public void table6Del(int tnum6) {
		tableDAO.table6Del(tnum6);
		
	}

	public void table7Del(int tnum7) {
		tableDAO.table7Del(tnum7);
		
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
	
	public void careerExcel2(String num, String lcode, String mnum, HttpServletRequest request) {
			   try {
		    	      // ????????? ???????????????
		              McourseVO mvo=resumeDAO.getName1(lcode, mnum);
			          //System.out.println(mname);
			          //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
			          //File uploadPath=new File(folder, mname);
			          String folder = request.getSession().getServletContext().getRealPath("/resources"); 
				 	  File uploadPath = new File(folder,mvo.getMname());
				 	  ResumeCareerVO rvo=resumeDAO.getStudentByNum(num);
			          //ResumeCareerVO vo=resumeDAO.getStudentByNum1(num);
			          // ??????
			          // ??????
			          // ??????
			          // ??????resumeDAOresumeDAO
			          // ????????????
			          // ????????????
			          List<SuperVO>  table1=tableDAO.getStudentByNumTable1(num);
			          List<SuperVO2> table2=tableDAO.getStudentByNumTable2(num);
			          List<SuperVO3> table3=tableDAO.getStudentByNumTable3(num);
			          List<SuperVO4> table4=tableDAO.getStudentByNumTable4(num);
			          List<SuperVO5> table5=tableDAO.getStudentByNumTable5(num);
			          List<SuperVO6> table6=tableDAO.getStudentByNumTable6(num);
			          List<SuperVO7> table7=tableDAO.getStudentByNumTable7(num);
			          
		    	      ClassPathResource resource = new ClassPathResource("?????????.xlsx");
		    	      XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
		    	      XSSFSheet sheet = workbook.getSheetAt(0);
			    	  System.out.println(sheet.getSheetName());
			    	  
			    	    // ????????? ????????? ????????????
			    	  if(!rvo.getImage().equals("NO")) {
			    	  int pictureIdx=0;
			    	  InputStream is=new FileInputStream(folder+"/"+rvo.getImage());
				      byte[] bytes=IOUtils.toByteArray(is);
				      String type=rvo.getImage().substring(rvo.getImage().lastIndexOf(".")+1);
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
				      anchor.setRow1(1);
				      anchor.setCol2(1);
				      anchor.setRow2(5);
				      anchor.setDx1(0);
				      anchor.setDx2(0); //????????? ??? ????????? ??????????????? Dx2, Dy2 1000?????? ?????? ??? ????????? ????????? ???????????? ??????. ????????? pict.resize()??? ?????? ??????.
				      anchor.setDy1(0);
				      anchor.setDy2(0);
				      
				      Picture pict=drawing.createPicture(anchor, pictureIdx);
				      //pict.resize();					      
			    	  }
				      //XSSFCell imgcell=sheet.getRow(3).getCell(7);
				      //int w=20*256;
				      //sheet.setColumnWidth(1, w);
				      //short h=30*20;
				      //imgcell.getRow().setHeight(h);
			    	  
			    	  
			    	  //??????
			    	  XSSFRow row=sheet.getRow(1);
			    	  XSSFCell cell=row.getCell(2);
			    	  cell.setCellValue(rvo.getName());
			    	  
			    	  // ????????????
			    	  row=sheet.getRow(1);
			    	  cell=row.getCell(4);
			    	  cell.setCellValue(rvo.getBirth()+"("+getAge(rvo.getSsn())+"???)");
			    	  
			    	  // ?????????
			    	  row=sheet.getRow(2);
			    	  cell=row.getCell(2);
			    	  cell.setCellValue(rvo.getTel());
			    	  
			    	  //?????????
			    	  row=sheet.getRow(2);
			    	  cell=row.getCell(4);
			    	  cell.setCellValue(rvo.getEmail());
			    	  
			    	  //??????
			    	  row=sheet.getRow(3);
			    	  cell=row.getCell(2);
			    	  cell.setCellValue(rvo.getAddr());
			    	  
			    	  //????????????
			    	   CellStyle cs=workbook.createCellStyle();
		    		   Font font = workbook.createFont(); font.setFontHeightInPoints((short) 9);
		    		   cs.setFont(font);
		    		   cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
		    		   cs.setVerticalAlignment(VerticalAlignment.CENTER);
		    		   cs.setWrapText(true);
			    	  
		    		   // ????????????
		    		   int endRow=sheet.getLastRowNum();
				    	 // System.out.println(endRow);
				    	  XSSFRow newRow=sheet.createRow(endRow+1);
				    	  newRow.setHeight((short)500);
				    	  
				    	   XSSFCellStyle  cs0=workbook.createCellStyle();
			    		   Font font0 = workbook.createFont(); font.setFontHeightInPoints((short) 10);
			    		   font0.setBold(true);
			    		   cs0.setFont(font0);
			        	   cs0.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
				    	   cs0.setVerticalAlignment(VerticalAlignment.CENTER);
				    	   cs0.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
				    	   cs0.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.index);
				    	   cs0.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				    	   
			    		  XSSFCell t0cend1=newRow.createCell(0);
			    		  t0cend1.setCellStyle(cs0);
				    	  XSSFCell t0cend2=newRow.createCell(4);
				    	  t0cend2.setCellStyle(cs0);
				    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t0cend1.getColumnIndex(), t0cend2.getColumnIndex()));
				    	 
				    	  t0cend1.setCellStyle(cs0);
				    	  t0cend1.setCellValue(" ???????????????");
				    	  
				    	  endRow=sheet.getLastRowNum();
				    	  newRow=sheet.createRow(endRow+1);
				    	  
				    	  cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
				    	  font= workbook.createFont(); font.setFontHeightInPoints((short) 9);
			    		  cs.setFont(font);
				    	   newRow.setHeight((short)500);
			    		   XSSFCell t0cstart1=newRow.createCell(0);		    		  
			    		   
			    		   t0cstart1.setCellStyle(cs);
			    		   t0cstart1.setCellValue("??????");
			    		   		    		   
			    		   XSSFCell t0cstart2=newRow.createCell(1);
			    		   XSSFCell t0cstart3=newRow.createCell(2);
			    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t0cstart2.getColumnIndex(), t0cstart3.getColumnIndex()));
			    		   t0cstart2.setCellStyle(cs);
			    		   t0cstart2.setCellValue("?????????(?????????/??????/??????)");
			    		  
			    		   XSSFCell t0cstart4=newRow.createCell(3);
			    		   t0cstart4.setCellStyle(cs);
			    		   t0cstart4.setCellValue("??????");
			    		   
			    		   XSSFCell t0cstart5=newRow.createCell(4);
			    		   t0cstart5.setCellStyle(cs);
			    		   t0cstart5.setCellValue("??????/??????/??????");
				    	  
			    		   endRow=sheet.getLastRowNum()+1;
			    	  //????????????(7)
			    	  if(table1.size()!=0) {
			    	   for(int i=0;i<table1.size();i++) {
			    		   SuperVO vo1=table1.get(i);
			    		   newRow=sheet.createRow(endRow+i);
			    		   newRow.setHeight((short)500);
			    		   XSSFCell t1c0=newRow.createCell(0);		    		  
			    		   
			    		   t1c0.setCellStyle(cs);
			    		   t1c0.setCellValue(vo1.getSdate());
			    		   
			    		   
			    		   XSSFCell t1c1=newRow.createCell(1);
			    		   XSSFCell t1c2=newRow.createCell(2);
			    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t1c1.getColumnIndex(), t1c2.getColumnIndex()));
			    		   t1c1.setCellStyle(cs);
			    		   t1c1.setCellValue(vo1.getStudent());
			    		  
			    		   XSSFCell t1c3=newRow.createCell(3);
			    		   t1c3.setCellStyle(cs);
			    		   t1c3.setCellValue(vo1.getSmajor());
			    		   
			    		   XSSFCell t1c4=newRow.createCell(4);
			    		   t1c4.setCellStyle(cs);
			    		   t1c4.setCellValue(vo1.getScong());
			    	   }
			    	  }else {
			    		   newRow=sheet.createRow(endRow);
			    		   newRow.setHeight((short)500);
			    		   XSSFCell t0c0=newRow.createCell(0);		    		  
			    		   
			    		   t0c0.setCellStyle(cs);
			    		   t0c0.setCellValue("????????????");				    		   
			    		   
			    		   XSSFCell t0c1=newRow.createCell(1);
			    		   XSSFCell t0c2=newRow.createCell(2);
			    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t0c1.getColumnIndex(), t0c2.getColumnIndex()));
			    		   t0c1.setCellStyle(cs);
			    		   t0c1.setCellValue("????????????");
			    		  
			    		   XSSFCell t0c3=newRow.createCell(3);
			    		   t0c3.setCellStyle(cs);
			    		   t0c3.setCellValue("????????????");
			    		   
			    		   XSSFCell t0c4=newRow.createCell(4);
			    		   t0c4.setCellStyle(cs);
			    		   t0c4.setCellValue("????????????"); 
			    	  }
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	 // System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+1);
			    	  newRow.setHeight((short)500);
			    	  
			    	   XSSFCellStyle  cs1=workbook.createCellStyle();
		    		   Font font1 = workbook.createFont(); font.setFontHeightInPoints((short) 10);
		    		   font1.setBold(true);
		    		   cs1.setFont(font1);
		        	   cs1.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
			    	   cs1.setVerticalAlignment(VerticalAlignment.CENTER);
			    	   cs1.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs1.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    	   
		    		  XSSFCell t1cend1=newRow.createCell(0);
		    		  t1cend1.setCellStyle(cs1);
			    	  XSSFCell t1cend2=newRow.createCell(4);
			    	  t1cend2.setCellStyle(cs1);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t1cend1.getColumnIndex(), t1cend2.getColumnIndex()));
			    	 
			    	  t1cend1.setCellStyle(cs1);
			    	  t1cend1.setCellValue(" ???????????????");
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	  newRow=sheet.createRow(endRow+1);
			    	  
			    	  cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  font= workbook.createFont(); font.setFontHeightInPoints((short) 9);
		    		  cs.setFont(font);
			    	   newRow.setHeight((short)500);
		    		   XSSFCell t2cstart1=newRow.createCell(0);		    		  
		    		   
		    		   t2cstart1.setCellStyle(cs);
		    		   t2cstart1.setCellValue("????????????");
		    		   		    		   
		    		   XSSFCell t2cstart2=newRow.createCell(1);
		    		   XSSFCell t2cstart3=newRow.createCell(2);
		    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t2cstart2.getColumnIndex(), t2cstart3.getColumnIndex()));
		    		   t2cstart2.setCellStyle(cs);
		    		   t2cstart2.setCellValue("?????????");
		    		  
		    		   XSSFCell t2cstart4=newRow.createCell(3);
		    		   t2cstart4.setCellStyle(cs);
		    		   t2cstart4.setCellValue("????????????");
		    		   
		    		   XSSFCell t2cstart5=newRow.createCell(4);
		    		   t2cstart5.setCellStyle(cs);
		    		   t2cstart5.setCellValue("????????????");
			    	  
		    		   endRow=sheet.getLastRowNum()+1;
		    		   cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  if(table2.size()!=0) {
				    	   for(int i=0;i<table2.size();i++) {
				    		   SuperVO2 vo1=table2.get(i);
				    		   newRow=sheet.createRow(endRow+i);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t1c0=newRow.createCell(0);		    		  
				    		   
				    		   t1c0.setCellStyle(cs);
				    		   t1c0.setCellValue(vo1.getSdate2());				    		   
				    		   
				    		   XSSFCell t1c1=newRow.createCell(1);
				    		   XSSFCell t1c2=newRow.createCell(2);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t1c1.getColumnIndex(), t1c2.getColumnIndex()));
				    		   t1c1.setCellStyle(cs);
				    		   t1c1.setCellValue(vo1.getCompany2());
				    		  
				    		   XSSFCell t1c3=newRow.createCell(3);
				    		   t1c3.setCellStyle(cs);
				    		   t1c3.setCellValue(vo1.getSjob2());
				    		   
				    		   XSSFCell t1c4=newRow.createCell(4);
				    		   t1c4.setCellStyle(cs);
				    		   t1c4.setCellValue(vo1.getSconti2());
				    	   }
				    	  }else {
				    		   newRow=sheet.createRow(endRow);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t1c0=newRow.createCell(0);		    		  
				    		   
				    		   t1c0.setCellStyle(cs);
				    		   t1c0.setCellValue("????????????");				    		   
				    		   
				    		   XSSFCell t1c1=newRow.createCell(1);
				    		   XSSFCell t1c2=newRow.createCell(2);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t1c1.getColumnIndex(), t1c2.getColumnIndex()));
				    		   t1c1.setCellStyle(cs);
				    		   t1c1.setCellValue("????????????");
				    		  
				    		   XSSFCell t1c3=newRow.createCell(3);
				    		   t1c3.setCellStyle(cs);
				    		   t1c3.setCellValue("????????????");
				    		   
				    		   XSSFCell t1c4=newRow.createCell(4);
				    		   t1c4.setCellStyle(cs);
				    		   t1c4.setCellValue("????????????"); 
				    	  }
			    	  
			    	 //????????????
			    	  endRow=sheet.getLastRowNum();
			    	 // System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+1);
			    	  newRow.setHeight((short)500);
			    	  
			    	   XSSFCellStyle  cs2=workbook.createCellStyle();
		    		   Font font2 = workbook.createFont(); font.setFontHeightInPoints((short) 10);
		    		   font2.setBold(true);
		    		   cs2.setFont(font2);
		        	   cs2.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
			    	   cs2.setVerticalAlignment(VerticalAlignment.CENTER);
			    	   cs2.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs2.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    	   
		    		  XSSFCell t3cend1=newRow.createCell(0);
		    		  t3cend1.setCellStyle(cs2);
			    	  XSSFCell t3cend2=newRow.createCell(4);
			    	  t3cend2.setCellStyle(cs2);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t3cend1.getColumnIndex(), t3cend2.getColumnIndex()));
			    	 
			    	  t3cend1.setCellStyle(cs2);
			    	  t3cend1.setCellValue(" ???????????????(??????/?????? ???)");
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	  newRow=sheet.createRow(endRow+1);
			    	  
			    	  cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  font= workbook.createFont(); font.setFontHeightInPoints((short) 9);
		    		  cs.setFont(font);
			    	   newRow.setHeight((short)500);
		    		   XSSFCell t3cstart1=newRow.createCell(0);		    		  
		    		   
		    		   t3cstart1.setCellStyle(cs);
		    		   t3cstart1.setCellValue("????????????");
		    		   		    		   
		    		   XSSFCell t3cstart2=newRow.createCell(1);
		    		   XSSFCell t3cstart3=newRow.createCell(3);
		    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t3cstart2.getColumnIndex(), t3cstart3.getColumnIndex()));
		    		   t3cstart2.setCellStyle(cs);
		    		   t3cstart2.setCellValue("??????(??????)?????????");
		    		  
		    		   XSSFCell t3cstart4=newRow.createCell(4);
		    		   t3cstart4.setCellStyle(cs);
		    		   t3cstart4.setCellValue("??????(??????)??????");
		    		   
		    		   endRow=sheet.getLastRowNum()+1;
		    		   cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  if(table3.size()!=0) {
				    	   for(int i=0;i<table3.size();i++) {
				    		   SuperVO3 vo3=table3.get(i);
				    		   newRow=sheet.createRow(endRow+i);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t3c0=newRow.createCell(0);		    		  
				    		   
				    		   t3c0.setCellStyle(cs);
				    		   t3c0.setCellValue(vo3.getSdate3());				    		   
				    		   
				    		   XSSFCell t3c1=newRow.createCell(1);
				    		   XSSFCell t3c2=newRow.createCell(3);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t3c1.getColumnIndex(), t3c2.getColumnIndex()));
				    		   t3c1.setCellStyle(cs);
				    		   t3c1.setCellValue(vo3.getScourse3());
				    					    		   
				    		   XSSFCell t3c4=newRow.createCell(4);
				    		   t3c4.setCellStyle(cs);
				    		   t3c4.setCellValue(vo3.getSname3());
				    	   }
				    	  }else {
				    		   newRow=sheet.createRow(endRow);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t3c0=newRow.createCell(0);		    		  
				    		   
				    		   t3c0.setCellStyle(cs);
				    		   t3c0.setCellValue("????????????");				    		   
				    		   
				    		   XSSFCell t3c1=newRow.createCell(1);
				    		   XSSFCell t3c2=newRow.createCell(3);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t3c1.getColumnIndex(), t3c2.getColumnIndex()));
				    		   t3c1.setCellStyle(cs);
				    		   t3c1.setCellValue("????????????");
								    		   
				    		   XSSFCell t3c4=newRow.createCell(4);
				    		   t3c4.setCellStyle(cs);
				    		   t3c4.setCellValue("????????????"); 
				    	  }
			    	  
				      //????????????
			    	  endRow=sheet.getLastRowNum();
			    	//  System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+1);
			    	  newRow.setHeight((short)500);
			    	  
			    	   XSSFCellStyle cs4=workbook.createCellStyle();
		    		   Font font4 = workbook.createFont(); font.setFontHeightInPoints((short) 10);
		    		   font4.setBold(true);
		    		   cs4.setFont(font4);
		        	   cs4.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
			    	   cs4.setVerticalAlignment(VerticalAlignment.CENTER);
			    	   cs4.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs4.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    	   
		    		  XSSFCell t4cend1=newRow.createCell(0);
		    		  t4cend1.setCellStyle(cs4);
			    	  XSSFCell t4cend2=newRow.createCell(4);
			    	  t4cend2.setCellStyle(cs4);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t4cend1.getColumnIndex(), t4cend2.getColumnIndex()));
			    	 
			    	  t4cend1.setCellStyle(cs4);
			    	  t4cend1.setCellValue(" ???????????????");
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	  newRow=sheet.createRow(endRow+1);
			    	  
			    	  cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  font= workbook.createFont(); font.setFontHeightInPoints((short) 9);
		    		  cs.setFont(font);
			    	   newRow.setHeight((short)500);
		    		   XSSFCell t4cstart1=newRow.createCell(0);		    		  
		    		   
		    		   t4cstart1.setCellStyle(cs);
		    		   t4cstart1.setCellValue("?????????");
		    		   		    		   
		    		   XSSFCell t4cstart2=newRow.createCell(1);
		    		   XSSFCell t4cstart3=newRow.createCell(3);
		    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t4cstart2.getColumnIndex(), t4cstart3.getColumnIndex()));
		    		   t4cstart2.setCellStyle(cs);
		    		   t4cstart2.setCellValue("????????????");
		    		  
		    		   XSSFCell t4cstart4=newRow.createCell(4);
		    		   t4cstart4.setCellStyle(cs);
		    		   t4cstart4.setCellValue("?????????");
		    		   
		    		   endRow=sheet.getLastRowNum()+1;
		    		   cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  if(table4.size()!=0) {
				    	   for(int i=0;i<table4.size();i++) {
				    		   SuperVO4 vo4=table4.get(i);
				    		   newRow=sheet.createRow(endRow+i);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t4c0=newRow.createCell(0);		    		  
				    		   
				    		   t4c0.setCellStyle(cs);
				    		   t4c0.setCellValue(vo4.getSdate4());				    		   
				    		   
				    		   XSSFCell t4c1=newRow.createCell(1);
				    		   XSSFCell t4c2=newRow.createCell(3);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t4c1.getColumnIndex(), t4c2.getColumnIndex()));
				    		   t4c1.setCellStyle(cs);
				    		   t4c1.setCellValue(vo4.getScert4());
				    					    		   
				    		   XSSFCell t4c4=newRow.createCell(4);
				    		   t4c4.setCellStyle(cs);
				    		   t4c4.setCellValue(vo4.getSsihang4());
				    	   }
				    	  }else {
				    		   newRow=sheet.createRow(endRow);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t4c0=newRow.createCell(0);		    		  
				    		   
				    		   t4c0.setCellStyle(cs);
				    		   t4c0.setCellValue("????????????");				    		   
				    		   
				    		   XSSFCell t4c1=newRow.createCell(1);
				    		   XSSFCell t4c2=newRow.createCell(3);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t4c1.getColumnIndex(), t4c2.getColumnIndex()));
				    		   t4c1.setCellStyle(cs);
				    		   t4c1.setCellValue("????????????");
								    		   
				    		   XSSFCell t4c4=newRow.createCell(4);
				    		   t4c4.setCellStyle(cs);
				    		   t4c4.setCellValue("????????????"); 
				    	  }
			    	  
			    	  // ????????????
			    	  endRow=sheet.getLastRowNum();
			    	//  System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+1);
			    	  newRow.setHeight((short)500);
			    	  
			    	   XSSFCellStyle cs5=workbook.createCellStyle();
		    		   Font font5 = workbook.createFont(); font.setFontHeightInPoints((short) 10);
		    		   font5.setBold(true);
		    		   cs5.setFont(font5);
		        	   cs5.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
			    	   cs5.setVerticalAlignment(VerticalAlignment.CENTER);
			    	   cs5.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs5.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    	   
		    		  XSSFCell t5cend1=newRow.createCell(0);
		    		  t4cend1.setCellStyle(cs5);
			    	  XSSFCell t5cend2=newRow.createCell(4);
			    	  t5cend2.setCellStyle(cs5);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t5cend1.getColumnIndex(), t5cend2.getColumnIndex()));
			    	 
			    	  t5cend1.setCellStyle(cs5);
			    	  t5cend1.setCellValue(" ??????????????? ??? ??????(????????????)");
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	  newRow=sheet.createRow(endRow+1);
			    	  
			    	  cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  font= workbook.createFont(); font.setFontHeightInPoints((short) 9);
		    		  cs.setFont(font);
			    	   newRow.setHeight((short)500);
		    		   XSSFCell t5cstart1=newRow.createCell(0);		    		  
		    		   
		    		   t5cstart1.setCellStyle(cs);
		    		   t5cstart1.setCellValue("???????????? ??? ??????");
		    		   	
		    		   XSSFCell t5cstart4=newRow.createCell(1);
		    		   t5cstart4.setCellStyle(cs);
		    		   t5cstart4.setCellValue("??????(???/???/???)");
		    		   
		    		   XSSFCell t5cstart2=newRow.createCell(2);
		    		   XSSFCell t5cstart3=newRow.createCell(4);
		    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t5cstart2.getColumnIndex(), t5cstart3.getColumnIndex()));
		    		   t5cstart2.setCellStyle(cs);
		    		   t5cstart2.setCellValue("????????????");
		    		  
		    		   endRow=sheet.getLastRowNum()+1;
		    		   cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
		    		   
		    		   XSSFCellStyle cs51=workbook.createCellStyle();
		    		   Font font51 = workbook.createFont(); 
		    		   font51.setFontHeightInPoints((short) 10);
		    		   cs51.setFont(font51);
		        	   cs51.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
			    	   cs51.setVerticalAlignment(VerticalAlignment.CENTER);
			    	   cs51.setWrapText(true);
			    	   
			    	  if(table5.size()!=0) {
				    	   for(int i=0;i<table5.size();i++) {
				    		  
				    		   SuperVO5 vo5=table5.get(i);
				    		   newRow=sheet.createRow(endRow+i);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t5c0=newRow.createCell(0);		    		  
				    		   
				    		   t5c0.setCellStyle(cs);
				    		   t5c0.setCellValue(vo5.getSkill5());				    		   
				    		   
				    		   XSSFCell t5c4=newRow.createCell(1);
				    		   t5c4.setCellStyle(cs);
				    		   t5c4.setCellValue(vo5.getSlevel5());
				    		   
				    		   XSSFCell t5c1=newRow.createCell(2);
				    		   XSSFCell t5c2=newRow.createCell(4);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t5c1.getColumnIndex(), t5c2.getColumnIndex()));
				    		   
				    		 
				    		   t5c1.setCellStyle(cs51);
				    		   t5c1.setCellValue(vo5.getScontent5());
				    	   }
				    	  }else {
				    		   newRow=sheet.createRow(endRow);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t5c0=newRow.createCell(0);		    		  
				    		   
				    		   t5c0.setCellStyle(cs);
				    		   t5c0.setCellValue("????????????");				    		   
				    		   
				    		   XSSFCell t5c4=newRow.createCell(1);
				    		   t5c4.setCellStyle(cs);
				    		   t5c4.setCellValue("????????????"); 
				    		   
				    		   XSSFCell t5c1=newRow.createCell(2);
				    		   XSSFCell t5c2=newRow.createCell(4);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t5c1.getColumnIndex(), t5c2.getColumnIndex()));
				    		   t5c1.setCellStyle(cs);
				    		   t5c1.setCellValue("????????????");								    		   
				    		   
				    	  }
			    	  
			    	  // ??????????????????
			    	  endRow=sheet.getLastRowNum();
			    	//  System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+1);
			    	  newRow.setHeight((short)500);
			    	  
			    	   XSSFCellStyle cs6=workbook.createCellStyle();
		    		   Font font6 = workbook.createFont(); font.setFontHeightInPoints((short) 10);
		    		   font6.setBold(true);
		    		   cs6.setFont(font6);
		        	   cs6.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
			    	   cs6.setVerticalAlignment(VerticalAlignment.CENTER);
			    	   cs6.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs6.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.index);
			    	   cs6.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    	  
			    	   
		    		  XSSFCell t6cend1=newRow.createCell(0);
		    		  t6cend1.setCellStyle(cs6);
			    	  XSSFCell t6cend2=newRow.createCell(4);
			    	  t6cend2.setCellStyle(cs6);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t6cend1.getColumnIndex(), t6cend2.getColumnIndex()));
			    	 
			    	  t6cend1.setCellStyle(cs6);
			    	  t6cend1.setCellValue(" ??????????????? ??????");
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	  newRow=sheet.createRow(endRow+1);
			    	  
			    	  cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	  font= workbook.createFont(); font.setFontHeightInPoints((short) 9);
		    		  cs.setFont(font);
			    	   newRow.setHeight((short)500);
		    		   XSSFCell t6cstart1=newRow.createCell(0);		    		  
		    		   
		    		   t6cstart1.setCellStyle(cs);
		    		   t6cstart1.setCellValue("??????????????????");
		    		   	
		    		   XSSFCell t6cstart2=newRow.createCell(1);
		    		   XSSFCell t6cstart3=newRow.createCell(2);
		    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t6cstart2.getColumnIndex(), t6cstart3.getColumnIndex()));
		    		   t6cstart2.setCellStyle(cs);
		    		   t6cstart2.setCellValue("??????????????????");
		    		   
		    		   XSSFCell t6cstart21=newRow.createCell(3);
		    		   XSSFCell t6cstart31=newRow.createCell(4);
		    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t6cstart21.getColumnIndex(), t6cstart31.getColumnIndex()));
		    		   t6cstart21.setCellStyle(cs);
		    		   t6cstart21.setCellValue("??????????????????");
		    		   endRow=sheet.getLastRowNum()+1;
		    		   cs.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
		    		   
		    		   XSSFCellStyle cs61=workbook.createCellStyle();
		    		   Font font61 = workbook.createFont(); 
		    		   font61.setFontHeightInPoints((short) 10);
		    		   cs61.setFont(font61);
		        	   cs61.setAlignment(HorizontalAlignment.LEFT);//????????? ??????
			    	   cs61.setVerticalAlignment(VerticalAlignment.CENTER);
			    	   cs61.setWrapText(true);
			    	   
			    	   //SSFRow rowhead=   sheet.createRow((short)0);
			    	   //HSSFCellStyle style = workbook.createCellStyle();
			    	   //style.setWrapText(true);
			    	   //row.setRowStyle(style);
			    	   
			    	  if(table6.size()!=0) {
				    	   for(int i=0;i<table6.size();i++) {
				    		  
				    		   SuperVO6 vo6=table6.get(i);
				    		   if(vo6.getScontent6()==null) break;
				    		   int lineCnt = 0;
				    		    int fromIndex = -1;
				    		    while ((fromIndex = vo6.getScontent6().indexOf("\n", fromIndex + 1)) >= 0) {
				    		      lineCnt++;
				    		    }
				    		   if(lineCnt>1) { 
				    		     newRow=sheet.createRow(endRow+i);
				    		     //newRow.setRowStyle(cs61);
				    		     newRow.setHeight((short)(380*lineCnt));
				    		   }else {
				    			 newRow=sheet.createRow(endRow+i);
					    		 newRow.setHeight((short)500);
				    			 //newRow.setHeight();
					    		 //newRow.setRowStyle(cs61);
				    		   }
				    		   newRow.setRowStyle(cs);
				    		   XSSFCell t6c0=newRow.createCell(0);		    		  
				    		   
				    		   t6c0.setCellStyle(cs);
				    		   t6c0.setCellValue(vo6.getSdate6());				    		   
				    		   
				    		   XSSFCell t6c1=newRow.createCell(1);
				    		   XSSFCell t6c2=newRow.createCell(2);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t6c1.getColumnIndex(), t6c2.getColumnIndex()));

				    		   t6c1.setCellStyle(cs);
				    		   t6c1.setCellValue(vo6.getStitle6());

				    		   XSSFCell t6c11=newRow.createCell(3);
				    		   XSSFCell t6c21=newRow.createCell(4);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t6c11.getColumnIndex(), t6c21.getColumnIndex()));
				    		  
				    		   t6c11.setCellStyle(cs61);
				    		   t6c11.setCellValue(vo6.getScontent6());
				    		   
				    		   
				    		 //  System.out.println(lineCnt);
				    	   }
				    	  }else {
				    		   newRow=sheet.createRow(endRow);
				    		   newRow.setHeight((short)500);
				    		   XSSFCell t6c0=newRow.createCell(0);		    		  
				    		   
				    		   t6c0.setCellStyle(cs);
				    		   t6c0.setCellValue("????????????");				    		   
				    		   
				    		   XSSFCell t6c1=newRow.createCell(1);
				    		   XSSFCell t6c2=newRow.createCell(2);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t6c1.getColumnIndex(), t6c2.getColumnIndex()));
				    		   t6c1.setCellStyle(cs);
				    		   t6c1.setCellValue("????????????");	
				    		   
				    		   XSSFCell t6c11=newRow.createCell(3);
				    		   XSSFCell t6c21=newRow.createCell(4);
				    		   sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t6c11.getColumnIndex(), t6c21.getColumnIndex()));
				    		   t6c11.setCellStyle(cs);
				    		   t6c11.setCellValue("????????????");								    		   
				    		 				    		   
				    	  }
			    	  
			    	  // ??????????????? ?????? ??????
			    	  XSSFSheet sheet1 = workbook.getSheetAt(1);
			    	  if(table7.size()!=0) {
			    		  				    	  
				    	  //??????
				    	  row=sheet1.getRow(1);
				    	  cell=row.getCell(1);
				    	  cell.setCellValue(rvo.getName());
			    		  
				    	  //???????????????
				    	  XSSFRow row3=sheet1.getRow(3);
				    	  XSSFCell cell0=row3.getCell(0);
				    	  SuperVO7 svo=table7.get(0);
				    	  cell0.setCellValue(svo.getScontent7());				    	  
			    	  }else {
			    		  //??????
				    	  row=sheet.getRow(1);
				    	  cell=row.getCell(1);
				    	  cell.setCellValue(rvo.getName());
			    		  
				    	  //???????????????
				    	  XSSFRow row3=sheet1.getRow(3);
				    	  XSSFCell cell0=row.getCell(0);
				    	  SuperVO7 svo=table7.get(0);
				    	  cell.setCellValue("????????????");	
			    	  }
			    	  
			    	  // ?????????
			    	  endRow=sheet.getLastRowNum();
			    	//  System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+3);
			    	  newRow.setHeight((short)500);
			    	  
			    	   XSSFCellStyle cs7=workbook.createCellStyle();
		    		   Font font7 = workbook.createFont(); font.setFontHeightInPoints((short) 11);
		    		   cs7.setFont(font7);
		        	   cs7.setAlignment(HorizontalAlignment.CENTER);//????????? ??????
			    	   cs7.setVerticalAlignment(VerticalAlignment.CENTER);
		    		   
		    		  XSSFCell t7cend1=newRow.createCell(0);
		    		  t7cend1.setCellStyle(cs6);
			    	  XSSFCell t7cend2=newRow.createCell(4);
			    	  t7cend2.setCellStyle(cs6);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t7cend1.getColumnIndex(), t7cend2.getColumnIndex()));
			    	 
			    	  t7cend1.setCellStyle(cs7);
			    	  t7cend1.setCellValue("?????? ?????? ??????????????? ????????? ????????? ????????? ???????????????.");
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	  System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+1);
			    	  newRow.setHeight((short)500);
			    	  
			    	  XSSFCell t8cend1=newRow.createCell(0);
		    		  t8cend1.setCellStyle(cs7);
			    	  XSSFCell t8cend2=newRow.createCell(4);
			    	  t8cend2.setCellStyle(cs7);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t8cend1.getColumnIndex(), t8cend2.getColumnIndex()));
			    	 
			    	  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			    	  Date date=new Date();
			    	  String currDate=df.format(date);
			    	  t8cend1.setCellStyle(cs7);
			    	  t8cend1.setCellValue(currDate);
			    	  
			    	  endRow=sheet.getLastRowNum();
			    	  System.out.println(endRow);
			    	  newRow=sheet.createRow(endRow+2);
			    	  newRow.setHeight((short)500);
			    	  
			    	  XSSFCell t9cend1=newRow.createCell(0);
		    		  t9cend1.setCellStyle(cs7);
			    	  XSSFCell t9cend2=newRow.createCell(4);
			    	  t9cend2.setCellStyle(cs7);
			    	  sheet.addMergedRegion(new CellRangeAddress(newRow.getRowNum(), newRow.getRowNum(), t9cend1.getColumnIndex(), t9cend2.getColumnIndex()));
			    	 
			    	  t9cend1.setCellStyle(cs7);
			    	  t9cend1.setCellValue("?????????      " +rvo.getName()+"    (???)" );
			    	  
			    	 /* row=sheet.getRow(4);
			    	  cell=row.getCell(4);
			    	  cell.setCellValue(vo.getBirth()+"("+getAge(vo.getSsn())+"???)");
			    	  
 			    	  //??????
			    	  row=sheet.getRow(5);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getAddr());
			    	  
			    	  //?????????
			    	  row=sheet.getRow(6);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getTel());
			    	  
			    	  //E-mail
			    	  row=sheet.getRow(6);
			    	  cell=row.getCell(4);
			    	  cell.setCellValue(vo.getEmail());
			    	  
			    	  //????????????
			    	  row=sheet.getRow(7);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getCollege()+" (?????????) "+vo.getMajor()+ "(??????)");
			    	  
			    	  // ?????????
			    	  row=sheet.getRow(8);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getCert());
			    	  
		    	     // ????????? ????????? ????????????
			    	  if(!vo.getImage().equals("NO")) {
			    	  int pictureIdx=0;
			    	  InputStream is=new FileInputStream(folder+"\\"+vo.getImage());
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
				      anchor.setDx2(0); //????????? ??? ????????? ??????????????? Dx2, Dy2 1000?????? ?????? ??? ????????? ????????? ???????????? ??????. ????????? pict.resize()??? ?????? ??????.
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
				     
				      //????????????
				      String part=vo.getCpart();
				      if(!part.equals("NO")) {
				    	  if(part.indexOf("1")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(1);
					    	  cell.setCellValue("???BigData/AI");
				    	  }
				    	  if(part.indexOf("2")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(2);
					    	  cell.setCellValue("???SW?????????");
				    	  }
				    	  if(part.indexOf("3")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(3);
					    	  cell.setCellValue("????????????????????????"); 
				    	  }
				    	  if(part.indexOf("4")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(4);
					    	  cell.setCellValue("???DB/??????");
				    	  }
				    	  if(part.indexOf("5")!=-1) {
				    		  row=sheet.getRow(9);
					    	  cell=row.getCell(5);
					    	  cell.setCellValue("?????????");
				    	  }
				      }
				    
				      //????????????
				      String cloc=vo.getCloc();
				      if(!cloc.equals("NO")) {
				    	  if(cloc.indexOf("1")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(1);
					    	  cell.setCellValue("???????????????");
				    	  }
				    	  if(cloc.indexOf("2")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(2);
					    	  cell.setCellValue("?????????");
				    	  }
				    	  if(cloc.indexOf("3")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(3);
					    	  cell.setCellValue("?????????"); 
				    	  }
				    	  if(cloc.indexOf("4")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(4);
					    	  cell.setCellValue("????????????/??????");
				    	  }
				    	  if(cloc.indexOf("5")!=-1) {
				    		  row=sheet.getRow(10);
					    	  cell=row.getCell(5);
					    	  cell.setCellValue("?????????");
				    	  }
				      }				      
				      
				      //????????????
				      String skill=vo.getCskill();
				      if(!skill.equals("NO")) {
				    	  if(skill.indexOf("1")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(1);
					    	  cell.setCellValue("???");
				    	  }
				    	  if(skill.indexOf("2")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(2);
					    	  cell.setCellValue("???");
				    	  }
				    	  if(skill.indexOf("3")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(3);
					    	  cell.setCellValue("???"); 
				    	  }
				    	  if(skill.indexOf("4")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(4);
					    	  cell.setCellValue("???");
				    	  }
				    	  if(skill.indexOf("5")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(5);
					    	  cell.setCellValue("???");
				    	  }
				    	  if(skill.indexOf("6")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(6);
					    	  cell.setCellValue("???");
				    	  }
				    	  if(skill.indexOf("7")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(7);
					    	  cell.setCellValue("???");
				    	  }
				    	  if(skill.indexOf("8")!=-1) {
				    		  row=sheet.getRow(13);
					    	  cell=row.getCell(8);
					    	  cell.setCellValue("???");
				    	  }
				      }				      
				      				      
				      // ????????????
			    	  row=sheet.getRow(15);
			    	  cell=row.getCell(1);
			    	  cell.setCellValue(vo.getCcontent());
				      
			    	  
			    	// ?????????
			    	  row=sheet.getRow(22);
			    	  cell=row.getCell(2);
			    	  cell.setCellValue(vo.getCsave());
			    	  
			    	// ???????????????
			    	  row=sheet.getRow(22);
			    	  cell=row.getCell(5);
			    	  cell.setCellValue(vo.getCstart());
			    	  
			    	// 4??????????????????
			    	  row=sheet.getRow(22);
			    	  cell=row.getCell(8);
			    	  cell.setCellValue(vo.getCinsur());
				      
			    	  row=sheet.getRow(3);
		    		  cell=row.getCell(4);
		    		  cell.setCellValue(vo.getName());	    		  
		    		  cell=row.getCell(7);
		    		  cell.setCellValue(vo.getBirth()+"("+getAge(vo.getSsn())+"???)");
		    		  
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
		    		  // ????????? ?????? ?????? ????????? ????????? ?????? ????????? ???????????? ?????? ????????? ???
		    	      date = new Date();
		    	      SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
		    	      String day = dayformat.format(date);
		    	      String fileName =rvo.getName()+ "_" + rvo.getSsn() + "(?????????).xlsx";
		    	      FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
		    	      workbook.write(fos);
		    	    //  System.out.println("???????????????");
		    	      workbook.close();
		    	      fos.close();
		    	      
			   } catch (Exception e) {
				   e.printStackTrace();		
		 }
		}
	public String excelFileDownloadProcess1(String num, String lcode, String mnum, HttpServletRequest request) {
		
	 	 // ????????? ???????????????
         String mname=resumeDAO.getName(lcode, mnum);
       //  System.out.println(mname);
        //String folder="C:\\eGovFrame-3.9.0\\workspace.edu\\resume\\src\\main\\webapp\\resources";
        //File uploadPath=new File(folder, mname);
         String folder = request.getSession().getServletContext().getRealPath("/resources"); 
	 	 File uploadPath = new File(folder,mname);
        //http://172.30.1.59:8081/resume/resources/??????_941205-2667416.xlsx
        ResumeCareerVO vo=resumeDAO.getStudentByNum(num);
        String fileName=vo.getName()+ "_" + vo.getSsn() + "(?????????).xlsx";
       // System.out.println(fileName);
        return fileName;
	}
}
