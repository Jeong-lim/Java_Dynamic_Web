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

<%
EmployeeAvg2 dao = new EmployeeAvg2();
String deptName = null;
String empidStr = request.getParameter("empid");

if(empidStr != null) {
	int empid = Integer.parseInt(empidStr);
	deptName = dao.getDepartmentNameByEmployeeId(empid);
}
%>
<h2>부서이름은 <%= deptName %> 입니다.</h2>
</body>
</html>