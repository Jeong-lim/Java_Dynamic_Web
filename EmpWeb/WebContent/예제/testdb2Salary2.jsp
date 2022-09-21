<%@ page import="practice.EmployeeAvg2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 사원별 월급 구하기 테스트</h1>

<%
EmployeeAvg2 dao = new EmployeeAvg2();
String empidStr = request.getParameter("empid");
int avgsal = 0;

if(empidStr != null) {
	int empid = Integer.parseInt(empidStr);
	avgsal = dao.getSalaryByEmployeeId(empid);
}
%>
<h2><%= avgsal %>원</h2>
</body>
</html>