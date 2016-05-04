<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Book Details</title>
    <meta name='description' content='book details page'>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<strong>Book Details</strong>
			</h3>
	  	</div>
	  	<div class="panel-body">
	    	<table class="table">
	    		<thead>
	    			<tr>
	    				<td><b>Id</b></td>
	    				<td><b>Name</b></td>
	    			</tr>
	    		</thead>
	    		<tbody>
    				<tr>
    					<td>${book.id}</td>
    					<td>${book.name}</td>
    				</tr>
	    		</tbody>
	    	</table>
	    	<a id="button_${lname}_back" class="btn btn-default" role="button" href="${path}">
				<span class="glyphicon glyphicon-arrow-left"></span> Back
			</a>
	  	</div>
	</div>
  </body>
</html>