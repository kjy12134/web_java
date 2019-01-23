<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%-- <c:choose>
			<c:when test="${fn:length(boards) == 0}">
				작성된 글이 존재하지 않습니다.
			</c:when>

			<c:otherwise>
				<c:forEach var="board" items="${boards}">
					<tr>
						<td>${board.seq}</td>
						<td>${board.title}</td>
						<td>${board.writer}</td>
						<td>${board.regdate}</td>
						<td>${board.cnt}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose> --%>
		
		<c:forEach var="board" items="${maps.list01}">
			<tr>
				<td>${board.seq}</td>
				<td>${board.title}</td>
				<td>${board.writer}</td>
				<td>${board.regdate}</td>
				<td>${board.cnt}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>

