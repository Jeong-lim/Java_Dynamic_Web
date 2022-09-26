<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>사원정보 삭제</h1>
<h3>삭제할 사원의 이메일을 입력하세요</h3>
<form action="EmpDelete.do" method="post">
<input type="hidden" name="empid" value="${param.empid}">
이메일 : <input type="text" name="email"><p>
<input type="submit" value=" 삭 제 ">
</form>
</body>
</html>