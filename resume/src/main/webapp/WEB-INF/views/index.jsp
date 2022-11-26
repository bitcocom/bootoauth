<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />   
<c:if test="${admin!='admin'}">
    <c:redirect url="${path}/"/>
</c:if> 
<html>
<head>
  <title>SAF(스마트아카이브공장)</title>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
 

  body, table {
      font-family: 나눔고딕, NanumGothic, NanumBarunGothic,'Nanum Gothic',arial,verdana,sans-serif;
	  font-size: 12px;
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
<script type="text/javascript">
	$(document).ready(function(){
		lcourse();
		
		$("#btn").click(function(e){
			e.preventDefault();
			$('#memtorinsert').css("display","none");
		    var dis=$('#courseview').css("display");
		    if(dis=="none"){
		    	$('#courseview').css("display","block");
		    }else{
		    	$('#courseview').css("display","none");
		    }		    
		});
		
        $("#btn1").click(function(){
        	$('#courseview').css("display","none");
		    var dis=$('#memtorinsert').css("display");
		    if(dis=="none"){
		    	$('#memtorinsert').css("display","block");
		    }else{
		    	$('#memtorinsert').css("display","none");
		    }		    
		});
		
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
	        $.ajax({
	         url: "mcourselist",
	         data: {"mnum" : this.value},
	         type: "POST",
	         success: mlist
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
	    	html+="<option>과정을 선택하시오</option>";
		if(data["list"]!="not"){
		  $.each(data["list"], function(index, obj){
				html+="<option value='"+obj.lcode+"'>"+obj.lname+"</option>";	
		  });
		   $("#lcourse").html(html);
		   $('#lcourse').val('0').trigger('change');
		}else{
		   $("#lcourse").html("<option>데이터가 없습니다.</option>");
		 
		}
	}	
	function gogo(){		
		    var mdays1=$("#mdays1").val();
		    var mdays2=$("#mdays2").val();
		    var mdays=mdays1+"~"+mdays2;
		    $("#mday").val(mdays);
		    var f = new FormData(document.getElementById('form1'));
	        $.ajax({
	            url: "courseinsert",
	            data: f,	          
	            type: "POST",
	            processData: false,
	            contentType: false,
	            success: function(data){
	                console.log(data);
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("코스등록입력완료");	
	                location.href='${path}/';
	            }
	        })
	}	
	
	function gogo1(){	
		  var f = new FormData(document.getElementById('form2'));
		  var len=$("input:checkbox:checked").length;
		  if(len==0){
			  alert("1개이상의 분야를 선택해주세요");
			  return false;
		  }
	        $.ajax({
	            url: "mentorinsert",
	            data: f,	          
	            type: "POST",
	            processData: false,
	            contentType: false,
	            success: function(data){
	                console.log(data);
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("멘토등록입력완료");	
	                location.href='${path}/';
	            }
	        })
	  /*   var mdays1=$("#mdays1").val();
	    var mdays2=$("#mdays2").val();
	    var mdays=mdays1+"~"+mdays2;
	    $("#mday").val(mdays);
	    var f = new FormData(document.getElementById('form1'));
        $.ajax({
            url: "courseinsert",
            data: f,	          
            type: "POST",
            processData: false,
            contentType: false,
            success: function(data){
                console.log(data);
                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                alert("코스등록입력완료");	
                location.href='/resume/';
            }
        }) */
    }	
	
	function mcourse(data){
		var html="<table align='center' class='table table-bordered'>";
		    html+="<tr align='center' class='info'>";
		    html+="<td>구분</td>";
		    html+="<td>과정명</td>";
		    html+="<td>교육기간</td>";
		    html+="<td>담임</td>";
		    html+="<td>TA</td>";
		    html+="<td>교육실</td>";
		    html+="<td>승인인원</td>";
		    html+="<td>시작인원(+)</td>";
		    html+="<td>현인원</td>";
		    html+="<td>중탈인원(-)</td>";
		    html+="<td>수료인원</td>";
		    html+="<td>취업인원</td>";
		    html+="<td>수료(%)</td>";
		    html+="<td>취업(%)</td>";
		    html+="<td>수정</td>";
		    html+="<td>삭제</td>";
		    html+="</tr>";
		    if(data["list"]!="not"){
		      $.each(data["list"], function(index, obj){
		    	   var total=parseInt(obj.msu)+parseInt(obj.misis); // 총인원
		    	    html+="<tr>";
		    	    if(obj.lcode==1){
		    	    	 html+="<td style='color:red'>혁신</td>";
		    	    }else if(obj.lcode==2){
		    	    	 html+="<td style='color:#F041EA'>4차</td>";
		    	    }else if(obj.lcode==3){
		    	    	 html+="<td style='color:green'>청취</td>";
		    	    }else if(obj.lcode==4){
		    	    	 html+="<td style='color:blue'>K-디지털</td>";
		    	    }			    	   
				    html+="<td><a href='${path}/list?lcode="+obj.lcode+"&mnum="+obj.mnum+"'>"+obj.mname+"</a></td>";
				    html+="<td id='mday"+obj.mnum+"'>"+obj.mday+"</td>";				    
				    html+="<td id='mmaster"+obj.mnum+"'>"+obj.mmaster+"</td>";
				    html+="<td id='mta"+obj.mnum+"'>"+obj.mta+"</td>";
				    html+="<td id='mlocation"+obj.mnum+"'>"+obj.mlocation+"</td>";
				    html+="<td style='text-align:center;color:red' id='mold"+obj.mnum+"'>"+obj.mold+"</td>";
				    html+="<td style='text-align:center' id='msu"+obj.mnum+"'>"+total+"</td>";
				    html+="<td style='text-align:center'>"+obj.msu+"</td>";
				    html+="<td id='misis"+obj.mnum+"' style='text-align:center'>"+obj.misis+"</td>";
				    html+="<td style='text-align:center;color:red'>"+obj.msu1+"</td>";
				    html+="<td style='text-align:center;color:red'>"+obj.msu2+"</td>";
				    html+="<td style='text-align:center'>"+obj.msu1per+"</td>";
				    html+="<td style='text-align:center'>"+obj.msu2per+"</td>";
				    html+="<td id='memupdateform"+obj.mnum+"'><input type='button' value='수정' class='btn btn-warning btn-xs' onclick='memupdateform(\""+obj.lcode+"\",\""+obj.mnum+"\")'></td>";
				    html+="<td><input type='button' value='삭제' class='btn btn-warning btn-xs' onclick='mdelete(\""+obj.lcode+"\",\""+obj.mnum+"\")'></td>";
				    html+="</tr>";
	  	      });
		      html+="</table>";
		      $("#mcourse").html(html);
		   }else{
			    html+="<tr>";
			    html+="<td colspan='16'>데이터가 없습니다</td>";
			    html+="</tr>";
			    html+="</table>";
		        $("#mcourse").html(html);
		 }		
	}
	function memupdateform(lcode, mnum){
		  // 교육기간, 담임,TA, 교육실, 승인인원, 교육인원
		  var mday=$("#mday"+mnum).text();
		  var mmaster=$("#mmaster"+mnum).text();
		  var mta=$("#mta"+mnum).text();
		  var mlocation=$("#mlocation"+mnum).text();
		  var mold=$("#mold"+mnum).text();
		  var msu=$("#msu"+mnum).text();
		  // misis
		  var misis=$("#misis"+mnum).text();
		  
		  $("#mday"+mnum).html("<input type='text' id='day"+mnum+"' value='"+mday+"'>");
		  $("#mmaster"+mnum).html("<input type='text' id='master"+mnum+"' value='"+mmaster+"' size='7'>");
		  $("#mta"+mnum).html("<input type='text' id='ta"+mnum+"' value='"+mta+"' size='7'>");
		  $("#mlocation"+mnum).html("<input type='text' id='location"+mnum+"' value='"+mlocation+"' size='7'>");
		  $("#mold"+mnum).html("<input type='text' id='old"+mnum+"' value='"+mold+"'' size='3'>");
		  $("#msu"+mnum).html("<input type='text' value='"+(msu-misis)+"' id='su"+mnum+"' size='3'>");
		  $("#memupdateform"+mnum).html("<input type='button' value='수정하기' class='btn btn-warning btn-xs' onclick='memupdate(\""+lcode+"\",\""+mnum+"\",\""+misis+"\")'>");
	}
	function memupdate(lcode, mnum, misis){
		  alert(lcode);
		  var mday=$("#day"+mnum).val();
		  var mmaster=$("#master"+mnum).val();
		  var mta=$("#ta"+mnum).val();
		  var mlocation=$("#location"+mnum).val();
		  var mold=$("#old"+mnum).val();
		  var msu=$("#su"+mnum).val()-misis;
		  
		  $.ajax({
			 url : "memupdate", 
			 type : "POST",
			 data : {"mnum": mnum, "mday": mday, "mmaster":mmaster, "mta" : mta, "mlocation": mlocation, "mold" : mold, "msu" : msu },
			 success : function(){
				 alert("수정성공");
				 $('#lcourse').val(lcode).trigger('change');
			 },
			 error : function(){ alert('error'); }			  
		  });
	}
	function mdelete(lcode, mnum){
		$.ajax({
			url : "mdelete",
			type : "GET",
			data : { "mnum" : mnum, "lcode" : lcode },
			success : function(){
				 alert("삭제성공");
				 // 확인?
				 $('#lcourse').val('0').trigger('change');
			},
			error : function(){ alert("error"); }
		});
	}
	function courseexcel(){
		//alert(num);
        $.ajax({
            url: "courseexcel",
            type: "GET",
            success: function(){
                //console.log(data);
                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                alert("엑셀에저장완료");
                //$("#s"+num).css("display","none");
                window.open("${path}/courseList","","width=500, height=300,left=400,top=300");
            }
        }) 
	}
	function dbname(){
		var mtname=$("#mtname").val();
		if(mtname==""){
			alert("이름을 입력하세요");
			return false;
		}
		$.ajax({
			url : "dbnamecheck",
			type : "POST",
			data : { "mtname" : mtname },
			success : function(data){
				if(data.cnt>0){
				     alert("중복되었습니다.");
				     $("#mtname").val("");
				}else{
				     alert("사용가능합니다.");
				}				
			},
			error : function(){ alert("error"); }
		});
	}
</script>
</head>
<body>
<div class="container" style="margin-top:5px">
<fieldset style="width: 99%; border-style: dotted;">
<div class="panel panel-default">
  <table style="width: 100%" class='table table-bordered'>
       <tr>
         <td style='vertical-align: middle;' align='center'><b>정부사업 사업선택</b></td>
         <td>
           <select style="width: 300px" id='lcourse'>             
           </select>                    
         </td>
       </tr>
  </table>
 </div> 
 <table style="width: 100%" class='table'>
   <tr>
   <td>
   <nav class='navbar navbar-inverse'>
   <div class='container-fluid'>
    <div class='navbar-header'>
    <a class='navbar-brand' href='${path}/'>SAF MENU▶</a>
     </div>
     <ul class='nav navbar-nav'>
     <li class='active'><a href="javascript:location.href='${path}/list?lcode=0&mnum=0'" id="btn2">교육운영관리</a></li>
     <li><a href="#" id="btn">교육과정등록</a></li>
     <li><a href="#" id="btn1">멘토등록</a></li>
     <li><a href="#" id="btn3">장비관리</a></li>
     </ul>
     </div>
     </nav>
     </td>
     </tr>
     <tr>
 <td>
  <div class="panel panel-default">
  <div class="panel-heading info" style="text-align: left;"><h4><span class="label label-success">과정리스트</span></h4></div>
   <div id="mcourse"></div>
   <table class='table table-bordered'>
     <tr>
       <td align="center"><input type="button" value="전체과정 엑셀에 저장" class='btn btn-primary btn-xs' onclick="courseexcel()"></td>
     </tr>
   </table>
   </div>
 </td>
 </tr>
   <tr>
  <td>
 <div id="courseview" style="display:none">
 <fieldset style="border-style: dotted;text-align: center;"> 
  <legend><span class="label label-success">교육과정등록</span></legend>
  <form id="form1" name="form1">
    <input type="hidden" name="mday" value="" id="mday">
    <table class='table table-bordered'>
      <tr>
        <td valign="middle" align="center"><img src='${path}/resources/image/course.jpg' width="250" height="170"></td>
        <td>
        <table  class='table'>
        <tr>
        <td>사업분류</td>
        <td>
           <div class='col-xs-8'><select id='lcode' name='lcode' class='selectpicker show-menu-arrow' data-style="btn-success">  
               <option value='1'>혁신성장인재양성</option>
               <option value='2'>4차산업혁명인력양성</option>  
               <option value='3'>청년취업아카데미</option>
               <option value='4'>디지털 핵심 실무인재 양성사업</option>
           </select></div>                    
         </td>         
      </tr>
      <tr>
        <td>과정명</td>
        <td><div class='col-xs-8'><input type='text' name='mname' size='80' class='form-control input-sm'></div></td>        
      </tr>
       <tr>
        <td>교육기간</td>
        <td><div class='col-xs-3'><input type='date' id="mdays1" name='mdays1' class='form-control input-sm'></div><div class='col-xs-3'><input type='date' class='form-control input-sm' name='mdays2' id="mdays2"></div></td>        
      </tr>
      <tr>
        <td>승인인원</td>
        <td><div class='col-xs-2'><input type='text' name='mold' size='5' class='form-control input-sm'></div></td>        
      </tr>
      <tr>
        <td>시작인원(+)</td>
        <td><div class='col-xs-2'><input type='text' name='msu' size='5' class='form-control input-sm'></div></td>        
      </tr> 
      <tr>
        <td>담임교사</td>
        <td><div class='col-xs-4'><input type='text' name='mmaster' class='form-control input-sm'></div></td>        
      </tr> 
      <tr>
        <td>TA</td>
        <td><div class='col-xs-4'><input type='text' name='mta' class='form-control input-sm'></div></td>        
      </tr> 
       <tr>
      <td>교육실</td>
        <td><div class='col-xs-4'><input type='text' name='mlocation' class='form-control input-sm'></div></td>        
      </tr>     
      <tr>
        <td align='center' colspan="2"><input type='button' class='btn btn-primary btn-xs' value='교육등록' onclick='gogo()'>&nbsp;<input type='button' class='btn btn-danger btn-xs' value='취소'></td>
      </tr>
    </table>
    </td>
    </tr>
    </table>
 </form>
 </fieldset>
</div> 
</td>
</tr>

<tr>
<td>
<div id="memtorinsert" style="display:none">
<fieldset style="border-style: dotted;text-align: center;"> 
 <legend style="font-size: 11pt; background-color: yellow;">PROJECT MENTOR(POOL)</legend>
  <form id="form2" name="form2">
  <table class='table'>
    <tr>
      <td valign="middle" align="center"><img src='${path}/resources/image/mt.png' width="230" height="150"></td>
      <td>
         <table class='table'>
      <tr>
        <td>멘토이름</td>
        <td>
         <input type='text' name='mtname' id="mtname" size='10'>
         <input type='button' value='중복확인' onclick="dbname()">
        </td>        
      </tr>
       <tr>
        <td>멘토소속(기업)</td>
        <td><input type='text' name='mtsosk' size='50'></td>        
      </tr>
      <tr>
        <td>멘토전화번호</td>
        <td><input type='text' name='mttel' size='20'>(-없이)</td>        
      </tr>
      <tr>
        <td>멘토이메일</td>
        <td><input type='text' name='mtemail' size='30' value="@"></td>        
      </tr> 
      <tr>
        <td>멘토전공</td>
        <td><input type='text' name='mtmajor' size='50'></td>        
      </tr> 
      <tr>
        <td>멘토가능분야</td>
        <td>
         <input type="checkbox" name='mtpart' value="AI">AI
         <input type="checkbox" name='mtpart' value="BigData">BigData
         <input type="checkbox" name='mtpart' value="IoT">IoT
         <input type="checkbox" name='mtpart' value="인터넷⋅서비스 SW">인터넷⋅서비스 SW
         <input type="checkbox" name='mtpart' value="스마트 홈⋅단말">스마트 홈⋅단말
         <input type="checkbox" name='mtpart' value="가상‧증강현실 /헬스케어">가상‧증강현실 / 헬스케어
        </td>        
      </tr> 
       <tr>
      <td>멘토등급</td>
        <td><input type='text' name='mtlevel'>(기본등급D, A~D)</td>        
      </tr>     
      <tr>
        <td colspan="2"><input type='button' value='멘토등록' onclick='gogo1()'>&nbsp;<input type='button' value='취소'></td>
      </tr>
    </table>
      </td>
    </tr> 
  </table>
</form>
</fieldset>
</div>
</td>
</tr>
</table>  
	<div class="panel panel-default" style="text-align: center;">
	<div class="panel-footer">
      스마트훈련관리시스템<br>
	스마트 아카이브 공장(SAF_Smart Archive Factory) | 개발자 : 박매일 | 이메일: bitcocom@empas.com<br>
	©SAFLAB. ALL RIGHTS RESERVED(저작권등록:제C-2021-005791)
	</div>
	</div>
</fieldset>
</div>
</body>
</html>
