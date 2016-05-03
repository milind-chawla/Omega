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
				<strong>Book Listing</strong> <a href="${path_new}" role="button" class="btn btn-default">+</a>
			</h3>
	  	</div>
	  	<div class="panel-body">
	    	<table class="table">
	    		<thead>
	    			<tr>
	    				<td><b>Id</b></td>
	    				<td><b>Name</b></td>
	    				<td><b>Action</b></td>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach items="${books}" var="book">
	    				<tr>
	    					<td>${book.id}</td>
	    					<td>${book.name}</td>
	    					<td>
	    						<button id="button_show_${book.id}" class="btn btn-default" type="button" onclick="showBook(${book.id});">
	    							<span class="glyphicon glyphicon-search"></span> Show
	    						</button>
	    						<button id="button_edit_${book.id}" class="btn btn-default" type="button" onclick="editBook(${book.id});">
	    							<span class="glyphicon glyphicon-edit"></span> Edit
	    						</button>
	    						<button id="button_delete_${book.id}" class="btn btn-default" type="button" onclick="deleteBook(${book.id});">
	    							<span class="glyphicon glyphicon-remove"></span> Delete
	    						</button>
	    					</td>
	    				</tr>
	    			</c:forEach>
	    		</tbody>
	    	</table>
	  	</div>
	</div>
  </body>
</html>