<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Hello World</title>
    <meta name='description' content='A simple page'>
  </head>
  <body>
    <p>Hello <strong>world11</strong>!</p>
    <c:forEach var="book" items="${books}" >
   		Id: <c:out value="${book.id}" />, Name: <c:out value="${book.name}" /> <br />
	</c:forEach>
  </body>
</html>