<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Hello World</title>
    <meta name='description' content='A simple page'>
  </head>
  <body>
    <table class="table">
    	<thead>
  			<tr>
     			<th>Id</th>
     			<th>Name</th>
  			</tr>
 		</thead>
 		<tbody>
 			<c:forEach var="book" items="${books}" >
	 			<tr>
	 				<td><c:out value="${book.id}" /></td>
	 				<td><c:out value="${book.name}" /></td>
	 			</tr>
			</c:forEach>
 		</tbody>
    </table>
  </body>
</html>