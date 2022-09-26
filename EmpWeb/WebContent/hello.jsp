<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>모든 사원의 수는 <%
out.print(request.getAttribute("cnt"));
%></h1> <!-- Scriptlet 사용 자바코드를 이용해서 출력, 가장 권장하지 않음 -->
<h2>모든 사원의 수는 <%= request.getAttribute("cnt") %></h2> <!-- Expression, 자바스크립트 안에서만 사용 -->
<h3>모든 사원의 수는 ${cnt}</h3> <!-- EL(Expression Language)를 사용, 가장 권장함 -->
</body>
</html>