<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">섭섭이와 함께</div>
	<hr />
	<div align="right">
		<c:if test="${loginUser == null }">
			<form action="./login.sinc" method="post">
				<label>아이디</label> 
				<input type="text" name="id" placeholder="아이디">
				<label>패스워드</label> 
				<input type="text" name="pwd" placeholder="패스워드">
				<button type="submit">로그인</button>
				<a href="registForm.sinc">[가입]</a>
			</form>
		</c:if>
		
		<c:if test="${loginUser != null }">
			${loginUser.name}님 환영합니다.
			<a href="logout.sinc">[로그아웃]</a>
		</c:if>
	</div>
</body>
</html>