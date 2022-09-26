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
String empidStr = request.getParameter("empid");
if(empidStr!=null) {
	int empid = Integer.parseInt(empidStr);
	int result = dao.getSalaryByEmployeeId(empid);
	out.println("<h1>" + result + "</h1>");
}
%>
</body>
</html>