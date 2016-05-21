<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${messages != null && messages.size() != 0}">
	<h4>Messages</h4>
	<ul>
		<c:forEach items="${messages}" var="message">
			<li class="message">
				<c:out value="${message}"></c:out>
			</li>
		</c:forEach>
	</ul>
</c:if>

<c:if test="${warnings != null && warnings.size() != 0}">
	<h4>Warnings</h4>
	<ul>
		<c:forEach items="${warnings}" var="warning">
			<li class="warning">
				<c:out value="${warning}"></c:out>
			</li>
		</c:forEach>
	</ul>
</c:if>
	
<c:if test="${errors != null && errors.size() != 0}">
	<h4>Errors</h4>
	<ul>
		<c:forEach items="${errors}" var="error">
			<li class="error">
				<c:out value="${error}"></c:out>
			</li>
		</c:forEach>
	</ul>
</c:if>
