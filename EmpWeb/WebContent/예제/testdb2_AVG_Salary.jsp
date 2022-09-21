<%@ page import="practice.EmployeeAvg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 사원 평균 월급 구하기 테스트</h1>

<%
EmployeeAvg dao = new EmployeeAvg();
String deptStr = request.getParameter("deptno");
double avgsal = 0;

if(deptStr != null) {
	int deptno = Integer.parseInt(deptStr);
	avgsal = dao.getAverageSalaryByDepartment(deptno);
} else {
	avgsal = dao.getAverageSalaryByDepartment();
}
%>
<h2><%= avgsal %>원</h2>
</body>
</html>