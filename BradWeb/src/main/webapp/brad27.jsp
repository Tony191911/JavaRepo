<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String x, y;
	String tempx = request.getParameter("x");
	String tempy = request.getParameter("y");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="brad27.jsp">
			<input name="x" />
			+
			<input name="y" />
			<input type="submit" value="=" />
		</form>
	</body>
</html>