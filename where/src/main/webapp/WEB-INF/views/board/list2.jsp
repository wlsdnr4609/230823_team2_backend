<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
<title>Home</title>
</head>
<body>
	<h1>Board List</h1>
	<table border="1">
	<thead>
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>제목</th>
		<th>조회</th>
		<th>작성일</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}">
		<tr>
			<td>${item.bid}</td>
			<td>${item.niname}</td>
			<td>${item.title}</td>
			<td>${item.counts}</td>
			<td>${item.regdate}</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
</body>
</html>
