<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<c:set var="path" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SAF(스마트아카이브공장)</title>
 <!--    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
<style>



 body, table {
	  font-size: 12px;
	  font-family: 나눔고딕, NanumGothic, NanumBarunGothic,'Nanum Gothic',arial,verdana,sans-serif;
   }

  .bg{
    color: white;
  }
        
  select {
  width: 250px;
  padding: .4em .2em;
  font-family: inherit;
  background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border: 1px solid #999;
  border-radius: 0px;
}

select::-ms-expand {
  /* for IE 11 */
  display: none;
}
</style>
<script type='text/javascript'>
$(document).ready(function(){
	//dataLoad('${lcode}', '${mnum}');	
	lcourse();
    $('#lcourse').change(function(){
       //alert(this.value);
       $.ajax({
        url: "mcourse",
        data: {"lcode" : this.value},
        type: "POST",
        success: mcourse
        })
    });    	
    
    $('#mcourse').change(function(){
    	 var lcode=$("#lcourse option:selected").val();
    	 $.ajax({
    	        url: "studentLoad",
    	        data : {"lcode": lcode, "mnum" : this.value},
    	        type: "GET",
    	        success: dataList
    	 })
     });
     
});
function lcourse(){
	  $.ajax({
        url: "lcourse",
        processData: false,
        contentType: false,
        type: "GET",
        dataType : "json",
        success: course
    })
}
function course(data){
	//data=JSON.stringify(data);
var html="";
	if(data["list"]!="not"){
	  $.each(data["list"], function(index, obj){
		html+="<option value='"+obj.lcode+"'>"+obj.lname+"</option>";
	  });
	   $("#lcourse").html(html);
	}else{
	   $("#lcourse").html("<option>데이터가 없습니다.</option>");
	}
	if('${lcode}'!=""){
	    $('#lcourse').val('${lcode}').trigger('change');
	}
}

function mcourse(data){
    //data=JSON.stringify(data);
    
    var html="<option>과정명을 선택하세요</option>";
	if(data["list"]!="not"){
	  $.each(data["list"], function(index, obj){
		html+="<option value='"+obj.mnum+"'>"+obj.mname+"</option>";
  	  });
	   $("#mcourse").html(html);
	}else{
	   $("#mcourse").html("<option>데이터가 없습니다.</option>");
	}
	if('${mnum}'!=""){
	    $('#mcourse').val('${mnum}').trigger('change');
	}
}

function dataList(data){
	console.log(data);
	var html="";
	html+="<table class='table table-hover' id='aaa' width='300px;'>";	
	html+="<tr style='vertical-align: top;'>";
	html+="<tr align='center' class='success'>";
    html+="<td width='100px'>사진</td>";
    html+="<td width='100px'>번호</td>";
    html+="<td width='100px'>이름</td>";
    
    html+="</tr>";
	$.each(data["list"], function(index, obj){
		 html+="<tr>";           
		 html+="<td><a href='javascript:career("+obj.lcode+","+obj.mnum+","+obj.num+")'><img src='${path}/resources/"+obj.image+"' width='60px' height='60px'></a></td>";
		 html+="<td>"+obj.num+"</td>";
		 if(obj.isis==1){
			 html+="<td id='classid-"+obj.num+"' class='name'>"+obj.name+"</td>";
		 }else{
			 html+="<td id='classid-"+obj.num+"' style='text-decoration: line-through;color:red'>"+obj.name+"(중탈)</td>"; 
		 }	
		
		 html+="</tr>";		
	});
       
	 
	 html+="</table>";
	 //$("#cartlist").empty();
	 $("#cartlist").html(html); 	
    
	// $("#selectBox").val("2");
	 
	// $('#lcourse').val('${lcode}');	
	// $('#mcourse').val('${mnum}').trigger('change');
	 $('#career').css("display","none");
}



function calcAge(birth) {
	alert(birth);
    var date = new Date();
    var year = date.getFullYear();
    var month = (date.getMonth() + 1);
    var day = date.getDate();       
    if (month < 10) month = '0' + month;
    if (day < 10) day = '0' + day;
    var monthDay = month + day;
    birth = birth.replace('-', '').replace('-', '');
    var birthdayy = birth.substr(0, 4);
    var birthdaymd = birth.substr(4, 4); 
    var age = monthDay < birthdaymd ? year - birthdayy - 1 : year - birthdayy;
    return age;
} 

function careerHtml(data){
	var mname=$("#mcourse option:selected").text();
	//console.log(data.mname);
	//$.each(data, function(index, obj){  
		//alert(data);
		var html="";
		var html2="";
		html+="<table  class='table table-bordered' style='undefined;table-layout: fixed; width: 850px'>";
		html+="<colgroup>";
		html+="<col style='width: 58px'>";
        html+="<col style='width: 63px'>";
       	html+="<col style='width: 136px'>";
   		html+="<col style='width: 106px'>";
		html+="<col style='width: 67px'>";
		html+="<col style='width: 113px'>";
    	html+="</colgroup>";
   		html+="<tbody>";
   		html+="<tr>";
   		html+="<td colspan='2' rowspan='4' valign='middle' align='center'><img border='1' src='${path}/resources/"+data["list"][0].image+"'></td>";
   		html+="<td>이름</td>";
   		html+="<td>"+data["list"][0].name+"</td>";
   		html+="<td>생년월일</td>";
   		html+="<td>"+data["list"][0].birth+"</td>";
 		html+="</tr>";
 		html+="<tr>";
 		html+="<td>핸드폰</td>";
    	html+="<td>"+data["list"][0].tel+"</td>";
   		html+="<td>E-Mail</td>";
   		html+="<td>"+data["list"][0].email+"</td>";
   		html+="</tr>";
   		html+="<tr>";
   		html+="<td class='tg-c3ow'>주소</td>";
   	    html+="<td class='tg-0pky' colspan='3'>"+data["list"][0].addr+"</td>";
   		html+="</tr>";
   		html+="<tr>";
   		html+="<td class='tg-c3ow'>지원분야</td>";
		html+="<td class='tg-0pky' colspan='3'></td>";
	    html+="</tr>";
		html+="<tr>";
		html+="<td colspan='6'><span class='label label-danger'>학력사항</span></td>";
		html+="</tr>";
    	html+="<tr class='info'>";
        html+="<td colspan='2'>년도</td>";
   		html+="<td>학교명(대학원/대학/고등)</td>";
   		html+="<td colspan='2'>전공</td>";
   		html+="<td>(졸업/수료/중퇴)</td>";
   		html+="</tr>";
   		if(data["list"][0].student!=null){
	   		$.each(data["list"], function(index, obj){
	   			html+="<tr id='aaa'>";
	   	   		html+="<td class='tg-0pky' colspan='2'>"+obj.sdate+"</td>";
	   	   		html+="<td class='tg-0pky'>"+obj.student+"</td>";
	   	   		html+="<td class='tg-0pky' colspan='2'>"+obj.smajor+"</td>";
	   	   		html+="<td class='tg-0pky'>"+obj.scong+"&nbsp;<input type='button' value='삭제' class='btn btn-warning btn-xs' onclick='table1Del("+obj.tnum+","+obj.num+")'/></td>";
	   	   		html+="</tr>";   			
	   		});
   		}
   		html+="<div class='form-group row'>";
   		html+="<form id='table1"+data["list"][0].num+"' method='post'>";
   		html+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
   		html+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
   		html+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
   		html+="<tr>";   		
   		html+="<td class='tg-0pky' colspan='2'><input type='date' name='t1Sdate' id='t1Sdate'>~<input type='date' name='t1Edate' id='t1Edate'></td>";
   		html+="<td><div class='col-xs-13'><input type='text' name='student' id='student' class='form-control input-sm'/></td></div>";
   		html+="<td colspan='2'><div class='col-xs-12'><input type='text' name='smajor' id='smajor' class='form-control input-sm'/></div></td>";
   		html+="<td><div class='col-xs-8'><input type='text' name='scong' id='scong' class='form-control input-sm'/></div>&nbsp;<input type='button' class='btn btn-primary btn-xs' value='등록' onclick='table1("+data["list"][0].num+")'/></td>";
   		html+="</tr>";
   		html+="</form>";
   		html+="</div>";
   	
   		html+="</tbody>";
		html+="</table>";
		$('#career').html(html);
		
	    //});
 
	    /* 	 var dis=$('#career').css("display");
		 if(dis=="none"){
		 	$('#career').css("display","block");
		 }else{
		 	$('#career').css("display","none");
		 }	 */
	     $('#career').css("display","block");
		 	 
		 $.ajax({
	    	 url : "getStudentByNumTable2",
	    	 type : "GET",
	    	 data: {"num" : data["list"][0].num, "lcode" : data["list"][0].lcode, "mnum" : data["list"][0].mnum },
	    	 dataType : "json",
	    	 success : career2Html,	    	 
	    	 error : function() { alert("error"); }
	     });		 
		
}
function career2Html(data){	
	var html2="";
	html2+="<table  class='table table-bordered' style='undefined;table-layout: fixed; width: 850px'>";
	html2+="<colgroup>";
	html2+="<col style='width: 58px'>";
    html2+="<col style='width: 63px'>";
   	html2+="<col style='width: 136px'>";
		html2+="<col style='width: 106px'>";
	html2+="<col style='width: 67px'>";
	html2+="<col style='width: 113px'>";
	html2+="</colgroup>";
	html2+="<tr>";
		html2+="<td colspan='6'><span class='label label-danger'>경력사항</span></td>";
	html2+="</tr>";
	html2+="<tr class='info'>";
	html2+="<td class='tg-c3ow' colspan='2'>근무기간</td>";
	html2+="<td class='tg-c3ow'>근무처</td>";
	html2+="<td class='tg-c3ow' colspan='2'>담당업무</td>";
	html2+="<td class='tg-c3ow'>근속연수</td>";
	html2+="</tr>";
	if(data["list"][0].company2!=null){
		$.each(data["list"], function(index, obj){
	     html2+="<tr>";
	     html2+="<td class='tg-0pky' colspan='2'>"+obj.sdate2+"</td>";
	     html2+="<td class='tg-0pky'>"+obj.company2+"</td>";
	     html2+="<td class='tg-0pky' colspan='2'>"+obj.sjob2+"</td>";
	     html2+="<td class='tg-0pky'>"+obj.sconti2+"&nbsp;<input type='button' value='삭제' class='btn btn-warning btn-xs' onclick='table2Del("+obj.tnum2+","+obj.num+")'/></td>";
	     html2+="</tr>";
		});
	 }   
		html2+="<form id='table2"+data["list"][0].num+"' method='post'>";
   		html2+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
   		html2+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
   		html2+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
   		html2+="<tr>";   		
   		html2+="<td class='tg-0pky' colspan='2'><input type='date' name='t2Sdate' id='t2Sdate'>~<input type='date' name='t2Edate' id='t2Edate'></td>";
   		html2+="<td><div class='col-xs-13'><input type='text' name='company2' id='company2' class='form-control input-sm'/></div></td>";
   		html2+="<td colspan='2'><div class='col-xs-12'><input type='text' name='sjob2' id='sjob2' class='form-control input-sm'/></div></td>";
   		html2+="<td><div class='col-xs-8'><input type='text' name='sconti2' id='sconti2' onclick='dateCalc()' class='form-control input-sm'/></div>&nbsp;<input type='button' class='btn btn-primary btn-xs' value='등록' onclick='table2("+data["list"][0].num+")'/></td>";
   		html2+="</tr>";
   		html2+="</form>";	
	   html2+="</table>";
       $('#career2').html(html2);
       
       $.ajax({
	    	 url : "getStudentByNumTable3",
	    	 type : "GET",
	    	 data: {"num" : data["list"][0].num, "lcode" : data["list"][0].lcode, "mnum" : data["list"][0].mnum },
	    	 dataType : "json",
	    	 success : career3Html,	    	 
	    	 error : function() { alert("error"); }
	 });	
}

function career3Html(data){	
	 var html3="";
	 html3+="<table  class='table table-bordered' style='undefined;table-layout: fixed; width: 850px'>";
	 html3+="<colgroup>";
	 html3+="<col style='width: 58px'>";
     html3+="<col style='width: 63px'>";
   	 html3+="<col style='width: 136px'>";
	 html3+="<col style='width: 106px'>";
	 html3+="<col style='width: 67px'>";
	 html3+="<col style='width: 113px'>";
	 html3+="</colgroup>";
     html3+="<tr>";
	 html3+="<td colspan='6'><span class='label label-danger'>교육사항</span></td>";
	 html3+="</tr>";
	 html3+="<tr class='info'>";
	 html3+="<td class='tg-c3ow' colspan='2'>교육기간</td>";
	 html3+="<td class='tg-c3ow' colspan='2'>교육과정명</td>";
	 html3+="<td class='tg-c3ow' colspan='2'>훈련기관</td>";
	 html3+="</tr>";
	 
	 if(data["list"][0].scourse3!=null){
			$.each(data["list"], function(index, obj){
		     html3+="<tr>";
		     html3+="<td class='tg-0pky' colspan='2'>"+obj.sdate3+"</td>";
			 html3+="<td class='tg-0pky' colspan='2'>"+obj.scourse3+"</td>";
		     html3+="<td class='tg-0pky' colspan='2'>"+obj.sname3+"&nbsp;<input type='button' value='삭제' class='btn btn-warning btn-xs' onclick='table3Del("+obj.tnum3+","+obj.num+")'/></td>";
		     html3+="</tr>";
			});
	 }   

	 html3+="<form id='table3"+data["list"][0].num+"' method='post'>";
   	 html3+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
   	 html3+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
   	 html3+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
   	 html3+="<tr>";   		
   	 html3+="<td class='tg-0pky' colspan='2'><input type='date' name='t3Sdate' id='t3Sdate'>~<input type='date' name='t3Edate' id='t3Edate'></td>";
   	 html3+="<td colspan='2'><div class='col-xs-10'><input type='text' name='scourse3' id='scourse3' class='form-control input-sm'/></div></td>";
   	 html3+="<td colspan='2'><div class='col-xs-10'><input type='text' name='sname3' id='sname3' class='form-control input-sm'/></div>&nbsp;<input type='button' class='btn btn-primary btn-xs' value='등록' onclick='table3("+data["list"][0].num+")'/></td>";
   	 html3+="</tr>";
   	 html3+="</form>";		 
	
     html3+="</table>";
     $('#career3').html(html3);
     
     $.ajax({
    	 url : "getStudentByNumTable4",
    	 type : "GET",
    	 data: {"num" : data["list"][0].num, "lcode" : data["list"][0].lcode, "mnum" : data["list"][0].mnum },
    	 dataType : "json",
    	 success : career4Html,	    	 
    	 error : function() { alert("error"); }
     });
}
function career4Html(data){
	 var html4="";
	 html4+="<table  class='table table-bordered' style='undefined;table-layout: fixed; width: 850px'>";
	 html4+="<colgroup>";
	 html4+="<col style='width: 58px'>";
     html4+="<col style='width: 63px'>";
   	 html4+="<col style='width: 136px'>";
	 html4+="<col style='width: 106px'>";
	 html4+="<col style='width: 67px'>";
	 html4+="<col style='width: 113px'>";
	 html4+="</colgroup>";
	 html4+="<tr>";
	 html4+="<td colspan='6'><span class='label label-danger'>자격사항</span></td>";
	 html4+="</tr>";
	 html4+="<tr class='info'>";
	 html4+="<td class='tg-c3ow' colspan='2'>취득일</td>";
	 html4+="<td class='tg-c3ow' colspan='2'>자격증명</td>";
	 html4+="<td class='tg-c3ow' colspan='2'>시행처</td>";
	 html4+="</tr>";
	 if(data["list"][0].scert4!=null){
		 $.each(data["list"], function(index, obj){
			 html4+="<tr>";
			 html4+="<td class='tg-0pky' colspan='2'>"+obj.sdate4+"</td>";
			 html4+="<td class='tg-0pky' colspan='2'>"+obj.scert4+"</td>";
			 html4+="<td class='tg-0pky' colspan='2'>"+obj.ssihang4+"&nbsp;<input type='button' value='삭제' class='btn btn-warning btn-xs' onclick='table4Del("+obj.tnum4+","+obj.num+")'/></td>";
			 html4+="</tr>";
		 });
	 }
	 
	 html4+="<form id='table4"+data["list"][0].num+"' method='post'>";
   	 html4+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
   	 html4+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
   	 html4+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
   	 html4+="<tr>";   		
   	 html4+="<td class='tg-0pky' colspan='2'><input type='date' name='sdate4' id='sdate4'></td>";
   	 html4+="<td colspan='2'><div class='col-xs-10'><input type='text'  name='scert4' id='scert4' class='form-control input-sm'/></div></td>";
   	 html4+="<td colspan='2'><div class='col-xs-10'><input type='text'  name='ssihang4' id='ssihang4' class='form-control input-sm'/></div>&nbsp;<input type='button' class='btn btn-primary btn-xs' value='등록' onclick='table4("+data["list"][0].num+")'/></td>";
   	 html4+="</tr>";
   	 html4+="</form>";
	    html4+="</table>";
	   
	    $('#career4').html(html4);
	    
	    $.ajax({
	    	 url : "getStudentByNumTable5",
	    	 type : "GET",
	    	 data: {"num" : data["list"][0].num, "lcode" : data["list"][0].lcode, "mnum" : data["list"][0].mnum },
	    	 dataType : "json",
	    	 success : career5Html,	    	 
	    	 error : function() { alert("error"); }
	     });
}
function career5Html(data){
	
	 var html5="";
	 html5+="<table  class='table table-bordered' style='undefined;table-layout: fixed; width: 850px'>";
	 html5+="<colgroup>";
	 html5+="<col style='width: 58px'>";
     html5+="<col style='width: 63px'>";
   	 html5+="<col style='width: 136px'>";
	 html5+="<col style='width: 106px'>";
	 html5+="<col style='width: 67px'>";
	 html5+="<col style='width: 113px'>";
	 html5+="</colgroup>";
	 html5+="<tr>";
     html5+="<td colspan='6'><span class='label label-danger'>보유기술 및 능력(프로젝트)</span></td>";
     html5+="</tr>";
     html5+="<tr class='info'>";
     html5+="<td class='tg-c3ow' colspan='2'>보유기술 및 능력</td>";
     html5+="<td class='tg-c3ow'>수준(상/중/하)</td>";
     html5+="<td class='tg-c3ow' colspan='3'>상세내용</td>";
     html5+="</tr>";
   
   if(data["list"][0].skill5!=null){
		 $.each(data["list"], function(index, obj){
			 scontent5=obj.scontent5.replace(/\n/g,"<br/>"); 
		     html5+="<tr>";
		     html5+="<td class='tg-0pky' colspan='2'>"+obj.skill5+"</td>";
		     html5+="<td class='tg-0pky'>"+obj.slevel5+"</td>";
		     html5+="<td class='tg-0pky' colspan='3'>"+scontent5+"&nbsp;<input type='button' value='삭제' class='btn btn-warning btn-xs' onclick='table5Del("+obj.tnum5+","+obj.num+")'/></td>";
		     html5+="</tr>";
		 });
	 }
   
	 html5+="<form id='table5"+data["list"][0].num+"' method='post'>";
   	 html5+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
   	 html5+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
   	 html5+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
     html5+="<tr>";
     html5+="<td colspan='2' valign='middle'><div class='col-xs-12'><input type='text' name='skill5' id='skill5' class='form-control input-sm'/></div></td>";
     html5+="<td valign='middle'><div class='col-xs-10'><input type='text' name='slevel5' id='slevel5' class='form-control input-sm'/></div></td>";
     html5+="<td colspan='3'><textarea rows='5' cols='50' name='scontent5' id='scontent5' class='form-control'></textarea>&nbsp;<input type='button' class='btn btn-primary btn-xs' value='등록' onclick='table5("+data["list"][0].num+")'/></td>";
     html5+="</tr>"; 
   	 html5+="</form>";     
 
   html5+="</table>";
   
   $('#career5').html(html5);
   
   $.ajax({
  	 url : "getStudentByNumTable6",
  	 type : "GET",
  	 data: {"num" : data["list"][0].num, "lcode" : data["list"][0].lcode, "mnum" : data["list"][0].mnum },
  	 dataType : "json",
  	 success : career6Html,	    	 
  	 error : function() { alert("error"); }
   });
}
function career6Html(data){
	 //alert(data["list"].length);
	 var html6="";
	 html6+="<table  class='table table-bordered' style='undefined;table-layout: fixed; width: 850px'>";
	 html6+="<colgroup>";
	 html6+="<col style='width: 58px'>";
     html6+="<col style='width: 63px'>";
   	 html6+="<col style='width: 136px'>";
	 html6+="<col style='width: 106px'>";
	 html6+="<col style='width: 67px'>";
	 html6+="<col style='width: 113px'>";
	 html6+="</colgroup>";
	  html6+="<tr>";
	     html6+="<td colspan='6'><span class='label label-danger'>프로젝트 경험</span></td>";
	   html6+="</tr>";
	   html6+="<tr class='info'>";
	     html6+="<td class='tg-c3ow' colspan='2'>프로젝트기간</td>";
	     html6+="<td class='tg-c3ow'>프로젝트제목</td>";
	     html6+="<td class='tg-c3ow' colspan='3'>프로젝트 내용</td>";
	   html6+="</tr>";
	   
	   if(data["list"][0].stitle6!=null){
			 $.each(data["list"], function(index, obj){
				   //scontent6 = obj.scontent6.replace("\n", "<br/>");
				   scontent6=obj.scontent6.replace(/\n/g,"<br/>"); 
				   html6+="<tr>";
				   html6+="<td class='tg-0pky' colspan='2'>"+obj.sdate6+"</td>";
				   html6+="<td class='tg-0pky'>"+obj.stitle6+"</td>";
				   html6+="<td class='tg-0pky' colspan='3'>"+scontent6+"&nbsp;<input type='button' value='삭제' class='btn btn-warning btn-xs' onclick='table6Del("+obj.tnum6+","+obj.num+")'/></td>";
				   html6+="</tr>";
			 });
		 }
	   
	     html6+="<form id='table6"+data["list"][0].num+"' method='post'>";
	   	 html6+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
	   	 html6+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
	   	 html6+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
	     html6+="<tr>";
	     html6+="<td class='tg-0pky' colspan='2'><input type='date' name='t6Sdate' id='t6Sdate'>~<input type='date' name='t6Edate' id='t6Edate'></td>";
	     html6+="<td><div class='col-xs-13'><input type='text' name='stitle6' id='stitle6' class='form-control'/></div></td>";
	     html6+="<td colspan='3'><textarea rows='5' cols='50' name='scontent6' id='scontent6' class='form-control'></textarea>&nbsp;<input type='button' class='btn btn-primary btn-xs' value='등록' onclick='table6("+data["list"][0].num+")'/></td>";
	     html6+="</tr>";	   
	   	 html6+="</form>";	     
	
	     html6+="</table>";
	   /* 	 html6+="<tr>";
	     html6+="<td class='tg-0pky' colspan='6' align='right'><input type='button' value='이력서저장다운로드' onclick='career2Excel("+data["list"][0].num+","+data["list"][0].lcode+","+data["list"][0].mnum+")'></td>";
	     html6+="</tr>";
	     html6+="</table>"; */
	   
	     $('#career6').html(html6);
	     // 새로추가함
	     $.ajax({
	      	 url : "getStudentByNumTable7",
	      	 type : "GET",
	      	 data: {"num" : data["list"][0].num, "lcode" : data["list"][0].lcode, "mnum" : data["list"][0].mnum },
	      	 dataType : "json",
	      	 success : career7Html,	    	 
	      	 error : function() { alert("error"); }
	       });
}

function career7Html(data){	
	   var html7="";
	   html7+="<table  class='table table-bordered' style='undefined;table-layout: fixed; width: 850px'>";
	   html7+="<colgroup>";
	   html7+="<col style='width: 58px'>";
       html7+="<col style='width: 63px'>";
  	   html7+="<col style='width: 136px'>";
	   html7+="<col style='width: 106px'>";
	   html7+="<col style='width: 67px'>";
	   html7+="<col style='width: 113px'>";
	   html7+="</colgroup>";
	   html7+="<tr>";
	   html7+="<td class='info' colspan='6'><span class='label label-danger'>자기소개서</span></td>";
	   html7+="</tr>";
	   
	    if(data["list"][0].scontent7!=null){
			 $.each(data["list"], function(index, obj){				  
				   //scontent6 = obj.scontent6.replace("\n", "<br/>");
				   //scontent7=obj.scontent7.replace(/\n/g,"<br/>"); 
				   html7+="<form id='table7"+data["list"][0].num+"' method='post'>";
				   html7+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
				   html7+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
				   html7+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
				   html7+="<tr>";
				   html7+="<td class='tg-0pky' colspan='6'><textarea rows='35' cols='100' name='scontent7' id='scontent7' class='form-control'>"+obj.scontent7+"</textarea>&nbsp;<input type='button' class='btn btn-primary btn-xs' value='등록' onclick='table7("+data["list"][0].num+")'/></td>";
				   html7+="</tr>";	   
				   html7+="</form>";
			 });
		 }else{
			 html7+="<form id='table7"+data["list"][0].num+"' method='post'>";
		   	 html7+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
		   	 html7+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
		   	 html7+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
		     html7+="<tr>";
		     html7+="<td colspan='6'><textarea rows='35' cols='100' name='scontent7' id='scontent7' class='form-control'></textarea>&nbsp;<input type='button' value='등록' class='btn btn-primary btn-xs' onclick='table7("+data["list"][0].num+")'/></td>";
		     html7+="</tr>";	   
		   	 html7+="</form>";
		 } 
	    
	    /*  html7+="<form id='table7"+data["list"][0].num+"' method='post'>";
	   	 html7+="<input type='hidden' name='num' id='num' value='"+data["list"][0].num+"'/>";
	   	 html7+="<input type='hidden' name='lcode' id='lcode' value='"+data["list"][0].lcode+"'/>";
	   	 html7+="<input type='hidden' name='mnum' id='mnum' value='"+data["list"][0].mnum+"'/>";
	     html7+="<tr>";
	     html7+="<td class='tg-0pky' colspan='6'><textarea rows='40' cols='100' name='scontent7' id='scontent7'></textarea>&nbsp;<input type='button' value='등록' onclick='table7("+data["list"][0].num+")'/></td>";
	     html7+="</tr>";	   
	   	 html7+="</form>"; */
	     
	   	 html7+="<tr>";
	     html7+="<td class='tg-0pky' colspan='6' align='right'><input type='button' value='이력서저장다운로드' class='btn btn-success btn-xs' onclick='career2Excel("+data["list"][0].num+","+data["list"][0].lcode+","+data["list"][0].mnum+")'></td>";
	     html7+="</tr>";
	     html7+="</table>";
	   
	     $('#career7').html(html7);
}

function career2Excel(num, lcode, mnum){
	alert(num+","+mnum);
	 // alert(lcode+","+mnum+","+num);	     
    $.ajax({
   	 url : "careerExcel2",
   	 type : "GET",
   	 data: {"num" : num, "lcode" : lcode, "mnum" : mnum },
   	 success : function(data){ 
   		 alert("이력서엑셀에저장");
   		 window.open("${path}/careerExcelDown2?num="+num+"&lcode="+lcode+"&mnum="+mnum,"","width=500, height=300,left=400,top=300");	 
   	 },	    	 
   	 error : function() { alert("error"); }
    });
}

function dateCalc(){
	 var sdd=$("#t2Sdate").val(); 
	 var edd=$("#t2Edate").val(); 
	 var ar1 = sdd.split('-');
	 var ar2 = edd.split('-');
	 sdd = new Date(ar1[0], ar1[1], ar1[2]);
	 edd = new Date(ar2[0], ar2[1], ar2[2]);	    
	 var elapsed = edd.getTime() - sdd.getTime(); 
	 var elapsedDay = elapsed / 1000/60/60/24;
	 // 두날짜의 차이 일수
	 console.log('총 지난 일수 : ' + elapsedDay + '일'); 
	 var year;
	 var month;
	 var day;
	 if(elapsedDay < 30) {
		 day = elapsedDay;
		 $("#sconti2").val(day + '일');
	 } else if (elapsedDay < 365) {
			 month = Math.floor(elapsedDay/30);
			 day = elapsedDay - (month * 30); 
			 $("#sconti2").val(month + '개월 ' + day + '일');
	         } else {
			   	    year = Math.floor(elapsedDay/365);
				    month = Math.floor((elapsedDay-(year*365))/30); 
				    day = elapsedDay - (year*365) - (month*30);
				    $("#sconti2").val(year + '년 ' + month + '개월 ');
	        }		   
}

function table1Del(tnum, num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
  	  $.ajax({
	            url: "table1Del",
	            data : {"tnum" : tnum},
	            type: "GET",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("삭제완료");
	                career(lcode,mnum,num);
	            }
	        }) 
}
function table2Del(tnum2, num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  $.ajax({
	            url: "table2Del",
	            data : {"tnum2" : tnum2},
	            type: "GET",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("삭제완료");
	                career(lcode,mnum,num);
	            }
	        }) 
}
function table3Del(tnum3, num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  $.ajax({
	            url: "table3Del",
	            data : {"tnum3" : tnum3},
	            type: "GET",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("삭제완료");
	                career(lcode,mnum,num);
	            }
	        }) 
}
function table4Del(tnum4, num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  $.ajax({
	            url: "table4Del",
	            data : {"tnum4" : tnum4},
	            type: "GET",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("삭제완료");
	                career(lcode,mnum,num);
	            }
	        }) 
}
function table5Del(tnum5, num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  $.ajax({
	            url: "table5Del",
	            data : {"tnum5" : tnum5},
	            type: "GET",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("삭제완료");
	                career(lcode,mnum,num);
	            }
	        }) 
}
function table6Del(tnum6, num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  $.ajax({
	            url: "table6Del",
	            data : {"tnum6" : tnum6},
	            type: "GET",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("삭제완료");
	                career(lcode,mnum,num);
	            }
	        }) 
}

function table7Del(tnum7, num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  $.ajax({
	            url: "table7Del",
	            data : {"tnum7" : tnum7},
	            type: "GET",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("삭제완료");
	                career(lcode,mnum,num);
	            }
	        }) 
}

function table2(num){
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  var company2=$("#company2").val();
	  var sjob2=$("#sjob2").val();
	  var sconti2=$("#sconti2").val(); 
	  var t2Sdate=$("#t2Sdate").val(); 
	  var t2Edate=$("#t2Edate").val(); 
    $.ajax({
        url: "table2",
        data: { "num":num,"lcode":lcode,"mnum":mnum,"t2Sdate":t2Sdate, "t2Edate":t2Edate,"company2":company2,"sjob2":sjob2,"sconti2":sconti2},
        type: "POST",
        success: function(data){
            console.log(data);
            //document.getElementById('result').innerHTML ="데이터베이스입력완료";
            alert("데이터베이스입력완료");
            career(lcode,mnum,num);
            //location.href='/resume/list';                
        }
    }) 
}
function table3(num){
	alert(num);
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  var scourse3=$("#scourse3").val();
	  var sname3=$("#sname3").val(); 
	  var t3Sdate=$("#t3Sdate").val(); 
	  var t3Edate=$("#t3Edate").val(); 
  $.ajax({
      url: "table3",
      data: { "num":num,"lcode":lcode,"mnum":mnum,"t3Sdate":t3Sdate, "t3Edate":t3Edate,"scourse3":scourse3,"sname3":sname3},
      type: "POST",
      success: function(data){
          console.log(data);
          //document.getElementById('result').innerHTML ="데이터베이스입력완료";
          alert("데이터베이스입력완료");
          career(lcode,mnum,num);
          //location.href='/resume/list';                
      }
  }) 
}

function table4(num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  var sdate4=$("#sdate4").val();
	  var scert4=$("#scert4").val(); 
	  var ssihang4=$("#ssihang4").val(); 
  $.ajax({
      url: "table4",
      data: { "num":num,"lcode":lcode,"mnum":mnum,"sdate4":sdate4, "scert4":scert4,"ssihang4":ssihang4},
      type: "POST",
      success: function(data){
          console.log(data);
          //document.getElementById('result').innerHTML ="데이터베이스입력완료";
          alert("데이터베이스입력완료");
          career(lcode,mnum,num);
          //location.href='/resume/list';                
      }
  }) 
}

function table5(num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  var skill5=$("#skill5").val();
	  var slevel5=$("#slevel5").val(); 
	  var scontent5=$("#scontent5").val(); 
$.ajax({
    url: "table5",
    data: { "num":num,"lcode":lcode,"mnum":mnum,"skill5":skill5, "slevel5":slevel5,"scontent5":scontent5},
    type: "POST",
    success: function(data){
        console.log(data);
        //document.getElementById('result').innerHTML ="데이터베이스입력완료";
        alert("데이터베이스입력완료");
        career(lcode,mnum,num);
        //location.href='/resume/list';                
    }
}) 
}
function table6(num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();		  
	  var t6Sdate=$("#t6Sdate").val(); 
	  var t6Edate=$("#t6Edate").val(); 	  
	  var stitle6=$("#stitle6").val(); 
	  var scontent6=$("#scontent6").val(); 
$.ajax({
  url: "table6",
  data: { "num":num,"lcode":lcode,"mnum":mnum,"stitle6":stitle6, "scontent6":scontent6,"t6Sdate":t6Sdate,"t6Edate":t6Edate},
  type: "POST",
  success: function(data){
      console.log(data);
      //document.getElementById('result').innerHTML ="데이터베이스입력완료";
      alert("데이터베이스입력완료");
      career(lcode,mnum,num);
      //location.href='/resume/list';                
  }
}) 
}

function table7(num){	
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();		  
	  var scontent7=$("#scontent7").val(); 
$.ajax({
url: "table7",
data: { "num":num,"lcode":lcode,"mnum":mnum, "scontent7":scontent7},
type: "POST",
success: function(data){
    console.log(data);
    //document.getElementById('result').innerHTML ="데이터베이스입력완료";
    alert("데이터베이스입력완료");
    career(lcode,mnum,num);
    //location.href='/resume/list';                
}
}) 
}

function table1(num){
	  var lcode=$("#lcode").val();
	  var mnum=$("#mnum").val();	
	  var student=$("#student").val();
	  var smajor=$("#smajor").val();
	  var scong=$("#scong").val(); 
	  var t1Sdate=$("#t1Sdate").val(); 
	  var t1Edate=$("#t1Edate").val(); 
      $.ajax({
          url: "table1",
          data: { "num":num,"lcode":lcode,"mnum":mnum,"t1Sdate":t1Sdate, "t1Edate":t1Edate,"student":student,"smajor":smajor,"scong":scong},
          type: "POST",
          success: function(data){
              console.log(data);
              //document.getElementById('result').innerHTML ="데이터베이스입력완료";
              alert("데이터베이스입력완료");
              career(lcode,mnum,num);
              //location.href='/resume/list';                
          }
      }) 
}

function addrAdd(num){
	//alert(part+":"+num);
	var addr=$('#addr').val();
	$.ajax({
		 url : "addr",
		 type : "POST",
		 data : { "num" : num, "addr" : addr},
		 success : function(){ 
            alert("주소등록성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function csave(cnum, lcode, mnum){
	//alert(part+":"+num);
	var csave=$('#csave').val();
	$.ajax({
		 url : "csave",
		 type : "POST",
		 data : { "cnum" : cnum, "csave" : csave, "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("등록성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function cstart(cnum, lcode, mnum){
	//alert(part+":"+num);
	var cstart=$('#cstart').val();
	$.ajax({
		 url : "cstart",
		 type : "POST",
		 data : { "cnum" : cnum, "cstart" : cstart, "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("등록성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function cinsur(cnum, lcode, mnum){
	//alert(part+":"+num);
	var cinsur=$('#cinsur').val();
	$.ajax({
		 url : "cinsur",
		 type : "POST",
		 data : { "cnum" : cnum, "cinsur" : cinsur, "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("등록성공");
		 },
		 error : function() {  alert("error");  }
	});	
}
function careerExcel(num, lcode, mnum){
	alert(num+","+mnum);
	 // alert(lcode+","+mnum+","+num);	     
    $.ajax({
   	 url : "careerExcel",
   	 type : "GET",
   	 data: {"num" : num, "lcode" : lcode, "mnum" : mnum },
   	 success : function(data){ 
   		 alert("교육생카드엑셀에저장");
   		 window.open("${path}/careerExcelDown?num="+num+"&lcode="+lcode+"&mnum="+mnum,"","width=500, height=300,left=400,top=300");	 
   	 },	    	 
   	 error : function() { alert("error"); }
    });
}
function content(cnum, lcode, mnum){
   var ccontent=$('#content').val();
   //alert(data);
   $.ajax({
	   url : "skillcontent",
	   type : "POST",
	   data : { "ccontent" : ccontent, "cnum" : cnum, "lcode" : lcode, "mnum" : mnum},
	   success : function(data){ alert("OK"); },
	   error : function(){ alert("error");  }
   });
}
function part1(cpart, cnum, lcode, mnum){
	//alert(part+":"+num);
	$.ajax({
		 url : "cpart",
		 type : "GET",
		 data : { "cnum" : cnum, "cpart" : cpart, "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("등록성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function part1cancel(cpart, cnum, lcode, mnum){
	//alert(part+":"+num);
	$.ajax({
		 url : "cpartcancel",
		 type : "GET",
		 data : { "cnum" : cnum, "cpart" : cpart , "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("취소성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function part2(cloc, cnum, lcode, mnum){
	//alert(part+":"+num);
	$.ajax({
		 url : "cpart1",
		 type : "GET",
		 data : { "cnum" : cnum, "cloc" : cloc , "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("등록성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function part2cancel(cloc, cnum, lcode, mnum){
	//alert(part+":"+num);
	$.ajax({
		 url : "cpartcancel1",
		 type : "GET",
		 data : { "cnum" : cnum, "cloc" : cloc , "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("취소성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function part3(cskill, cnum, lcode, mnum){
	//alert(part+":"+num);
	$.ajax({
		 url : "cpart2",
		 type : "GET",
		 data : { "cnum" : cnum, "cskill" : cskill , "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("등록성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function part3cancel(cskill, cnum, lcode, mnum){
	//alert(part+":"+num);
	$.ajax({
		 url : "cpartcancel2",
		 type : "GET",
		 data : { "cnum" : cnum, "cskill" : cskill , "lcode" : lcode, "mnum" : mnum},
		 success : function(){ 
            alert("취소성공");
		 },
		 error : function() {  alert("error");  }
	});	
}

function career(lcode,mnum,num){
	    // alert(lcode+","+mnum+","+num);	     
	     $.ajax({
	    	 url : "getStudentByNumTable1",
	    	 type : "GET",
	    	 data: {"num" : num, "lcode" : lcode, "mnum" : mnum },
	    	 dataType : "json",
	    	 success : careerHtml,	    	 
	    	 error : function() { alert("error"); }
	     });
}
</script>
</head>
<body>
<div class="container" style="margin-top:0px">
<fieldset style="width: 99%; border-style: dotted;">
<div class="panel panel-default">
<table style="width: 100%"  class='table table-hover'>
  <tr>
        <td style='vertical-align: middle;' align='center'><b>정부사업 사업선택</b></td>
        <td align="center">
           <select id='lcourse'>             
           </select>                    
        </td>
        <td style='width:200px;'>
           <select style='width:350px;' id='mcourse' >             
           </select>                    
        </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
       <%--  <td style='width:120px;' align="center"><b><input type='button' id="btn2" value='교육운영관리' class='btn btn-success btn-xs' onclick="location.href='${path}/list?lcode=${lcode}&mnum=${mnum}'"></b></td>
        <td style='width:120px;' align="center"><b><input type='button' id="btn" value='메인으로' class='btn btn-success btn-xs' onclick="location.href='${path}/main'"></b></td> --%>
    </tr>
       <tr>
   <td colspan='5'>
   <nav class='navbar navbar-inverse'>
   <div class='container-fluid'>
    <div class='navbar-header'>
    <a class='navbar-brand' href='${path}/'>SAF MENU▶</a>
     </div>
     <ul class='nav navbar-nav'>
     <li class='active'><a href="javascript:location.href='${path}/list?lcode=${lcode}&mnum=${mnum}'">교육운영관리</a></li>
     <li><a href="javascript:location.href='${path}/main'">전체과정으로</a></li> 
     </ul>
     </div>
     </nav>
     </td>
     </tr>    
 </table>
 </div>
<table style="width: 100%" class='table table-bordered'>

 <tr>
   <td valign="top" width='150px;'>
    <div id="cartlist" style="width: 200px;"></div>
   </td>
   <td style="vertical-align: top; text-align: left;" colspan="4">
   <h4><span class="label label-success">스마트훈련관리시스템 _이력서 관리</span></h4>
   <div id="career" style="display: none;"></div> 
   <div id="career2" style="display: block;"></div> 
   <div id="career3" style="display: block;"></div> 
   <div id="career4" style="display: block;"></div>
   <div id="career5" style="display: block;"></div> 
   <div id="career6" style="display: block;"></div> 
   <div id="career7" style="display: block;"></div>  
   </td>
 </tr> 
</table>
<div class="panel panel-default" style="text-align: center;">
<div class="panel-footer">
      스마트훈련관리시스템<br>
	스마트 아카이브 공장(SAF_Smart Archive Factory) | 개발자 : 박매일 | 이메일: bitcocom@empas.com<br>
	©SAFLAB. ALL RIGHTS RESERVED(등록:제C-2021-005791)
	</div>
</div>
</fieldset>
</div>
</body>
</html>
