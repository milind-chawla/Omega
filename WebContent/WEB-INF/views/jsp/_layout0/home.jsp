<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
    	<title>SiteMesh example: <sitemesh:write property='title'/></title>
    	
    	<link src="<%=request.getContextPath()%>/resources/bootstrap/dist/css/bootstrap.css"></script>
    	
    	<sitemesh:write property='head'/>
  	</head>
  	<body>
		<h1 class='title'>SiteMesh example site: <sitemesh:write property='title'/></h1>
		
    	<div class='mainBody'>
      		<sitemesh:write property='body'/>
    	</div>
    	
    	<div class='disclaimer'>Site disclaimer. This is an example.</div>
  	</body>
  	
  	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/javascript-opt.js"></script>
	
	<script type="text/javascript">
		var i = new omega.OmegaJS().factorial(10);
		
		console.log(i);
	</script>
</html>
