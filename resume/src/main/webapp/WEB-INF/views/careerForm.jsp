<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<style type="text/css">
	.tg  {border-collapse:collapse;border-spacing:0;}
	.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
	  overflow:hidden;padding:10px 5px;word-break:normal;}
	.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
	  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
	.tg .tg-46ru{background-color:#96fffb;border-color:inherit;text-align:left;vertical-align:top}
	.tg .tg-cjtp{background-color:#ecf4ff;border-color:inherit;text-align:left;vertical-align:top}
	.tg .tg-y7gf{border-color:inherit;font-size:28px;text-align:center;vertical-align:top}
	.tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}
	.tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
	
	  body, table {
		  font-size: 12px;
		  font-family: 나눔고딕, NanumGothic, NanumBarunGothic,'Nanum Gothic',arial,verdana,sans-serif;
	   }
	  table {
	    background-color:#f2f2f2; 
	    border: 1px solid #cacac8;
	    border-collapse: collapse;
	    font-family: 나눔고딕, NanumGothic, NanumBarunGothic,'Nanum Gothic',arial,verdana,sans-serif;
	  }
	  th, td {
	    border: 1px solid #cacac8;
	    padding: 5px;
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
<script>
$(document).ready(function(){
	
	
	
});
 
</script>
</head>
<body>
<table class="tg" style="undefined;table-layout: fixed; width: 800px">
<colgroup>
<col style="width: 58px">
<col style="width: 63px">
<col style="width: 136px">
<col style="width: 106px">
<col style="width: 67px">
<col style="width: 113px">
</colgroup>
<thead>
  <tr>
    <th class="tg-y7gf" colspan="6">이&nbsp;&nbsp;&nbsp;력&nbsp;&nbsp;&nbsp;서</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-c3ow" colspan="2" rowspan="4">사진</td>
    <td class="tg-c3ow">이름</td>
    <td class="tg-0pky">박매일</td>
    <td class="tg-0pky">생년월일</td>
    <td class="tg-0pky">1973.1.10</td>
  </tr>
  <tr>
    <td class="tg-c3ow">핸드폰</td>
    <td class="tg-0pky">010-8853-2436</td>
    <td class="tg-0pky">E-Mail</td>
    <td class="tg-0pky">bit@empas.com</td>
  </tr>
  <tr>
    <td class="tg-c3ow">주소</td>
    <td class="tg-0pky" colspan="3"></td>
  </tr>
  <tr>
    <td class="tg-c3ow">지원분야</td>
    <td class="tg-0pky" colspan="3"></td>
  </tr>
  <tr>
    <td class="tg-cjtp" colspan="6">■학력사항</td>
  </tr>
  <tr>
    <td class="tg-c3ow" colspan="2">년도</td>
    <td class="tg-c3ow">학교명</td>
    <td class="tg-c3ow" colspan="2">전공</td>
    <td class="tg-c3ow">졸업여부</td>
  </tr>
   <tr id="aaa">
    <td class="tg-0pky" colspan="2">2018.03~2019.05</td>
    <td class="tg-0pky">순천대학교</td>
    <td class="tg-0pky" colspan="2">정보통신공학</td>
    <td class="tg-0pky">졸업&nbsp;<input type="button" value="삭제"/></td>
  </tr>
  <tr id="aaa">
    <td class="tg-0pky" colspan="2"><input type="date">~<input type="date"></td>
    <td class="tg-0pky"><input type="text" value="대한민국 대학교"/></td>
    <td class="tg-0pky" colspan="2"><input type="text"/></td>
    <td class="tg-0pky"><input type="text" size="10"/>&nbsp;<input type="button" value="등록"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky"></td>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky"></td>
  </tr>
  <tr>
    <td class="tg-cjtp" colspan="6">■경력사항</td>
  </tr>
  <tr>
    <td class="tg-c3ow" colspan="2">근무기간</td>
    <td class="tg-c3ow">근무처</td>
    <td class="tg-c3ow" colspan="2">담당업무</td>
    <td class="tg-c3ow">근속연수</td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2">2018.03~2019.05</td>
    <td class="tg-0pky">(주)비온시이노베이터</td>
    <td class="tg-0pky" colspan="2">개발 및 사업관리</td>
    <td class="tg-0pky">1년2개월&nbsp;<input type="button" value="삭제"/></td>
  </tr>
   <tr>
    <td class="tg-0pky" colspan="2"><input type="date">~<input type="date"></td>
    <td class="tg-0pky"><input type="text" value="(주)비온시이노베이터"/></td>
    <td class="tg-0pky" colspan="2"><input type="text"/></td>
    <td class="tg-0pky"><input type="text" size="10"/>&nbsp;<input type="button" value="등록"/></td>
  </tr> 
  <tr>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky"></td>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky"></td>
  </tr>
  <tr>
    <td class="tg-cjtp" colspan="6">■ 교육사항</td>
  </tr>
  <tr>
    <td class="tg-c3ow" colspan="2">교육기관</td>
    <td class="tg-c3ow" colspan="2">교육과정명</td>
    <td class="tg-c3ow" colspan="2">훈련기관</td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2">2019.11~2020.03</td>
    <td class="tg-0pky" colspan="2">지능형 빅데이터 서비스개발자과정</td>
    <td class="tg-0pky" colspan="2">스마트인재개발원&nbsp;<input type="button" value="삭제"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2"><input type="date">~<input type="date"></td>
    <td class="tg-0pky" colspan="2"><input type="text"/></td>
    <td class="tg-0pky" colspan="2"><input type="text" size="10"/>&nbsp;<input type="button" value="등록"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky" colspan="2"></td>
  </tr>
  <tr>
    <td class="tg-cjtp" colspan="6">■ 자격사항</td>
  </tr>
  <tr>
    <td class="tg-c3ow" colspan="2">취득일</td>
    <td class="tg-c3ow" colspan="2">자격증명</td>
    <td class="tg-c3ow" colspan="2">시행처</td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2">2017년5월26일</td>
    <td class="tg-0pky" colspan="2">정보처리기사</td>
    <td class="tg-0pky" colspan="2">한국산업인력공단&nbsp;<input type="button" value="삭제"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2"><input type="date">~<input type="date"></td>
    <td class="tg-0pky" colspan="2"><input type="text"/></td>
    <td class="tg-0pky" colspan="2"><input type="text" size="10"/>&nbsp;<input type="button" value="등록"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky" colspan="2"></td>
  </tr>
  <tr>
    <td class="tg-46ru" colspan="6">■ 보유기술 및 능력(프로젝트)</td>
  </tr>
  <tr>
    <td class="tg-c3ow" colspan="2">보유기술 및 능력</td>
    <td class="tg-c3ow">수준(상/중/하)</td>
    <td class="tg-c3ow" colspan="3">상세내용</td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2">Java</td>
    <td class="tg-0pky">중</td>
    <td class="tg-0pky" colspan="3">자바의 기본기술 및 이를 이용한 어플개발 가능&nbsp;<input type="button" value="삭제"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2" valign="middle"><input type="text"/></td>
    <td class="tg-0pky" valign="middle"><input type="text"/></td>
    <td class="tg-0pky" colspan="3"><textarea rows="5" cols="50"></textarea>&nbsp;<input type="button" value="등록"/></td>
  </tr> 
  <tr>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky"></td>
    <td class="tg-0pky" colspan="3"></td>
  </tr>
  <tr>
    <td class="tg-46ru" colspan="6">■ 프로젝트 경험</td>
  </tr>
  <tr>
    <td class="tg-c3ow" colspan="2">프로젝트기간</td>
    <td class="tg-c3ow">프로젝트제목</td>
    <td class="tg-c3ow" colspan="3">프로젝트 내용</td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2">2019.11~2020.03</td>
    <td class="tg-0pky">자바 애플리케이션</td>
    <td class="tg-0pky" colspan="3">자바 애플리케이션&nbsp;<input type="button" value="삭제"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2"><input type="date">~<input type="date"></td>
    <td class="tg-0pky"><input type="text"/></td>
    <td class="tg-0pky" colspan="3"><textarea rows="5" cols="50"></textarea>&nbsp;<input type="button" value="등록"/></td>
  </tr>
  <tr>
    <td class="tg-0pky" colspan="2"></td>
    <td class="tg-0pky"></td>
    <td class="tg-0pky" colspan="3"></td>
  </tr>  
</tbody>
</table>
</body>
</html>