<%@page import="kr.kosa.emp.EmpDao"%>
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
EmpDao dao = new EmpDao();

String deptStr = request.getParameter("deptno");
if(deptStr!=null) {
	int deptno = Integer.parseInt(deptStr);
	double avgsal = dao.getAverageSalaryByDepartment(deptno);
	out.print("<h1>" + avgsal + "</h1>");
}
%>
</body>
</html>