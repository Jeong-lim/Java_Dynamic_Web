<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="booklist">
	<booklist>
		<book>
			<name>사피엔스</name>
			<author>유발 하라리</author>
			<price>19800</price>
		</book>
		<book>
			<name>총,균,쇠</name>
			<author>제러드 다이아몬드</author>
			<price>25200</price>
		</book>
	</booklist>
</c:set>
<x:parse xml="${booklist}" var="blist"/>
<h1><x:out select="$blist/booklist/book[1]/name" escapeXml="true"/></h1>
<h1><x:out select="$blist/booklist/book[1]/author" escapeXml="true"/></h1>
<h1><x:out select="$blist/booklist/book[1]/price" escapeXml="true"/></h1>
<x:forEach var="item" select="$blist/booklist/book">
	<x:out select="$item/name"/><br>
</x:forEach>
</body>
</html>