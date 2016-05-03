<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
  	<meta name='description' content='books index page'>
    <title>Books</title>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				Book Listing <a href="${path_new}" role="button" class="btn btn-default">+</a>
			</h3>
	  	</div>
	  	<div class="panel-body">
	    	<table class="table">
	    		<thead>
	    			<tr>
	    				<td>Id</td>
	    				<td>Name</td>
	    				<td>Action</td>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach items="${books}" var="book">
	    				<tr>
	    					<td>${book.id}</td>
	    					<td>${book.name}</td>
	    					<td>
	    						<a href="${path}/${book.id}">View</a> |
	    						<a href="${path}/${book.id}.json">View Json</a>
	    					</td>
	    				</tr>
	    			</c:forEach>
	    		</tbody>
	    	</table>
	    	
	    	<div id="testDiv">${msg}</div>
	  	</div>
	</div>
  </body>
</html>