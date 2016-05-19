<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
  	<meta name='description' content=''>
    <title>Books</title>
  </head>
  <body>
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a href="${path_new}" role="button" class="btn btn-default">
					<span class="glyphicon glyphicon-plus"></span> Add Book
				</a>
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
	    			<c:set var="xid" scope="page" value="-1"></c:set>
	    			<c:forEach items="${books}" var="book">
	    				<tr>
	    					<td>${book.id}</td>
	    					<td>${book.name}</td>
	    					<td>
	    						<a id="${lname}_show_${book.id}" class="btn btn-default" role="button" href="${path}/${book.id}">
	    							<span class="glyphicon glyphicon-search"></span> Show
	    						</a>
	    						<a id="${lname}_edit_${book.id}" class="btn btn-default" role="button" href="${path}/${book.id}/edit">
	    							<span class="glyphicon glyphicon-edit"></span> Edit
	    						</a>
	    						<a id="${lname}_delete_${book.id}" class="btn btn-default" role="button" href="${path}/${book.id}/delete">
	    							<span class="glyphicon glyphicon-remove"></span> Delete
	    						</a>
	    					</td>
	    				</tr>
	    				<c:set var="xid" scope="page" value="${book.id}"></c:set>
	    			</c:forEach>
	    		</tbody>
	    	</table>
	  	</div>
	</div>
	<nav>
  		<ul class="pager">
    		<li id="prev"><a href="${path}/index?page=${ppage}&xid=${xid}">&larr; Previous</a></li>
    		<li id="next"><a href="${path}/index?page=${npage}&xid=${xid}">Next &rarr;</a></li>
  		</ul>
	</nav>
	
	<script type="text/javascript">
		var ppage = ${ppage};
		var npage = ${npage};
		var xsz = ${xsz};
	</script>
	
	<content tag="javascripts">
  		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/books.js"></script>
  	</content>
  </body>
</html>