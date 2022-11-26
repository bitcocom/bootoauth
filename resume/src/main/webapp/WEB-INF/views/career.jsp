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
	html+="<table class='table table-hover'>";	
	html+="<tr style='vertical-align: top;'>";
	html+="<tr align='center' class='success'>";
    html+="<td>사진</td>";
    html+="<td>번호</td>";
    html+="<td>이름</td>";
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
	$.each(data, function(index, obj){  
		//alert(data);
		 var html="";
		     html+="<table class='table table-bordered' style='undefined;table-layout: fixed; width: 850px;'>";
		     html+="<colgroup>";
		     html+="<col style='width: 122px'>";
		     html+="<col style='width: 55px'>";
		     html+="<col style='width: 69px'>";
		     html+="<col style='width: 67px'>";
		     html+="<col style='width: 81px'>";
		     html+="<col style='width: 80px'>";
		     html+="<col style='width: 103px'>";
		     html+="<col style='width: 51px'>";
		     html+="<col style='width: 60px'>";	
			 html+="</colgroup>";
			 html+="<thead>";
			 html+="<tr style='text-align: center; vertical-align: middle;'>";
			 html+="<th class='tg-ub5w' colspan='7' rowspan='2' style='text-align: left; vertical-align: middle;'><h4><span class='label label-success'>스마트훈련관리시스템 _훈련생취업관리카드</span></h4></th>";
			 html+="<th class='tg-0pky' colspan='2'>No."+obj.num+"</th>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-c3ow' width='300px'>담당</td>";
			 html+="<td class='tg-0pky'>"+obj.master+"/"+obj.mta+"</td>";
			 html+="</tr>";
			 html+="</thead>";
			 html+="<tbody>";
			 html+="<tr>";
			 html+="<td class='tg-5w3z'>과정명</td>";
			 html+="<td class='tg-0pky' colspan='6'>"+mname+"</td>";
			 html+="<td class='tg-9wq8' colspan='2' rowspan='4'><img border='1' src='${path}/resources/"+obj.image+"'></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-5w3z'>교육기간</td>";
			 html+="<td class='tg-0pky' colspan='6'>${mday}</td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-5w3z'>성명</td>";
			 html+="<td class='tg-0pky' colspan='2'>"+obj.name+"</td>";
			 html+="<td class='tg-0pky'>생년월일</td>";
			 html+="<td class='tg-0pky' colspan='3'>"+obj.birth+"</td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-5w3z'><span class='label label-danger'>주소</span></td>";
			 html+="<td class='tg-0pky' colspan='6'><div class='col-xs-11'><input type='text' name='addr' id='addr' class='form-control input-sm' value='"+obj.addr+"'></div><input type='button' value='등록' class='btn btn-primary btn-xs' onclick='addrAdd("+obj.num+")'></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-5w3z'>연락처</td>";
			 html+="<td class='tg-0pky' colspan='2'>"+obj.tel+"</td>";
			 html+="<td class='tg-c3ow'>이메일</td>";
			 html+="<td class='tg-0pky' colspan='5'>"+obj.email+"</td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-5w3z'>최종학력</td>";
			 html+="<td class='tg-0pky' colspan='8'>"+obj.college+" <b>대학교</b>  "+obj.major+" <b>학과</b></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-5w3z'>자격증보유</td>";
			 html+="<td class='tg-0pky' colspan='8'>"+obj.cert+"</td>";
			 html+="</tr>";
			 
			 html+="<tr>";
			 html+="<td class='tg-jq7l' rowspan='2'><span class='label label-danger'>희망분야(part1)</span></td>";
			 html+="<td class='tg-9wq8' colspan='2' align='center'>BigData/AI①</td>";
			 html+="<td class='tg-9wq8'>SW개발자②</td>";
			 html+="<td class='tg-9wq8'>시스템운영관리③</td>";
			 html+="<td class='tg-9wq8'>DB/기획④</td>";
			 html+="<td class='tg-9wq8'>기타⑤</td>";
			 html+="<td class='tg-9wq8'>-</td>";
			 html+="<td class='tg-9wq8'>-</td>";
			 
			 html+="</tr>";
			 html+="<tr>";
			 var arr=[];
			 if(obj.cpart!=null){
			   arr=String(obj.cpart).split('');
			 }
			 console.log($.inArray("1", arr)+1);
			 if(obj.cpart!=null && ($.inArray("1", arr) != -1)){
			   html+="<td class='tg-0pky' colspan='2' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part1cancel(1,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
			 }else{
			   html+="<td class='tg-0pky' colspan='2' align='center'><input type='button' id='btn1"+obj.num+"' value='등록①' class='btn btn-primary btn-xs' onclick='part1(1,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cpart!=null && ($.inArray("2", arr) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part1cancel(2,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
					 html+="<td class='tg-0pky' align='center'><input type='button' id='btn2"+obj.num+"' value='등록②' class='btn btn-primary btn-xs' onclick='part1(2,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cpart!=null && ($.inArray("3", arr) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part1cancel(3,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button' id='btn3"+obj.num+"' value='등록③' class='btn btn-primary btn-xs' onclick='part1(3,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cpart!=null && ($.inArray("4", arr) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part1cancel(4,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button' id='btn4"+obj.num+"' value='등록④' class='btn btn-primary btn-xs' onclick='part1(4,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>"; 
				 }
			 
			 if(obj.cpart!=null && ($.inArray("5", arr) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part1cancel(5,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button' id='btn5"+obj.num+"' value='등록⑤' class='btn btn-primary btn-xs' onclick='part1(5,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }
			 html+="<td class='tg-0pky' align='center'>-</td>";
			 html+="<td class='tg-0pky' align='center'>-</td>";
			 
			 html+="</tr>";			 
			 html+="<tr>";
			 html+="<td class='tg-jq7l' rowspan='2'><span class='label label-danger'>희망지역(part2)</span></td>";
			 html+="<td class='tg-9wq8' colspan='2' align='center'>지역무관①</td>";
			 html+="<td class='tg-9wq8'>광주②</td>";
			 html+="<td class='tg-9wq8'>전남③</td>";
			 html+="<td class='tg-9wq8'>수도권/서울④</td>";
			 html+="<td class='tg-9wq8'>기타⑤</td>";
			 html+="<td class='tg-9wq8'>-</td>";
			 html+="<td class='tg-9wq8'>-</td>";	
			 html+="</tr>";
			 html+="<tr>";
			 
			 var arr1=[];
			 if(obj.cloc!=null){
			   arr1=String(obj.cloc).split('');
			 }
			 console.log($.inArray("1", arr1)+1);
			 if(obj.cloc!=null && ($.inArray("1", arr1) != -1)){
			   html+="<td class='tg-0pky' colspan='2' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part2cancel(1,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
			 }else{
			   html+="<td class='tg-0pky' colspan='2' align='center'><input type='button' value='등록①' class='btn btn-primary btn-xs' onclick='part2(1,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cloc!=null && ($.inArray("2", arr1) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part2cancel(2,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
					 html+="<td class='tg-0pky' align='center'><input type='button' value='등록②' class='btn btn-primary btn-xs' onclick='part2(2,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cloc!=null && ($.inArray("3", arr1) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part2cancel(3,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button' value='등록③' class='btn btn-primary btn-xs' onclick='part2(3,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cloc!=null && ($.inArray("4", arr1) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part2cancel(4,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button' value='등록④' class='btn btn-primary btn-xs' onclick='part2(4,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>"; 
				 }
			 
			 if(obj.cloc!=null && ($.inArray("5", arr1) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part2cancel(5,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button'  value='등록⑤' class='btn btn-primary btn-xs' onclick='part2(5,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }
			 html+="<td class='tg-0pky' align='center'>-</td>";
			 html+="<td class='tg-0pky' align='center'>-</td>";

			 html+="</tr>";
			 
			 
			 html+="<tr>";			 
			 html+="<td class='tg-0pky' colspan='9'></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-jq7l' rowspan='2'><span class='label label-danger'>기술수준</span></td>";
			 html+="<td class='tg-9wq8'>Java①</td>";
			 html+="<td class='tg-9wq8'>Python②</td>";
			 html+="<td class='tg-9wq8'>Web③</td>";
			 html+="<td class='tg-9wq8'>Mobile<br>Android④</td>";
			 html+="<td class='tg-9wq8'>Machine<br>Learning⑤</td>";
			 html+="<td class='tg-9wq8'>Deep<br>Learning⑥</td>";
			 html+="<td class='tg-9wq8'>IoT⑦</td>";
			 html+="<td class='tg-9wq8'>기타⑧</td>";
			 html+="</tr>";
			 
			 html+="<tr>";
			 var arr2=[];
			 if(obj.cskill!=null){
			   arr2=String(obj.cskill).split('');
			 }
			 console.log($.inArray("1", arr2)+1);
			 if(obj.cskill!=null && ($.inArray("1", arr2) != -1)){
			   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(1,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
			 }else{
			   html+="<td class='tg-0pky' align='center'><input type='button' value='등록①' class='btn btn-primary btn-xs' onclick='part3(1,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cskill!=null && ($.inArray("2", arr2) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(2,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
					 html+="<td class='tg-0pky' align='center'><input type='button' value='등록②' class='btn btn-primary btn-xs' onclick='part3(2,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cskill!=null && ($.inArray("3", arr2) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(3,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button' value='등록③' class='btn btn-primary btn-xs' onclick='part3(3,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }
			 
			 if(obj.cskill!=null && ($.inArray("4", arr2) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(4,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button' value='등록④' class='btn btn-primary btn-xs' onclick='part3(4,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>"; 
				 }
			 
			 if(obj.cskill!=null && ($.inArray("5", arr2) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(5,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button'  value='등록⑤' class='btn btn-primary btn-xs' onclick='part3(5,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
			 }
			 if(obj.cskill!=null && ($.inArray("6", arr2) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(6,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button'  value='등록⑥' class='btn btn-primary btn-xs' onclick='part3(6,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
			 }
			 if(obj.cskill!=null && ($.inArray("7", arr2) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(7,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button'  value='등록⑦' class='btn btn-primary btn-xs' onclick='part3(7,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
			 }
			 if(obj.cskill!=null && ($.inArray("8", arr2) != -1)){
				   html+="<td class='tg-0pky' align='center'><font size='5' color='red'>○</font>&nbsp;<input type='button' value='취소' class='btn btn-warning btn-xs' onclick='part3cancel(8,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
				 }else{
				   html+="<td class='tg-0pky' align='center'><input type='button'  value='등록⑧' class='btn btn-primary btn-xs' onclick='part3(8,"+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";
			 }
			 html+="</tr>";
			 
			 html+="<tr>";
			 html+="<td class='info' align='center' colspan='9'>취업연계사항(날짜, 기업명, 분야, 면접일등 자유롭게 기재)</td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-jq7l' width='200px'><span class='label label-danger'>연계내용작성</span></td>";
			 if(obj.ccontent!=null){
				 html+="<td class='tg-0pky' colspan='8'><textarea cols='90' rows='15' class='form-control' id='content'>"+obj.ccontent+"</textarea>&nbsp;<input type='button' value='취업연계등록' class='btn btn-primary btn-xs' onclick='content("+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>"; 
			 }else{
				 html+="<td class='tg-0pky' colspan='8'><textarea cols='90' rows='15' class='form-control' id='content'></textarea>&nbsp;<input type='button' value='취업연계등록' class='btn btn-primary btn-xs' onclick='content("+obj.num+","+obj.lcode+","+obj.mnum+")'/></td>";	 
			 }			 
			 
			 html+="</tr>";
/* 			 html+="<tr>";
			 html+="<td class='tg-cjtp'><input type='date' id='aaa' name='aaa'/></td>";
			 html+="<td class='tg-0pky' colspan='8'><input type='text' size='80' name='bbb'/>&nbsp;<input type='button' value='등록'/></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-cjtp'><input type='date' id='aaa' name='aaa'/></td>";
			 html+="<td class='tg-0pky' colspan='8'><input type='text' size='80' name='bbb'/>&nbsp;<input type='button' value='등록'/></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-cjtp'><input type='date' id='aaa' name='aaa'/></td>";
	         html+="<td class='tg-0pky' colspan='8'><input type='text' size='80' name='bbb'/>&nbsp;<input type='button' value='등록'/></td>";
			 html+="</tr>";
		 	 html+="<tr>";
			 html+="<td class='tg-cjtp'><input type='date' id='aaa' name='aaa'/></td>";
		 	 html+="<td class='tg-0pky' colspan='8'><input type='text' size='80' name='bbb'/>&nbsp;<input type='button' value='등록'/></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td class='tg-cjtp'><input type='date' id='aaa' name='aaa'/></td>";
			 html+="<td class='tg-0pky' colspan='8'><input type='text' size='80' name='bbb'/>&nbsp;<input type='button' value='등록'/></td>";
			 html+="</tr>";
			 html+="<tr>";
		     html+="<td class='tg-cjtp'><input type='date' id='aaa' name='aaa'/></td>";
			 html+="<td class='tg-0pky' colspan='8'><input type='text' size='80' name='bbb'/>&nbsp;<input type='button' value='등록'/></td>";
			 html+="</tr>"; */
			 html+="<tr>";
			 html+="<td class='tg-jq7l'><span class='label label-danger'>최종취업</span></td>";
			 html+="<td class='tg-lboi'>기업명</td>";
			 html+="<td class='tg-lboi' colspan='2'><input type='text' size='18' class='form-control input-sm' name='csave' id='csave' value='"+obj.csave+"'/><br/><input type='button' value='등록' class='btn btn-primary btn-xs'  onclick='csave("+obj.num+")'/></td>";
			 html+="<td class='tg-lboi'>근무시작일</td>";
			 html+="<td class='tg-lboi'><input type='text' size='10' name='cstart' class='form-control input-sm' id='cstart' value='"+obj.cstart+"'/><br/><input type='button' value='등록' class='btn btn-primary btn-xs'  onclick='cstart("+obj.num+")'/></td>";
	         html+="<td class='tg-lboi'>4대보험가입일</td>";
			 html+="<td class='tg-lboi' colspan='2'><input type='text' size='13' id='cinsur' class='form-control input-sm' name='cinsur' value='"+obj.cinsur+"'/><br/><input type='button' value='등록' class='btn btn-primary btn-xs'  onclick='cinsur("+obj.num+")'/></td>";
			 html+="</tr>";
			 html+="<tr>";
			 html+="<td colspan='9' align='right'><input type='button' value='엑셀에저장 및 다운로드' class='btn btn-success btn-xs' onclick='careerExcel("+obj.num+","+obj.lcode+","+obj.mnum+")'></td>";
			 html+="</tr>";
			 html+="</tbody>";			
			 html+="</table>";  
			 $('#career').html(html);
		
	});
 
	/* 	 var dis=$('#career').css("display");
		 if(dis=="none"){
		 	$('#career').css("display","block");
		 }else{
		 	$('#career').css("display","none");
		 }	 */
	     $('#career').css("display","block");
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
	    	 url : "getStudentByNum1",
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
<div class="container" style="margin-top:5px">
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
   
   <div id="career" style="display: none;"></div> 
   </td>
 </tr> 
</table>
<div class="panel panel-default" style="text-align: center;" >
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
