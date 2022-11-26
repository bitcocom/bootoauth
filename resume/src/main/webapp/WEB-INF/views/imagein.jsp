<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진업로드</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
 <style>
  body, table {
	  font-size: 12px;
   }
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    padding: 5px;
  }
  .bg{
    color: white;
  }
</style>
<script type="text/javascript">
   function imageupload(){
	    var f = new FormData(document.getElementById('form1'));
        //alert($('#lcode').val());
        $.ajax({
            url: "imageupload",
            data: f,
            processData: false,
            contentType: false,
            type: "POST",
            success: function(data){
                console.log(data);
                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                alert("사진업로드완료");
                //opener.location.href='/resume/upload';
               // opener.$("#stview").trigger('onclick');
               window.opener.$("#stview").trigger('click');
               self.close();
            }
        })
   }

</script>
</head>
<body>
${num}, ${lcode}, ${mnum}, ${name} <br/>
이미이름은 훈련생이름으로 할것<br/>
이미지는 80X100 크기로 올려주세요(jpg, png, gif)<br/>
<hr/>
<form id='form1' name='form1' method='post' enctype='multipart/form-data' accept-charset="UTF-8">
	<input type='file' id='fileInput' name='fileInput'>
	<input type='hidden' id='num' name='num' value='${num}'>
	<input type='hidden' id='lcode' name='lcode' value='${lcode}'>
	<input type='hidden' id='mnum' name='mnum' value='${mnum}'>
	<input type='button' value='사진업로드' onclick="imageupload()"/> 
</form>
</body>
</html>