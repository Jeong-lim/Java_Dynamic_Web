<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="number1" value="12345" scope="page"/>
<h1>${number1}</h1>
<h1><fmt:formatNumber value="${number1}"/></h1>
<h1><fmt:formatNumber value="${number1}" groupingUsed="false"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencySymbol="@"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencyCode="GBP"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencyCode="EUR"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencyCode="KRW"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencyCode="USD"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencyCode="JPY"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencyCode="CAD"/></h1>
<h1><fmt:formatNumber value="${number1}" type="currency" currencyCode="CNY"/></h1>
<c:set var="number2" value="6,789.01"/>
<fmt:parseNumber value="${number2}" pattern="00,000.00"/>
<fmt:parseNumber value="${number2}" integerOnly="true"/>
<c:set var="today" value="<%= new java.util.Date() %>"/>
<h2>${today}</h2>
<h2><fmt:formatDate value="${today}" type="date" dateStyle="full"/></h2>
<h2><fmt:formatDate value="${today}" type="date" dateStyle="short"/></h2>
<h2><fmt:formatDate value="${today}" type="date" dateStyle="long"/></h2>
<h2><fmt:formatDate value="${today}" type="date" dateStyle="default"/></h2>

<h2><fmt:formatDate value="${today}" type="time" timeStyle="full"/></h2>
<h2><fmt:formatDate value="${today}" type="time" timeStyle="short"/></h2>
<h2><fmt:formatDate value="${today}" type="time" timeStyle="long"/></h2>
<h2><fmt:formatDate value="${today}" type="time" timeStyle="default"/></h2>

<h2><fmt:formatDate value="${today}" type="both"/></h2>
<h2><fmt:formatDate value="${today}" type="both" pattern="yyyy-MM-dd hh:mm:ss"/></h2>
<h2><fmt:formatDate value="${today}" type="both" pattern="yyyy. M. d. hh:mm:ss"/></h2>

<fmt:timeZone value="American/Chicago">
<h2><fmt:formatDate value="${today}" type="both" pattern="yyyy. M. d. hh:mm:ss"/></h2>
</fmt:timeZone>

<p>
<fmt:setLocale value="ko_kr"/>
<fmt:formatNumber value="${number1}" type="currency"/>
<fmt:formatDate value="${today}"/>
<p>
<fmt:setLocale value="ja_jp"/>
<fmt:formatNumber value="${number1}" type="currency"/>
<fmt:formatDate value="${today}"/>
<p>
<fmt:setLocale value="en_US"/>
<fmt:formatNumber value="${number1}" type="currency"/>
<fmt:formatDate value="${today}"/>
</body>
</html>





