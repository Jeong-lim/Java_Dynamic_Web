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
<h1>사원 정보 수정 양식</h1>
<form action="EmpUpdate.do" method="post">
<table border=1>
<tr>
	<td>사원아이디</td>
	<td><input type="number" name="employeeId" value="${emp.employeeId}" readonly></td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="firstName" value="${emp.firstName}"></td>
</tr>
<tr>
	<td>성</td>
	<td><input type="text" name="lastName" value="${emp.lastName}"></td>
</tr>
<tr>
	<td>이메일</td>
	<td><input type="text" name="email" value="${emp.email}"></td>
</tr>
<tr>
	<td>전화번호</td>
	<td><input type="text" name="phoneNumber" value="${emp.phoneNumber}"></td>
</tr>
<tr>
	<td>직무아이디</td>
	<td>
		<select name="jobId">
		<c:forEach var="job" items="${jobIdList}">
			<option value="${job.jobId}"
			<c:if test="${emp.jobId==job.jobId}">selected</c:if>
			>${job.jobTitle}</option>
		</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td>입사일</td>
	<td><input type="date" name="hireDate" value="${emp.hireDate}"></td>
</tr>
<tr>
	<td>급여</td>
	<td><input type="number" name="salary" value="${emp.salary}"></td>
</tr>
<tr>
	<td>보너스율</td>
	<td><input type="number" name="commissionPct" min="0" max="0.99" step="0.01" value="${emp.commissionPct}"></td>
</tr>
<tr>
	<td>매니저아이디</td>
	<td>
		<select name="managerId">
		<c:forEach var="mgr" items="${mgrIdList}">
			<option value="${mgr.empId}"
			<c:if test="${emp.managerId==mgr.empId}">selected</c:if>
			>${mgr.name} (${mgr.empId})</option>
		</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td>부서번호</td>
	<td>
		<select name="departmentId">
		<c:forEach var="dept" items="${deptIdList}">
			<option value="${dept.deptId}"
			<c:if test="${emp.departmentId==dept.deptId}">selected</c:if>
			>${dept.deptName} (${dept.deptId})</option>
		</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td colspan=2>
		<input type="submit" value=" 저 장 ">
		<input type="reset" value=" 취 소 ">
	</td>
</table>
</form>
</body>
</html>