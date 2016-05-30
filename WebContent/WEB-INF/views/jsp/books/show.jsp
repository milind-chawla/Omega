<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Book Details</title>
    <meta name='description' content=''>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<strong>Book Details</strong>
			</h3>
	  	</div>
	  	<div class="panel-body">
	  		<%@include file="/WEB-INF/views/jsp/_common0/msg.jsp" %>
	  		
	    	<table class="table">
	    		<thead>
	    			<tr>
	    				<td><b>Id</b></td>
	    				<td><b>Name</b></td>
	    				<td><b>Thumbnail</b></td>
	    			</tr>
	    		</thead>
	    		<tbody>
    				<tr>
    					<td>${book.id}</td>
    					<td>${book.name}</td>    					
    					<td><img class='book-image' alt='No book' src='data:image/jpeg;charset=utf-8;base64,${book.getImageHex()}'></td>
    				</tr>
	    		</tbody>
	    	</table>
	    	<a class="btn btn-default" role="button" href="${apath}">
				<span class="glyphicon glyphicon-arrow-left"></span> Back
			</a>
	  	</div>
	</div>
  </body>
</html>