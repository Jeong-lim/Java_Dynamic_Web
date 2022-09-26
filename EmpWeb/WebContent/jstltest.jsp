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
<c:set var="name" value="홍길동" scope="request"/>
<%
 // request.setAttribute("name", "홍길동");
%>
<h1>${name}</h1>
<c:remove var="name" scope="request"/>
<%
	request.removeAttribute("name");
%>
.<h1>${name}</h1>
<c:if test="${empty name}">
<h1>이름 데이터가 없습니다.</h1>
</c:if>

<c:set var="age" value="15"/>
<c:choose>
	<c:when test="${age ge 20}">성인입니다.</c:when>
	<c:when test="${age le 7 }">유아입니다.</c:when>
	<c:otherwise>기타입니다.</c:otherwise>
</c:choose>
<%
String[] nameList = {"홍길동", "이순신", "허진경", "홍길서", "홍길남"};
request.setAttribute("nameList", nameList);
%>
<p>
<c:forEach var="name" items="${nameList}">
	${name}<br>
</c:forEach>
<c:forEach var="num" begin="1" end="20" step="2">
${num}-Hello<br>
</c:forEach>
<%
request.setAttribute("strings", "홍길동,홍길서,홍길남,홍길북");
%>
<p>
<c:forTokens var="str" items="${strings}" delims=",">
${str}-<c:out value="${str}"/><br>
</c:forTokens>
<p>
<%@ include file="index.jsp" %>
<jsp:include page="hello.jsp"></jsp:include>
<!-- c:import url="https://www.naver.com/"/-->
<%
//response.sendRedirect("index.jsp");
%>
<%-- c:redirect url="index.jsp"></c:redirect--%>
<a href='<c:url value="/emp?cmd=empdetail&empid=103"/>'>103번 사원 정보</a>

</body>
</html>




