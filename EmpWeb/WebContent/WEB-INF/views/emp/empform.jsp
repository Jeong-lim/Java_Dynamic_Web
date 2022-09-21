<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>사원 정보 입력 양식</h1>
<form action="EmpInsert.do" method="post">
<table border=1>
	<tr>
		<td>사원번호</td>
		<td><input type="number" name="employeeId" /></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="firstName" /></td>
	</tr>
	<tr>
		<td>성</td>
		<td><input type="text" name="lastName" /></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" /></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="phoneNumber" /></td> <!-- valueObject의 변수명하고 똑같이 썻다. -->
	</tr>
	<tr>
		<td>입사일</td>
		<td><input type="date" name="hireDate" /></td>
	</tr>
	<tr>
		<td>직무 아이디</td>
		<td>
			<select name="jobId">
			<c:forEach var="job" items="${jobIdList}">
				<option value="${job.jobId}">${job.jobTitle}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>급여</td>
		<td><input type="number" name="salary" /></td>
	</tr>
	<tr>
		<td>보너스율</td>
		<td><input type="number" name="commissionPct" min="0" max="0.99" step="0.01" /></td>
	</tr>
	<tr>
		<td>매니저 아이디</td>
		<td>
			<select name="managerId">
			 	<c:forEach var="emp" items="${empIdList}">
					<option value="${emp.empId}">${emp.empName} (${emp.empId })</option>
				</c:forEach>
		    </select>
		</td>
	</tr>
	<tr>
		<td>부서번호</td>
		<td>
			<select name="departmentId">
			<c:forEach var="dept" items="${deptIdList}">
				<option value="${dept.deptId}">${dept.deptName} (${dept.deptId })</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan=2>
			<input type="submit" value=" 저장 " />
			<input type="reset" value=" 취 소 " />
		</td>
	</tr>
</table>
</form>
</body>
</html>