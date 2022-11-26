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
    border: 0px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 0px solid #444444;
    padding: 5px;
  }
  .bg{
    color: white;
  }
</style>
<script type="text/javascript">
function doExcelUploadProcess(){
    var f = new FormData(document.getElementById('form1'));
    alert($('#lcode').val());
    $.ajax({
        url: "uploadExcelFile1",
        data: f,
        processData: false,
        contentType: false,
        type: "POST",
        success: function(data){
            console.log(data);
            //document.getElementById('result').innerHTML ="데이터베이스입력완료";
            alert("시간표업로드완료");
            //location.href='/resume/list';
            opener.location.href='${path}/career2?lcode='+data.lcode+'&mnum='+data.mnum;
            self.close();            
        }
    }) 
}
</script>
</head>
<body>
	<form id='form1' name='form1' method='post' enctype='multipart/form-data'>
	<input type='file' id='fileInput' name='fileInput'>
	<input type='hidden' id='lcode' name='lcode' value='${lcode}'>
	<input type='hidden' id='mnum' name='mnum' value='${mnum}'>
	<input type='button' onclick='doExcelUploadProcess()' value='시간표업로드'/>  
	</form>
</body>
</html>