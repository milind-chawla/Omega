<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<html>
  <head>
  	<meta name='description' content=''>
    <title>Edit Book</title>
    <style type="text/css">
    	.formFieldError { background-color: #FFC; }
	</style>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				Edit ->> ${book}
			</h3>
	  	</div>
	  	<div class="panel-body">
	  		<%@include file="/WEB-INF/views/jsp/_common0/msg.jsp" %>
			
			<mvc:form modelAttribute="book">
			  <div class="form-group row">
			    <label for="name" class="col-md-12 form-control-label">Name</label>
			    <div class="col-md-8">
			    	<mvc:input path="name" class="form-control" cssErrorClass="form-control formFieldError" placeholder="Enter name here" />
			    	<mvc:hidden path="image" />
			    	<mvc:hidden path="id" />
			    </div>
			    <div class="col-md-4">
			    	<mvc:errors path="name" />
			    </div>
			  </div>
			  <div class="form-group row">
			      <div class="col-md-12">
			         <mvc:button class="btn btn-default">
			  			<span class="glyphicon glyphicon-floppy-disk"></span> Submit
			  		 </mvc:button>
			  		 <a class="btn btn-default" role="button" href="${apath}">
						<span class="glyphicon glyphicon-floppy-remove"></span> Cancel
			  		 </a>
			      </div>
			  </div>
			</mvc:form>
	  	</div>
	</div>
  </body>
</html>