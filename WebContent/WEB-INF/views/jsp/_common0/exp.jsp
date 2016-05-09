<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Error</title>
	</head>
<body>
	
    <div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<h3>Error Page</h3>
			</h3>
	  	</div>
	  	<div class="panel-body">
	  		<p>Application has encountered an error. Please contact support on ...</p>
		    Failed URL: ${url} <br /> 
		    Exception:  ${error.message}
		        <c:forEach items="${error.stackTrace}" var="ste"> 
		        	${ste} 
		    	</c:forEach>
	  	</div>
	</div>
</body>
</html>
