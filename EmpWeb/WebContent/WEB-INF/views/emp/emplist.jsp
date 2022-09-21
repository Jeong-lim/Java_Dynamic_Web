<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<H1>사원 리스트</H1>
<a href="EmpInsert.do">신규 사원정보 입력</a>
<table border=1>
<tr>
	<th>사원 번호</th>
	<th>이름</th>
	<th>이메일</th>
	<th>전화번호</th>
	<th>직무아이디</th>
	<th>입사일</th>
	<th>급여</th>
	<th>보너스율</th>
	<th>매니저 아이디</th>
	<th>부서 번호</th>
<c:forEach var="emp" items="${empList}">
<tr>
	<td>${emp.employeeId}</td>
	<td>${emp.firstName} ${emp.lastName}</td>
	<td>${emp.email}</td>
	<td>${emp.phoneNumber}</td>
	<td>${emp.jobId}</td>
	<td>${emp.hireDate}</td>
	<td>${emp.salary}</td>
	<td>${emp.commissionPct}</td>
	<td>${emp.managerId}</td>
	<td>${emp.departmentId}</td>
</tr>
</c:forEach>
</table>
</body>
</html>