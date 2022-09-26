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
<h1>데이터베이스 연결 테스트 4</h1>
<%
//String url = application.getInitParameter("OracleURL");
//String id = application.getInitParameter("OracleId");
//String pw = application.getInitParameter("OraclePwd");
//EmpDao dao = new EmpDao(url, id, pw);
//EmpDao dao = new EmpDao(application);
EmpDao dao = new EmpDao();
int result = 0;

String deptStr = request.getParameter("deptno");
if(deptStr == null) {
	result = dao.getEmpCount();
}else {
	int deptno = Integer.parseInt(deptStr);
	result = dao.getEmpCount(deptno);
}


%>
<h2>사원의 수 : <%=result%></h2>
</body>
</html>