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

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>

a:hover{
  text-decoration: none;
  color: white;    
}
a:link{
  text-decoration: none;
  color: white; 
}


  body, table {
	  font-size: 12px;
	  font-family: 나눔고딕, NanumGothic, NanumBarunGothic,'Nanum Gothic',arial,verdana,sans-serif;
   }

  .bg{
    color: white;
  }
  
  input.form-text {
        border: 1px solid #bcbcbc;
        height: 28px;
  }
      
	input { background-color: white; }
	input[type=text] { border:dotted 1px red }
	input[type=button] { border:solid 1px }
      
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
var tcourse1="";
var tcourse1 = new Array();
var tteach1 = new Array();
var date1;
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
    	        url: "timeLoad", // 시간표로드
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
	if(data.istime=="NO"){
		alert("시간표가 등록되지 않음");
		return false;
	}
	//alert(data);
	console.log(data);
	var html="";
	html+="<table class='table table-bordered'>";
	html+="<colgroup>";
	html+="<col style='width: 70px'>";
	html+="<col style='width: 130px'>";
	html+="<col style='width: 60px'>";
	html+="<col style='width: 75px'>";
	html+="<col style='width: 131px'>";
	html+="<col style='width: 177px'>";
	html+="<col style='width: 111px'>";
	html+="<col style='width: 135px'>";
	html+="<col style='width: 96px'>";
	html+="</colgroup>";
	html+="<thead>";
	html+="<tr>";
	html+="<th class='info' colspan='9' rowspan='2'>"+data.mname+"</th>";
	html+="</tr>";
	html+="<tr>";
	html+="</tr>";
	html+="</thead>";
	html+="<tbody>";
	html+="<tr>";
	html+="<td class='tg-zv4m' colspan='9'></td>";
	html+="</tr>";
	html+="<tr>";
	html+="<td class='tg-zv4m' colspan='9'>운영기관 : AI 인공지능 교육센터</td>";
	  
	    html+="</tr>";
	  html+="<tr>";
	    html+="<td class='tg-zv4m' colspan='9'>과 정 명 : "+data.mname+"</td>";
	
	  html+="</tr>";
	  html+="<tr>";
	    html+="<td class='tg-zv4m' colspan='9'>훈련기간 : "+data.mday+"</td>";

	  html+="</tr>";
	  html+="<tr>";
	  html+="<td class='tg-0pky' colspan='9'>엑셀시간표(파일 : "+data.istime+")</td>";
	  html+="</tr>";
	  html+="<tr class='success' align='center'>";
	    html+="<td class='tg-c3ow'>구분</td>";
	    html+="<td class='tg-c3ow'>날짜</td>";
	    html+="<td class='tg-c3ow'>요일</td>";
	    html+="<td class='tg-c3ow'>교시</td>";
	    html+="<td class='tg-c3ow'>시간</td>";
	    html+="<td class='tg-c3ow'>과목명</td>";
	    html+="<td class='tg-c3ow'>강사명</td>";
	    html+="<td class='tg-c3ow'>장소</td>";
	    html+="<td class='tg-c3ow'>누적시간</td>";
	    html+="</tr>";
	    var endKey=Object.keys(data["list"]).length;
	    //alert(endKey);
        var pos=1; 
    	var date, date2;
	     $.each(data["list"], function(index, obj){
	    	
	    	html+="<tr>";
	    	if(obj.tday!=""){   
	  	      html+="<td class='info' id='tday"+obj.tday+"'><font color='black'>"+obj.tday+"<a href='javascript:window.scrollTo( 0, 0 );'><span class='badge'>↑</span></a></font>";
	    	}else{
	    	  html+="<td class='tg-c3ow'>"+obj.tday+"</td>";
	    	}
	    
	    	if(obj.tdate!=null){
	    		 date1=new Date(obj.tdate);
	    		 date=moment(date1).format('YYYY-MM-DD');
	    		 date2=new Date();
	    		 date2=moment(date2).format('YYYY-MM-DD');
	    		 if(date==date2){
	    			 html+="<td class='tg-c3ow22' bgColor='red' id='tdate"+date+"'>"+date+"</td>";
	    		 }else{	    			 
	    		     html+="<td class='tg-c3ow' id='tdate"+date+"'>"+date+"</td>";
	    		 }
	    	}else{	    		
	    		 html+="<td class='tg-c3ow'></td>";		
	    		 if(index==(endKey-1)){	 	  	    	
		 	  	    	html+="<span id='endKey100'><font color='white'>"+obj.tinc+"</font></span>";
		 	  	 }
	    	}	    	
	  	    html+="<td class='tg-c3ow'>"+obj.tmon+"</td>";
	  	    html+="<td class='tg-c3ow'>"+obj.tstep+"</td>";
	  	    html+="<td class='tg-c3ow'>"+obj.ttime+"</td>";
	  	    var pos1=moment(date1).format('YYYY-MM-DD')+""+pos;
	  	    //alert(pos1);
	  	    html+="<td class='tg-c3ow' id='tcourse"+pos1+"'>"+obj.tcourse+"</td>";
	  	    
	  	    html+="<td class='tg-c3ow' id='tteach"+pos1+"'>"+obj.tteach+"</td>";
	  	    
	  	    html+="<td class='tg-c3ow'>"+obj.treal+"</td>";	
	  	   if(index==(endKey-9)){	 	
	  		 html+="<td class='tg-c3ow' id='endKey101'>"+obj.tinc+"</td>"; 
	  	   }else{
	  		 html+="<td class='tg-c3ow'>"+obj.tinc+"</td>"; 
	  	   }
	  	    html+="</tr>";
	  	    pos++;
	  	    if(obj.tstep=="8교시"){
	  	    	html+="<tr>";
	  	    	html+="<td colspan='9' height='1'></td>";
	  	    	html+="</tr>";
	  	    	pos=1;
	  	    }	  	    
	    });
	  
	  html+="</tbody>";
	  html+="</table>";
	  $("#career2").html(html); 
	 
	/* var html="";
	html+="<table id='aaa' width='300px;'>";	
	html+="<tr style='vertical-align: top;'>";
	html+="<tr align='center' style='background-color: #bbdefb'>";
    html+="<td>사진</td>";
    html+="<td>번호</td>";
    html+="<td>이름</td>";
    html+="<td>주민번호</td>";   
    html+="</tr>";
	$.each(data["list"], function(index, obj){
		 html+="<tr>";           
		 html+="<td><a href='javascript:career("+obj.lcode+","+obj.mnum+","+obj.num+")'><img src='/resume/resources/"+obj.image+"' width='60px' height='60px'></a></td>";
		 html+="<td>"+obj.num+"</td>";
		 if(obj.isis==1){
			 html+="<td id='classid-"+obj.num+"' class='name'>"+obj.name+"</td>";
		 }else{
			 html+="<td id='classid-"+obj.num+"' style='text-decoration: line-through;color:red'>"+obj.name+"(중탈)</td>"; 
		 }	
		 html+="<td>"+obj.birth+"</td>";
		 html+="</tr>";		
	});
       
	 
	 html+="</table>";
	 //$("#cartlist").empty();
	 $("#cartlist").html(html); 	
    
	// $("#selectBox").val("2");
	 
	// $('#lcourse').val('${lcode}');	
	// $('#mcourse').val('${mnum}').trigger('change');
	 $('#career').css("display","none"); */
	  var offset = $("#tdate"+date2).offset();
	  $('html, body').animate({scrollTop : offset.top}, 400);
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


function userDateFn(){

   ///var userDate=new Date($("#userDate").val());
   var isDate=$("#userDate").val();
   //userDate=moment(userDate).format('YYYY-MM-DD');
   var tdate=$("#tdate"+isDate).text();   
   if(isDate!=tdate){
	   alert("훈련이 없습니다.");
	   return false;
   }
   tdate=moment(isDate).format('YYYY년MM월DD일(dddd)');
   
    
   var tcourse1=$("#tcourse"+isDate+1).text();
   var tcourse2=$("#tcourse"+isDate+2).text();
   var tcourse3=$("#tcourse"+isDate+3).text();
   var tcourse4=$("#tcourse"+isDate+4).text();
   //var tcourse5=$("#tcourse"+isDate+5).text();
   var tcourse6=$("#tcourse"+isDate+6).text();
   var tcourse7=$("#tcourse"+isDate+7).text();
   var tcourse8=$("#tcourse"+isDate+8).text();
   var tcourse9=$("#tcourse"+isDate+9).text();
   
   var tteach1=$("#tteach"+isDate+1).text();
   var tteach2=$("#tteach"+isDate+2).text();
   var tteach3=$("#tteach"+isDate+3).text();
   var tteach4=$("#tteach"+isDate+4).text();
   //var tcourse5=$("#tcourse"+isDate+5).text();
   var tteach6=$("#tteach"+isDate+6).text();
   var tteach7=$("#tteach"+isDate+7).text();
   var tteach8=$("#tteach"+isDate+8).text();
   var tteach9=$("#tteach"+isDate+9).text();
   
   var tcourse=tcourse1+"#"+tcourse2+"#"+tcourse3+"#"+tcourse4+"#"+tcourse6+"#"+tcourse7+"#"+tcourse8+"#"+tcourse9;
   var tteach=tteach1+"#"+tteach2+"#"+tteach3+"#"+tteach4+"#"+tteach6+"#"+tteach7+"#"+tteach8+"#"+tteach9;
   var mname='${mname}';
   var mday='${mday}';
   var lcode='${lcode}';
   var mnum='${mnum}';
   var endKey=$("#endKey100").text();
   var endKey1=$("#endKey101").text();
   if(endKey==""){
	   endKey=endKey1;
   }
   //alert(endKey);
   $.ajax({	  
	   url : "courseWrite",
	   type : "POST",
	   data : {"lcode" : lcode, "mnum" : mnum, "mday" : mday, "tdate": new Date(isDate), "tcourse" : tcourse, "tteach" : tteach, "endKey" : endKey},
	   success : function(){
		   alert("훈련일지 작성완료");
	   	   window.open("${path}/courseDown?lcode="+lcode+"&mnum="+mnum,"","width=500, height=300,left=400,top=300");	 
	   },
	   error : function(){ alert("error"); }	   
   })
  }
  function timeDateFn(){
	   var mname='${mname}';
	   var lcode='${lcode}';
	   var mnum='${mnum}';
	   alert(mname+":"+lcode+":"+mnum);
	   window.open("${path}/timeFileUpload?lcode="+lcode+"&mnum="+mnum,"","width=500, height=300,left=400,top=300");
  }
  
  function handler(e){
	  date=e.target.value;
	  var offset = $("#tdate"+date).offset();
	  $('html, body').animate({scrollTop : offset.top}, 400);
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
  <table class="table table-bordered"> 
        <tr class='info'>
        <td align="center"><input type='button' value="훈련시간표"  class='btn btn-success btn-sm'/>	</td>
	    <td valign="bottom">
	    <input id="userDate" type='date' name='userDate' onchange="handler(event);"/>
	    <input type='button' value="훈련일지 만들기" onClick='userDateFn()' class='btn btn-success btn-sm'/>	    
	    <input type='button' value="시간표등록(재등록)하기" onClick='timeDateFn()' class='btn btn-success btn-sm'/>
	    </td>
	    </tr>
	    
	    <tr> 
	    <td colspan='2'>
	    <div id="career" style="display: none;"></div> 
	    <div id="career2" style="display: block;"></div> 
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
