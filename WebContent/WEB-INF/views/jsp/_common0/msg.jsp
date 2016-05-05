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
