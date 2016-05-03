<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html>
  <head>
  	<meta name='description' content='book details page'>
    <title>Create Book</title>
    <style type="text/css">
    	.formFieldMessage { color: #8ec127; }
		.formFieldError { color: #d41243; }
	</style>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				Create Book
			</h3>
	  	</div>
	  	<div class="panel-body">
	  		
	  		<c:if test="${messages != null && messages.size() != 0}">
	  			<h4>Messages</h4>
	  			<ul>
					<c:forEach items="${messages}" var="message">
						<li class="formFieldMessage">
							<c:out value="${message}"></c:out>
						</li>
					</c:forEach>
				</ul>
	  		</c:if>
	  		
	  		<c:if test="${errors != null && errors.size() != 0}">
	  			<h4>Errors</h4>
	  			<ul>
					<c:forEach items="${errors}" var="error">
						<li class="formFieldError">
							<c:out value="${error}"></c:out>
						</li>
					</c:forEach>
				</ul>
	  		</c:if>
	  		
	    	<mvc:form modelAttribute="book">
			  <div class="form-group">
			    <label for="name">Name</label>
			    <mvc:input path="name" class="form-control" placeholder="Enter name here" cssErrorClass="formFieldError" />
			  </div>
			  <mvc:button class="btn btn-default">Submit</mvc:button>
			</mvc:form>
			
	  	</div>
	</div>
  </body>
</html>