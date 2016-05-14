<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html>
  <head>
  	<meta name='description' content=''>
    <title>Edit Book</title>
    <style type="text/css">
    	.formFieldMessage { color: #8ec127; }
		.formFieldError { color: #d41243; }
	</style>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				Edit Book
			</h3>
	  	</div>
	  	<div class="panel-body">
	  		<%@include file="/WEB-INF/views/jsp/_common0/msg.jsp" %>
	  		
	  		<mvc:form modelAttribute="book">
			  <div class="form-group">
			    <label for="name">Name</label>
			    <mvc:input path="name" class="form-control" placeholder="Enter name here" />
			    <mvc:hidden path="id" />
			  </div>
			  <mvc:button id="${lname}_submit_${book.id}" class="btn btn-default">
			  	<span class="glyphicon glyphicon-floppy-disk"></span> Submit
			  </mvc:button>
			  <a id="${lname}_cancel_${book.id}" class="btn btn-default" role="button" href="${path}">
				<span class="glyphicon glyphicon-floppy-remove"></span> Cancel
			  </a>
			</mvc:form>
			
	  	</div>
	</div>
  </body>
</html>