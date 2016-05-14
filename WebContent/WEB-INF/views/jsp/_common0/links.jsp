<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${links}" var="link">
	<li id='<c:out value="${link._1()}" />'><a href="${link._3()}">${link._2()}</a></li>	
</c:forEach>

<!-- <c:out value="${links.toString()}" /> -->
