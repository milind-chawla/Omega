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
	  		<%@include file="/WEB-INF/views/jsp/_common0/msg.jsp" %>
	  		
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