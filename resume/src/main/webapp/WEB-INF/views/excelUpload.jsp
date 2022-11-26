<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />    
<html>
<head>
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

<script type="text/javascript">
    $(document).ready(function(){
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
           // alert(this.value);
            $.ajax({
             url: "mcourselist",
             data: {"mnum" : this.value},
             type: "POST",
             success: mlist
             })
         });     
      /*  
        if(${lcode}!=''){ 
       	  $('#lcourse').val('${lcode}').trigger('change');    			
		  $('#mcourse').val('${mnum}').trigger('change');
	    }    */
    });    
    function mlist(data){
    	   //data=JSON.stringify(data);
    	$('#mcourse').val(data["list"][0].mnum).prop("selected", true);
		var html="";
		html+="<table class='table table-bordered'>";
		html+="<tr align='center' class='info'>";
		html+="<td>과정명</td>";
		html+="<td>교육기간</td>";
		html+="<td>승인인원</td>";
		html+="<td>교육인원(현인원)</td>";
		html+="<td>중탈인원</td>";
		html+="<td>담임</td>";
		html+="<td>TA</td>";
		html+="<td>교육실</td>";        
		html+="<td>교육생등록여부</td>";
		//html+="<td>상담카트만들기</td>";
		html+="<td>교육생리스트보기</td>";
		html+="</tr>";		
		$.each(data["list"], function(index, obj){
			var total=parseInt(obj.msu)+parseInt(obj.misis); // 총인원
			html+="<tr align='center'>";
			html+="<td>"+obj.mname+"</td>";
			html+="<td>"+obj.mday+"</td>";
			html+="<td>"+obj.mold+"</td>";
			html+="<td>"+total+"<b><font color='red'>("+obj.msu+")</font></b></td>";
			html+="<td>"+obj.misis+"</td>";
			html+="<td>"+obj.mmaster+"</td>";
			html+="<td>"+obj.mta+"</td>";
			html+="<td>"+obj.mlocation+"</td>";     
			if(obj.isdata=='NO'){
				html+="<td><input type='button' value='훈련생등록' class='btn btn-success btn-xs' onclick='dataform(\""+obj.lcode+"\",\""+obj.mnum+"\")'/></td>";
			}else{
				html+="<td>교육생등록완료 <input type='button' value='미등록상태로돌리기' class='btn btn-success btn-xs' onclick='datadelete(\""+obj.lcode+"\",\""+obj.mnum+"\")'/></td>";
			}
		/* 	if(obj.ismeet=='NO'){
				html+="<td><input type='button' value='상담카드만들기' onclick='doExcelDownloadProcess(\""+obj.lcode+"\",\""+obj.mnum+"\")'/></td>";
			}else{
				html+="<td>상담카드만들기완료 <input type='button' value='미등록상태로돌리기' onclick='sangdamno(\""+obj.lcode+"\",\""+obj.mnum+"\")'/></td>";
			} */
			if(obj.isdata!='NO'){
			    html+="<td><input type='button' id='stview' value='교육생보기' class='btn btn-success btn-xs' onclick='dataLoad(\""+obj.lcode+"\",\""+obj.mnum+"\",\""+obj.mold+"\")'/></td>";
			}else{
				html+="<td>등록된교육생이 없습니다.</td>";
			}
			html+="</tr>";	
		});
		html+="</table>";
		$("#mlist").html(html);  
		$("#form").css("display", "none");
		dataLoad(data["list"][0].lcode,data["list"][0].mnum, data["list"][0].mold);
	}
   /*  function sangdamno(lcode, mnum){
  	  $.ajax({
	            url: "sangdamno",
	            data : { "lcode" : lcode, "mnum" : mnum},
	            type: "POST",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("미등록상태로전환");
	                location.href='/resume/upload';                
	            }
	        })
  } */
    
    function datadelete(lcode, mnum){
	      
    	  $.ajax({
	            url: "datadelete",
	            data : { "lcode" : lcode, "mnum" : mnum},
	            type: "POST",
	            success: function(data){
	                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
	                alert("미등록상태로전환");
	                location.href='${path}/list';                
	            }
	        })
    }
    
    function dataform(lcode, mnum){
    	alert(lcode+":"+mnum);
    	$("#form").css("display", "block");
    	var frm="<form id='form1' name='form1' method='post' enctype='multipart/form-data'>";
    	frm+="<input type='hidden' id='lcode' name='lcode' value='"+lcode+"'>";
	    frm+="<input type='hidden' id='mnum' name='mnum' value='"+mnum+"'>";
    	frm+="<table class='table table-bordered'>";
    	frm+="<tr>";
    	frm+="<td align='center'>";
    	frm+="<input type='file' id='fileInput' name='fileInput'>";
    	frm+="</td>";
    	frm+="<td>";
    	frm+="<input type='button' onclick='doExcelUploadProcess()' value='◀훈련생 엑셀파일 업로드(DB저장)' class='btn btn-primary btn-xs'/>";
    	frm+="</td>";
    	frm+="<td style='width:600px'>";
    	frm+="<input type='button' onclick='maingo()' class='btn btn-primary btn-xs' value='전체과정으로'/>";
    	frm+="</td>";
    	frm+="</tr>";
    	frm+="</table>";
	    frm+="</form>";	
	    $("#form").html(frm);    	
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
	
    function doExcelUploadProcess(){
        var f = new FormData(document.getElementById('form1'));
        alert($('#lcode').val());
        $.ajax({
            url: "uploadExcelFile",
            data: f,
            processData: false,
            contentType: false,
            type: "POST",
            success: function(data){
                console.log(data);
                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                alert("데이터베이스입력완료");
                location.href='${path}/list';                
            }
        }) 
    }
    
    /* function doExcelDownloadProcess(lcode, mnum){       
        $.ajax({
            url: "cardFile",
            data : {"lcode": lcode, "mnum" : mnum},
            type: "POST",
            success: function(data){
                console.log(data);
                //document.getElementById('result').innerHTML ="카드생성완료";
                alert("상담카드생성완료");
                location.href='/resume/upload';  
            }
        })
    } */
    function dataLoad(lcode, mnum, mold){
    	 $.ajax({
             url: "studentLoad",
             data : {"lcode": lcode, "mnum" : mnum},
             type: "GET",
             success: function(data){ 
            	 dataList(data, mold);
           	 }
         })
    }
    function dataList(data, mold){
        //console.log(data["list"]);
        //document.getElementById('result').innerHTML =JSON.stringify(data["list"]);
        //data["list"]
        //alert(data["list"].length);
        var state1=0;
        var state2=0;
        var state1per=0;
        var state2per=0;
        var html="";
     
        if(data["list"].length==0){        
        	 html+="<table>";
             html+="<tr>";
     		 html+="<td colspan='13'><b>등록된 훈련생 데이터가 없습니다.</b></td>";
     		 html+="</tr>";
        }else{        	
        	 $.each(data["list"], function(index, obj){
        		 if(obj.state1=="YES"){
        			 state1++; //수료자수
        		 }
        		 if(obj.state2=="YES"){
        			 state2++; //취업자수
        		 }
        	 });
        state1per=state1 / mold * 100;
        state2per=state2 / state1 * 100;        	 
        
        html="";
        html+="<table class='table' align='center'>";
        html+="<tr>";
        html+="<td colspan='14' align='center'>";
        html+="<nav class='navbar navbar-inverse'>";
        html+="<div class='container-fluid'>";
        html+="<div class='navbar-header'>";
        html+="<a class='navbar-brand' href='${path}/'>SAF MENU▶</a>";
        html+="</div>";
        html+="<ul class='nav navbar-nav'  style='color:red;'>";
        html+="<li class='active'><a href='javascript:career2(\""+data["list"][0].lcode+"\",\""+data["list"][0].mnum+"\")'>시간표훈련일지</a></li>";
        html+="<li><a href='javascript:career1(\""+data["list"][0].lcode+"\",\""+data["list"][0].mnum+"\")'>이력서 관리</a></li>";
        html+="<li><a href='javascript:career(\""+data["list"][0].lcode+"\",\""+data["list"][0].mnum+"\")'>교육생취업카드</a></li>";
        html+="<li><a href='javascript:teammatch(\""+data["list"][0].lcode+"\",\""+data["list"][0].mnum+"\")'>팀배정하기</a></li>";
        html+="<li><a href='javascript:makeImgList(\""+data["list"][0].lcode+"\",\""+data["list"][0].mnum+"\")'>사진출석부만들기(A4)</a></li>";
        html+="<li><a href='javascript:maingo()'>전체과정으로</a></li>";        
        html+="</ul>";
        html+="</div>";
        html+="</nav>";
        html+="</td>";
        html+="<tr>";

        html+="<tr style='vertical-align: middle;'>";
		html+="<td colspan='9'><h4><span class='label label-success'>훈련생리스트("+data["list"].length+")</span></h4></td>";
		html+="<td><input type='button' class='btn btn-warning btn-xs' readonly value='수료:"+state1+"'></td>";
		html+="<td><input type='button' class='btn btn-warning btn-xs' readonly value='취업:"+state2+"'></td>";
		html+="<td colspan='2' align='center'><b>수료율:"+parseFloat(state1per.toFixed(2))+" / 취업률:"+parseFloat(state2per.toFixed(2))+"</b></td>";
		html+="</tr>";	
        html+="<tr align='center' class='info'>";
        html+="<td>중탈</td>";
        html+="<td>사진</td>";
        html+="<td>이름</td>";
        /* html+="<td>주민번호</td>"; */
        html+="<td>전화번호</td>";
        html+="<td>이메일</td>";
        html+="<td>성별</td>";
        html+="<td>생년월일</td>";
        html+="<td>학교</td>";
        html+="<td>전공</td>";
        html+="<td>수료</td>";
        html+="<td>취업</td>";
        html+="<td>사진</td>";
        html+="<td>상담카드</td>";
        html+="</tr>";
        $.each(data["list"], function(index, obj){
        	  html+="<tr align='center'>";
        	  if(obj.isis=="0"){
        	     html+="<td><input type='checkbox' name='isis' id='isis"+obj.num+"' onclick='isis("+obj.num+",\""+obj.lcode+"\",\""+mold+"\",\""+obj.mnum+"\")' disabled></td>";
        	  }else{
        		 html+="<td><input type='checkbox' name='isis' id='isis"+obj.num+"' onclick='isis("+obj.num+",\""+obj.lcode+"\",\""+mold+"\",\""+obj.mnum+"\")'></td>"; 
        	  }
        	  html+="<td><img src='${path}/resources/"+obj.image+"' width='60px' height='60px'></td>";
          	  if(obj.isis=="0"){
          		   html+="<td><p style='text-decoration:line-through;color:red'>"+obj.name+"</p>(중탈)</td>";
        	  }else{
          	       html+="<td>"+obj.name+"</td>";
        	  }
              /* html+="<td>"+obj.ssn+"</td>"; */
              html+="<td>"+obj.tel+"</td>";
              html+="<td>"+obj.email+"</td>";
              html+="<td>"+obj.sex+"</td>";
              html+="<td>"+obj.birth+"</td>";
              html+="<td>"+obj.college+"</td>";
              html+="<td>"+obj.major+"</td>";
              if(obj.state1=="YES") {
            	  html+="<td><input type='button' class='btn btn-info btn-xs' value="+obj.state1+" onclick='state1("+obj.num+",\""+obj.lcode+"\",\""+obj.state1+"\",\""+obj.mnum+"\",\""+mold+"\")'></td>";
              }else{
            	  html+="<td><input type='button' value="+obj.state1+" class='btn btn-info btn-xs' onclick='state1("+obj.num+",\""+obj.lcode+"\",\""+obj.state1+"\",\""+obj.mnum+"\",\""+mold+"\")'></td>";  
              }
              if(obj.state2=="YES") {
                 html+="<td><input type='button' class='btn btn-info btn-xs' value="+obj.state2+" onclick='state2("+obj.num+",\""+obj.lcode+"\",\""+obj.state2+"\",\""+obj.mnum+"\",\""+mold+"\")'></td>";
              }else{
                 html+="<td><input type='button' class='btn btn-info btn-xs' value="+obj.state2+" onclick='state2("+obj.num+",\""+obj.lcode+"\",\""+obj.state2+"\",\""+obj.mnum+"\",\""+mold+"\")'></td>";
              }
              
              if(obj.image=="NO"){
            	  html+="<td><input type='button' value="+obj.image+" class='btn btn-primary btn-xs' onclick='imagein("+obj.num+",\""+obj.lcode+"\",\""+obj.name+"\",\""+obj.mnum+"\")'></td>";  
              }else{
            	  html+="<td><input type='button' value='등록됨' class='btn btn-success btn-xs' onclick='imagein("+obj.num+",\""+obj.lcode+"\",\""+obj.name+"\",\""+obj.mnum+"\")'></td>";
              }
              
              html+="<td><input type='button' id='sangclick"+obj.num+"' class='btn btn-primary btn-xs' value='상담내역("+obj.cnt+")' onclick='cardMake("+obj.num+",\""+obj.lcode+"\",\""+obj.mnum+"\",\""+obj.cert+"\")'/></td>";                       
              html+="</tr>";
        
              html+="<tr>";
              html+="<td colspan='13'>";
              html+="<div id='s"+obj.num+"' style='display:none;'>";              
              html+="</div>";
              html+="</td>";
              html+="</tr>";
          });
         }
         html+="</table>"; 
         document.getElementById('result').innerHTML=html;
    }
    
    function maingo(){
    	location.href="${path}/main";
    }
    function teammatch(lcode, mnum){
    	location.href="${path}/team?lcode="+lcode+"&mnum="+mnum;
    }
    
    
    function career(lcode, mnum){
    	location.href="${path}/career?lcode="+lcode+"&mnum="+mnum;
    }
      
    function career1(lcode, mnum){
    	location.href="${path}/career1?lcode="+lcode+"&mnum="+mnum;
    }
    
    function career2(lcode, mnum){
    	location.href="${path}/career2?lcode="+lcode+"&mnum="+mnum;
    }
    
    function isis(num, lcode, mold, mnum){
    	if($('#isis'+num).is(":checked") == true){    	
    	   $.ajax({
    		   url : "isisdata",
    		   type : "GET",
    		   data : { "num" : num, "mnum" : mnum},
    		   success : function() {
    			   alert("중도탈락처리완료");
    			   //dataLoad(lcode, mnum, mold);
    			   $('#lcourse').val(lcode).trigger('change');    			
    			   $('#mcourse').val(mnum).trigger('change');
    			  
    		   },
    		   error : function(){ alert("error"); }
    	   });
    	   
    	}else{
    		alert("선택이 않되었습니다.");
    	}
    }
    function makeImgList(lcode, mnum){
    	//alert(num);
        $.ajax({
            url: "makeImgList",
            data: {"lcode" : lcode, "mnum" : mnum },
            type: "POST",
            success: function(data){
                //console.log(data);
                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                alert("사진List작성완료");
                //$("#s"+num).css("display","none");
                window.open("${path}/imageList?lcode="+lcode+"&mnum="+mnum,"","width=500, height=300,left=400,top=300");
            }
        }) 
    }
    
    function imagein(num, lcode, name, mnum){
    	window.open("${path}/imagein?num="+num+"&lcode="+lcode+"&mnum="+mnum+"&name="+name,"","width=500, height=300,left=400,top=300");
    }
    function state1(num, lcode, state1, mnum, mold){
    	alert(state1);
    	var state="";
    	if(state1=="NO"){
    		 state="YES";
    	 }
    	 if(state1=="YES"){
    		 state="NO";
    	 }
    	 
    	   $.ajax({
               url: "state1update",
               data : {"num": num, "state1":state, "mnum" : mnum},
               type: "POST",
               success: function(data){
                   console.log(data);
                   //document.getElementById('result').innerHTML ="카드생성완료";
                   //location.href='/resume/upload';
                   dataLoad(lcode, mnum, mold);
               }
           }) 	
    }
    function state2(num, lcode, state2, mnum, mold){
    	alert(num);
    	var state="";
    	if(state2=="NO"){
    		 state="YES";
    	 }
    	 if(state2=="YES"){
    		 state="NO";
    	 }
    	 
    	   $.ajax({
               url: "state2update",
               data : {"num": num, "state2":state, "mnum" : mnum},
               type: "POST",
               dataType : "json",
               success: function(data){
                   console.log(data); 
                   if(data.save=="nosave"){
                       alert("취업카드에 취업정보를 입력하세요");
                   }
                   //document.getElementById('result').innerHTML ="카드생성완료";
                   //location.href='/resume/upload';
                   dataLoad(lcode, mnum, mold);
               }
           }) 	
    }
    function sangform(num){
    	//alert("OK")
    	var num=$("#num"+num).val();
    	var lcode=$("#lcode"+num).val();
    	var mnum=$("#mnum"+num).val();
    	var scontent=$("#scontent"+num).val();
    	var scert=$("#scert"+num).val();
    	var sname=$("#sname"+num).val();
    	if(scontent=="" || sname==""){
    		alert("누락된 정보가 있습니다.");
    		return false;
    	}
    	//alert(num+","+lcode+","+mnum+","+scontent+","+sname);
    	//var f = new FormData(document.getElementById('sangform'));
          $.ajax({
              url: "sanginsert",
              data: { "num" : num , "lcode" : lcode, "mnum" : mnum, "scontent" : scontent, "sname" : sname, "cert" : scert},
              type: "POST",
              success: function(data){
                  console.log(data);
                  //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                  alert("상담카드작성완료");
                  //$("#s"+num).css("display","none"); 
                  $("#sangclick"+num).trigger("click");  
              }
          })    	
    }    
    
    function certInsert(num){
    	//alert("OK")    	
    	var cert=$("#cert"+num).val();
    	var num=$("#num"+num).val();
    	alert(cert+","+num);
    	if(cert==""){
    		alert("누락된 정보가 있습니다.");
    		return false;
    	}
    	//alert(num+","+lcode+","+mnum+","+scontent+","+sname);
    	//var f = new FormData(document.getElementById('sangform'));
          $.ajax({
              url: "certInsert",
              data: {"num" : num , "cert" : cert},
              type: "POST",
              success: function(data){
                  console.log(data);
                  //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                  alert("자격증업데이트완료");
                  //$("#s"+num).css("display","none"); 
                  //$("#sangclick"+num).trigger("click");
                  $("#stview").trigger('click');
              }
          })    	
    }    
    
    function cardMake(num, lcode, mnum, cert){
      	//var html="";
        var shtml="";
        shtml+="<table class='table table-bordered'>";
        shtml+="<div class='form-group'>";
        shtml+="<form  method='post' id='sangform'>";
        shtml+="<input type='hidden' id='num"+num+"' value='"+num+"'>";
        shtml+="<input type='hidden' id='lcode"+num+"' value='"+lcode+"'>";
        shtml+="<input type='hidden' id='mnum"+num+"' value='"+mnum+"'>";        
        shtml+="<tr>";
        shtml+="<td colspan='5'><h4><span class='label label-success'>상담내역</span></h4></td>";
        shtml+="</tr>";
        shtml+="<tr>";
        shtml+="<td align='center'>자격증이름</td>";
        shtml+="<td><div class='col-xs-6'><input type='text' id='cert"+num+"' class='form-control input-sm'/></div>※자격증 취득(콤마로 구분)</td>";
        shtml+="<td align='center' colspan='2'>"+cert+"</div></td>";
        shtml+="<td align='center'><input type='button' value='최종자격증등록' class='btn btn-primary btn-xs' onclick='certInsert(\""+num+"\")'/></td>";
        shtml+="</tr>";
        shtml+="<tr bgColor='blue' align='center' style='background-color: #bbdefb'>";
        shtml+="<td>상담일자</td>";
        shtml+="<td>상담내용</td>";
        shtml+="<td>상담자</td>";
        shtml+="<td>자격현황</td>";
        shtml+="<td>삭제</td>";
        shtml+="</tr>";
    	// 상담내역 가져오기
    	var cnt=0;
        $.ajax({
         url: "sangdam",
         data : {"num": num},
         type: "POST",
         success: function(data){
              console.log(data.list);
              if(data.list=="not"){
            	  cnt=0;
            	  shtml+="<tr>";
                  shtml+="<td colspan='5'>상담내역이 없습니다.</td>";                
                  shtml+="</tr>";
              }else{
            	 cnt=data["list"].length;
                 $.each(data["list"], function(index, obj){
            	    shtml+="<tr>";
                    shtml+="<td align='center'>"+obj.sdate+"</td>";
                    shtml+="<td>"+obj.scontent.replaceAll("\n","<br style='mso-data-placement:same-cell;'>")+"</td>";
                    shtml+="<td align='center'>"+obj.sname+"</td>";
                    shtml+="<td>"+obj.cert+"</td>";
                    shtml+="<td align='center'><input type='button' class='btn btn-warning btn-xs' onclick='sangdeletenum("+obj.num+","+obj.snum+")' value='삭제'></td>";
                    shtml+="</tr>";
               });
              }
              shtml+="<tr>";
              shtml+="<td align='center'>상담내용</td>";
              shtml+="<td colspan='2'><textArea rows='6' cols='50' id='scontent"+num+"' class='form-control'></textArea></td>";
              shtml+="<td colspan='2' align='center'>&nbsp;</td>";
              shtml+="</tr>";
              shtml+="<tr>";
              shtml+="<td align='center'>자격현황</td>";
              shtml+="<td colspan='4'><div class='col-xs-8'><input type='text' id='scert"+num+"' class='form-control input-sm'></div>※자격증 취득 상황(필수사항은아님)</td>";
              shtml+="</tr>";
              shtml+="<tr>";
              shtml+="<td align='center'>상담자</td>";
              shtml+="<td colspan='4'><div class='col-xs-3'><input type='text' class='form-control input-sm' id='sname"+num+"'></div></td>";
              shtml+="</tr>";
              shtml+="<tr>";
              shtml+="<td colspan='5' align='center' valign='middle'><input type='button' onclick='sangform(\""+num+"\")' class='btn btn-primary btn-xs' value='작성하기'/>&nbsp;<input type='reset' class='btn btn-warning btn-xs' value='취소'/>&nbsp;<input type='button' value='닫기(X)' class='btn btn-danger btn-xs' onclick='cardClose(\""+num+"\")'/>";
              if(data.list!="not"){
                shtml+="&nbsp;<input type='button' value='상담카드에 저장' class='btn btn-success btn-xs' onclick='sangdamsave("+num+",\""+lcode+"\",\""+mnum+"\")'/>";
                shtml+="▶<input type='button' value='상담카드다운로드' class='btn btn-success btn-xs' onclick='sangdamdown("+num+",\""+lcode+"\",\""+mnum+"\")'/></td>";             
              }else{
            	  shtml+="</td>";  
              }
              shtml+="</tr>";
              shtml+="</table>"; 
              shtml+="</form>";
              shtml+="</div>";
              $("#s"+num).html(shtml);   
              $("#s"+num).css("display","block"); 
              $("#sangclick"+num).val("상담내역("+cnt+")");
             }
        });        
    }   
    function sangdeletenum(num, snum){
    	alert(num+":"+snum);
    	 $.ajax({
             url: "sangdeletenum",
             data: { "num" : num, "snum" : snum },
             type: "POST",
             success: function(data){
                 //console.log(data);
                 //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                 alert("개인상담삭제");
                 //$("#s"+num).css("display","none");   
                 $("#sangclick"+num).trigger("click");  
             }
         })
    }
    function sangdamsave(num, lcode, mnum){
    	//alert(num);
        $.ajax({
            url: "sangdamsave",
            data: { "num" : num, "lcode" : lcode, "mnum" : mnum },
            type: "POST",
            success: function(data){
                //console.log(data);
                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                alert("개인상담카드작성완료");
                //$("#s"+num).css("display","none");   
                $("#sangclick"+num).trigger("click");  
            }
        })    	
    	
    }
    
    function sangdamdown(num, lcode, mnum){
    	window.open("${path}/download?num="+num+"&lcode="+lcode+"&mnum="+mnum,"","width=500, height=300,left=400,top=300");
    	
    }
    function cardClose(num){
    	//alert(tel);
    	$("#s"+num).css("display","none");
    }
    function calcAge(birth) {
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
</script>
</head>
<body>
<div class="container" style="margin-top:5px">
<fieldset style="width: 100%; border-style: dotted;">
<div class="panel panel-default">
    <table class='table table-hover'>
       <tr>
         <td style='vertical-align: middle;' align='center'><b>정부사업 사업선택</b></td>
         <td>
           <select id='lcourse'>             
           </select>                    
         </td>
         <td>
           <select id='mcourse' style='width:350px;'>             
           </select>                    
         </td>
         <td>&nbsp;</td>
         <%-- <td align="center"><b><input type='button' id="btn" value='메인으로' class='btn btn-success btn-xs' onclick="location.href='${path}/main'"></b></td> --%>
       </tr>
       <tr>
       <td colspan='4'>
        <div id="mlist"></div> 
        <div id="form" style="display: none"></div>
         <div id="result"></div>
       </td>
       </tr>
    </table>
  </div>   
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
