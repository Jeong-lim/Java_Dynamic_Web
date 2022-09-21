<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees</title>
</head>

<body>
<a href="emp?cmd=empcount">모든 사원의 수</a><p>
<a href="emp?cmd=empcount&deptid=50">50번 사원의 수</a><p>
<a href="emp?cmd=getdept&empid=103">103번 사원의 부서이름</a><p>
<a href="emp?cmd=avgsal&deptid=50">50번 부서의 급여 평균</a><p>
<a href="emp?cmd=empsal&empid=103">103번 사원의 급여</a><p>
<a href="emp?cmd=empdetail&empid=103">103번 사원의 상세 정보</a><p>
<h1>사원 관리</h1>
<a href="EmpList.do">사원 목록 조회</a>
</body>
</html>