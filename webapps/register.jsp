<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./join.sinc" method="post">
				<label>아이디</label> 
				<input type="text" name="id" placeholder="아이디">
				<br/>
				<label>패스워드</label> 
				<input type="password" name="pwd" placeholder="패스워드">
				<label>이름</label> 
				<br/>
				<input type="text" name="name" placeholder="이름">
				<br/>
				<label>부서</label>
				<select name="dept">
					<option value="PAY">PAY</option>
					<option value="AI">AI</option>
					<option value="FOOD">FOOD</option>
				</select>
				<button type="submit">등록</button>
	</form>
</body>
</html>