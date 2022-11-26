<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html>
<head>
<title>SAF(스마트아카이브공장)</title>
<meta charset="EUC-KR">
  <meta name="robots" content="noindex, nofollow" />
  <meta name="googlebot" content="noindex, nofollow" />
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
 
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
<style type="text/css">
	 

	 body{
	      font-size:12px;
	 }
	 .left{
	    width: 120px;
	    background: #c7defa;
	    border: 2px solid #7da4fa;
	    float: left;
	    min-height: 600px;
	}
	a {
	     background-color: transparent;
	     text-decoration: none;
	   }
	
	li{
	   font-weight:bold;
	}
	
	h2{
	    margin: 5px 0 2px 0;
	    font-size: 1.2em;
	}
	#cart0{
	    min-height: 600px;
	    width: 118px;
	    border: 1px solid red;
	}
	#cart0.hover{
	    border: 1px solid red;
	}
	#cart1{
	    min-height: 600px;
	    width: 118px;
	    border: 1px solid blue;
	}
	#cart1.hover{
	    border: 1px solid red;
	}
	#cart2{
	    min-height: 600px;
	    width: 118px;
	    border: 1px solid green;
	}
	#cart2.hover{
	    border: 1px solid red;
	}
	#cart3{
	    min-height: 600px;
	    width: 118x;
	    border: 1px solid #AA65F6;
	}
	#cart3.hover{
	    border: 1px solid red;
	}
	
	#cart4{
	    min-height: 600px;
	    width: 118x;
	    border: 1px solid #ED6F58;
	}
	#cart4.hover{
	    border: 1px solid red;
	}
	
	#cart5{
	    min-height: 600px;
	    width: 118x;
	    border: 1px solid #22ABEF;
	}
	#cart5.hover{
	    border: 1px solid red;
	}
	
	table.listing,
	.drag-cart-item table{
	    width: 500px;
	}
	table.listing, table.listing td, table.listing th ,
	.drag-cart-item table, .drag-cart-item td{
	    border-collapse: collapse;
	    border: 1px solid #cacac8;
	    padding: 5px;
	    
	}
	.ln{
	   text-decoration:line-through;
	}
	table td.name{
	    cursor: move;
	}
	table th{
	    background:#E6E6E6;
	}
	.drag-cart-item{
	    background: #FFF;
	}
    input.form-text {
        border: 1px solid #bcbcbc;
        height: 21px;
   }
    
	</style>
 <script type='text/javascript'>//<![CDATA[
 var count=0;
 $(document).ready(function(){

	teamAllDelete();	  
	
 });//]]> 

 function teamAllDelete(){
	  $.ajax({
		  url : "teamAllDelete",
		  type : "POST",
		  data : {"lcode": '${lcode}', "mnum" : '${mnum}' },
		  success : function(data){
			  //alert("팀 배정 초기화");
			  console.log("팀 배정 초기화");
		  },
		  error : function(){ alert("error"); }
	  });
 }

 function total(tesmsu){
	 var sum=0;
	 for(var i=0;i<tesmsu;i++){
		 //var tot=parseInt(c0.split(":")[1])+parseInt(c1.split(":")[1])+parseInt(c2.split(":")[1])+parseInt(c3.split(":")[1]+parseInt(c4.split(":")[1])+parseInt(c5.split(":")[1]));
		 sum+=parseInt($('#count'+i).html().split(":")[1]);
	 }		
		$('#tot').html("총배정인원:"+sum);
  }
	
  function fnDel(i, classid, tesmsu){
	   //alert(i+":"+classid+":"+tesmsu);
	   var iclassid=i+""+classid;
	   $('#'+iclassid).remove();
	   count=$('#cart'+i+' .selected').find('li').length;
	   $('#count'+i).html("인원수:"+count);
	   $('#'+classid).css("backgroundColor",""); 
	   total(tesmsu);
	   var num=classid.split("-")[1];
	   
	   $.ajax({
			  url : "teamDelete",
			  type : "POST",
			  data : {"num": num },
			  success : function(data){
				  alert("팀원 삭제완료");
			  },
			  error : function(){ alert("error"); }
	  });	
 }
	 
 function teammake(){
		
		 var tesmsu=$("#teamsu").val();
		 if(tesmsu>6 || tesmsu==0){
			 alert("팀은 2팀 이상~6팀 이하");
			 return false;
		 }
		 teamAllDelete();		 
		 
		 var html="";
		 html+="<table class='table'>";
		 html+="<tr>";
	 	 html+="<td colspan='7'><div id='tot'>총배정인원:0</div>[팀 배정 프로그램(오른쪽에있는 이름을 드래그하여 팀에 놓는다/ 삭제시 클릭하여 삭제한다)]</td>";
		 html+="</tr>";
		 html+="<tr style='vertical-align: top;'>";
		 for(var i=0;i<tesmsu;i++){
			 html+="<td>";
				 html+="<div class='left'>";
				 html+="<h2>"+(i+1)+"팀</h2>"; 
				 html+="<div id='cart"+i+"'>"; 
				 html+="<ol class='selected'>"; 
				 html+="</ol>"; 
				 html+="</div>"; 
				 html+="<br/>";
				 html+="<div id='count"+i+"'>인원수:0</div>";
				 html+="</div>"; 
			 html+="</td>";			 
		 }
		 
		     html+="<td>";   
				 html+="<table class='table table-hover'>"; 
				 html+="<thead>"; 
				 html+="<tr>"; 
				 html+="<th colspan='3'>${mname}</th>";
				 html+="</tr>"; 
				 html+="<tr>";
				 html+="<th>번호</th><th>이름</th><th>생년월일</th>";
				 html+=" </tr>"; 
				 html+="</thead>"; 
				 html+="<tbody>"; 
		         
				 html+="<c:forEach var='vo' items='${list}'>";
				 html+="<c:if test='${vo.isis==1}'>";
				 html+="<tr>";           
				 html+="<td>${vo.num}</td>";
				 html+="<td id='classid-${vo.num}' class='name'>${vo.name}</td>"; 
				 html+="<td>${vo.birth}</td>";
				 html+="</tr>";
				 html+="</c:if>";
				 html+="<c:if test='${vo.isis==0}'>";
				 html+="<tr>";           
				 html+="<td>${vo.num}</td>";
				 html+="<td id='classid-${vo.num}' style='text-decoration: line-through;color:red'>${vo.name}(중탈)</td>"; 
				 html+="<td>${vo.birth}</td>";
				 html+="</tr>";
				 html+="</c:if>";
				 html+="</c:forEach>";
				 
				 html+="</tbody>"; 
				 html+="</table>";
		     html+="</td>";     
		     html+="</tr>";
		     html+="</table>";
		 //$("#cartlist").empty();
		 $("#cartlist").html(html); 	
		 
		 $("#cart"+0).droppable({
			    scope: 'cart-item',
			    activeClass: 'active',
			    hoverClass: 'hover',
			    tolerance: 'pointer',
			    drop: function(event, ui) {
			        var classid = ui.helper.find('td').attr('id');
			        //alert(classid);
			        $('#'+classid).css("backgroundColor","red"); 
			        var name = ui.helper.find('.name').html();
			        $('#cart0 .selected').append('<li id="0' + classid + '"><a href="javascript:fnDel(\''+0+'\',\''+classid+'\',\''+tesmsu+'\')"><font color=\'red\'>' + name + '</font></a></li>');
			        count=$('#cart0 .selected').find('li').length;
			        $('#count0').html("인원수:"+count);
					total(tesmsu);
					teamInsert(classid, 1);	
					
				 }
		 });
		 
 		 $("#cart"+1).droppable({
				    scope: 'cart-item',
				    activeClass: 'active',
				    hoverClass: 'hover',
				    tolerance: 'pointer',
				    drop: function(event, ui) {
				        var classid = ui.helper.find('td').attr('id');
				        //alert(classid);
				        $('#'+classid).css("backgroundColor","blue"); 
				        var name = ui.helper.find('.name').html();
				        $('#cart1 .selected').append('<li id="1' + classid + '"><a href="javascript:fnDel(\''+1+'\',\''+classid+'\',\''+tesmsu+'\')"><font color=\'blue\'>' + name + '</font></a></li>');
				        count=$('#cart1 .selected').find('li').length;
				        $('#count1').html("인원수:"+count);
						total(tesmsu);
						teamInsert(classid, 2);						
					 }
			 });
			 
		   $("#cart"+2).droppable({
				    scope: 'cart-item',
				    activeClass: 'active',
				    hoverClass: 'hover',
				    tolerance: 'pointer',
				    drop: function(event, ui) {
				        var classid = ui.helper.find('td').attr('id');
				        //alert(classid);
				        $('#'+classid).css("backgroundColor","green"); 
				        var name = ui.helper.find('.name').html();
				        $('#cart2 .selected').append('<li id="2' + classid + '"><a href="javascript:fnDel(\''+2+'\',\''+classid+'\',\''+tesmsu+'\')"><font color=\'green\'>' + name + '</font></a></li>');
				        count=$('#cart2 .selected').find('li').length;
				        $('#count2').html("인원수:"+count);
						total(tesmsu);
						teamInsert(classid, 3);	
					 }
			 });
			 
			 $("#cart"+3).droppable({
				    scope: 'cart-item',
				    activeClass: 'active',
				    hoverClass: 'hover',
				    tolerance: 'pointer',
				    drop: function(event, ui) {
				        var classid = ui.helper.find('td').attr('id');
				        //alert(classid);
				        $('#'+classid).css("backgroundColor","#AA65F6"); 
				        var name = ui.helper.find('.name').html();
				        $('#cart3 .selected').append('<li id="3' + classid + '"><a href="javascript:fnDel(\''+3+'\',\''+classid+'\',\''+tesmsu+'\')"><font color=\'#AA65F6\'>' + name + '</font></a></li>');
				        count=$('#cart3 .selected').find('li').length;
				        $('#count3').html("인원수:"+count);
						total(tesmsu);
						teamInsert(classid, 4);	
					 }
			 });
			 
			 $("#cart"+4).droppable({
				    scope: 'cart-item',
				    activeClass: 'active',
				    hoverClass: 'hover',
				    tolerance: 'pointer',
				    drop: function(event, ui) {
				        var classid = ui.helper.find('td').attr('id');
				        //alert(classid);
				        $('#'+classid).css("backgroundColor","#ED6F58"); 
				        var name = ui.helper.find('.name').html();				       
				        $('#cart4 .selected').append('<li id="4' + classid + '"><a href="javascript:fnDel(\''+4+'\',\''+classid+'\',\''+tesmsu+'\')"><font color=\'#ED6F58\'>' + name + '</font></a></li>');
				        count=$('#cart4 .selected').find('li').length;
				        $('#count4').html("인원수:"+count);
						total(tesmsu);
						teamInsert(classid, 5);	
					 }
			 });
			 
			 $("#cart"+5).droppable({
				    scope: 'cart-item',
				    activeClass: 'active',
				    hoverClass: 'hover',
				    tolerance: 'pointer',
				    drop: function(event, ui) {
				        var classid = ui.helper.find('td').attr('id');
				        //alert(classid);
				        $('#'+classid).css("backgroundColor","#22ABEF"); 
				        var name = ui.helper.find('.name').html();				      
				        $('#cart5 .selected').append('<li id="5' + classid + '"><a href="javascript:fnDel(\''+5+'\',\''+classid+'\',\''+tesmsu+'\')"><font color=\'#22ABEF\'>' + name + '</font></a></li>');
				        count=$('#cart5 .selected').find('li').length;
				        $('#count5').html("인원수:"+count);
						total(tesmsu);
						teamInsert(classid, 6);	
					 }
			 });
			 
			 var table = $('table');
			 table.find('tr td.name').bind('mousedown', function() {
			    table.disableSelection();
			 }).bind('mouseup', function() {
			    table.enableSelection();
			 }).draggable({
			    helper: function(event) {		    
			        return $('<div class="drag-cart-item"><table></table></div>').find('table').append($(event.target).closest('td').clone()).end().insertAfter(table);
			    },
			    cursorAt: {
			        left: -5,
			        bottom: 5
			    },
			    cursor: 'move',
			    distance: 10,
			    delay: 100,
			    scope: 'cart-item',
			    revert: 'invalid'
			});
	 }
  
    function teamInsert(classid, team){
	  //alert(classid);
	  var num=classid.split("-")[1];
	  //alert(num);
	  $.ajax({
		  url : "teamInsert",
		  type : "POST",
		  data : {"num": num , "team" : team  },
		  success : function(data){
			  //alert("팀원 저장완료");
		  },
		  error : function(){ alert("error"); }
	  });	 
    }
  
    function teamExcel(){
	  $.ajax({
		  url : "teamExcel",
		  type : "POST",
		  data : {"lcode": '${lcode}', "mnum" : '${mnum}' },
		  success : function(data){
			  alert("팀 엑셀 저장완료");			
			  window.open("${path}/teamDownload?lcode=${lcode}&mnum=${mnum}","","width=500, height=300,left=400,top=300");
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
	<table style="width: 100%"  class='table table-hover'>
   <tr>
   <td colspan='3'>
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
      <tr class='info'>
        <td align="right"><input type='button' value='팀프로젝트 수(인원수)'  class='btn btn-success btn-xs'/></td>
        <td>
        <div class='col-xs-2'>
        <input type='text'  id='teamsu' class='form-control input-sm' name='teamsu' size='3'/>
        </div>
        <input type='button' class='btn btn-primary btn-xs' value='팀수만들기' onclick='teammake()'/>
        </td>
        <td valign="bottom">
	    <input type='button' value='엑셀에저장하기' onclick='teamExcel()' class='btn btn-success btn-xs'/>
	    </td>
	    </tr>
	    <tr>
	     <td colspan="3">
	     <div class="panel-body" id="cartlist"></div>
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