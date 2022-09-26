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
String deptName = null;
String empidStr = request.getParameter("empid");
if(empidStr!=null) {
	int empid = Integer.parseInt(empidStr);
	deptName = dao.getDepartmentNameByEmployeeId(empid);
}
%>
<h1>부서 이름은 <%= deptName %>입니다.</h1>
</body>
</html>