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
<c:if test="${not empty emp}">
사원아이디 : ${emp.employeeId}<br> <%-- emp.getEmployeeId() --%>
이름 : ${emp.firstName} ${emp.lastName}<br>
이메일 : ${emp.email}<br>
전화번호 : ${emp.phoneNumber}<br>
입사일 : ${emp.hireDate}<br>
직무아이디 : ${emp.jobId}<br>
급여 : ${emp.salary}<br>
보너스율 : ${emp.commissionPct}<br>
매니저아이디 : ${emp.managerId}<br>
부서아이디 : ${emp.departmentId}<br>
</c:if>
<c:if test="${empty emp}">
사원 정보가 없습니다.
</c:if>
</body>
</html>