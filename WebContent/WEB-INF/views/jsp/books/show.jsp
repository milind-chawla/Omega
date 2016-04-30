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
				Book Details
			</h3>
	  	</div>
	  	<div class="panel-body">
	    	<table class="table">
	    		<thead>
	    			<tr>
	    				<td>Id</td>
	    				<td>Name</td>
	    			</tr>
	    		</thead>
	    		<tbody>
    				<tr>
    					<td>${book.id}</td>
    					<td>${book.name}</td>
    				</tr>
	    		</tbody>
	    	</table>
	  	</div>
	</div>
  </body>
</html>