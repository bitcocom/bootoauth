<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<c:set var="path" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html lang="en">
<head>
   <title>SAF(스마트아카이브공장)</title>
   <link rel="stylesheet" href="${path}/resources/css/style.css">
   <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
   <style type="text/css">
   body {
    font-family : 'Noto Sans KR', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background:url("${path}/resources/image/bg.jpg") no-repeat center;
    background-size: :cover;
}
body::before {

    content: "";
    position: absolute; z-index: 1;
    top: 0; right: 0; bottom: 0; left: 0;
    background-color: rgba(0,0,0,.7);    
}
   </style>
</head>
  <body>
  		<section class="login-form">
			<h1>SAF LOGIN</h1>
			<form method="post" action="${path}/login">
				<div class="int-area">
					<input type="text" name="id" id="id" autocomplete="off" required>
					<label for="id">ID</label>
				</div>

				<div class="int-area">
					<input type="password" name="pwd" id="pwd" autocomplete="off" required>
					<label for="pw">PASSWORD</label>
				</div>
				<div class="btn-area">
					<button id="btn" type="submit" style="outline: none;">LOGIN</button>
				</div>
			</form>

			<div class="caption">
				<a href="">Smart Archive Factory(기록물 보관소)</a>
			</div>
		</section>
  
   	<script>
			let id = $('#id');
			let pw = $('#pw');
			let btn = $('#btn');
			$(btn).on('click', function() {

				if($(id).val() == "") {

					$(id).next('label').addClass('warning');

					setTimeout(function() {

						$('label').removeClass('warning');

					},1500);

				}

				else if($(pw).val() == "") {

					$(pw).next('label').addClass('warning');

					setTimeout(function() {

						$('label').removeClass('warning');

					},1500);

				}

			} );

	</script>   
  </body>
</html> 

