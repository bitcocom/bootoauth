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
   function download(){
	    var f = new FormData(document.getElementById('form1'));
        //alert($('#lcode').val());
        $.ajax({
            url: "downloadExcelFile",
            data: f,
            processData: false,
            contentType: false,
            type: "POST",
            success: function(data){
                console.log(data);
                //document.getElementById('result').innerHTML ="데이터베이스입력완료";
                alert("사진LIST다운로드완료");
                //opener.location.href='/resume/upload';
                self.close();
            }
        })
   }

</script>
</head>
<body>
<br/>
${fileName}<br/>
<hr/>
<table>
<tr>
<td valign="middle" align="center">[이력서 다운로드]<td>
<td><a href="${path}/fileDown?lcode=${lcode}&mnum=${mnum}&filecol=${fileName}"><img src='${path}/resources/image/sang.png'/></a></td>
</tr>
<tr>
<td colspan="2"><input type="button" value="닫기" onclick="self.close();"/></td>
</tr>
</table>
</body>
</html>