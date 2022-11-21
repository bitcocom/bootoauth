<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/> 
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/>
<c:set var="au" value="${SPRING_SECURITY_CONTEXT.authentication.principal.authorities}"/> 
<c:set var="cpath" value="${pageContext.request.contextPath}"/>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${auth[0].authority}
${au[0].authority}
${auth[1].authority}
${au[1].authority}
</body>
</html>