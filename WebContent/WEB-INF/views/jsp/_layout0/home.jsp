<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		
    	<title>SiteMesh example: <sitemesh:write property='title'/></title>
    	
    	<link href="<%=request.getContextPath()%>/resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet" />
    	
    	<sitemesh:write property='head'/>
    	
    	<!-- <p><a href="<c:url value="/logout" />" > Logout</a></p> -->
  	</head>
  	<body>
  		<nav class="navbar navbar-default">
  			<!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header container-fluid">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">Omega</a>
		    </div>
		    
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    	<ul class="nav navbar-nav">
        			<li class="active"><a href="#">Home</a></li>
        		</ul>
        		
        		<ul class="nav navbar-nav navbar-right">
			        <li><a href="#">Link</a></li>
		    	</ul>
		    </div>
  		</nav>
  		
  		<div class="container-fluid">
  			<div class="row">
  				<div class="col-md-2"></div>
  				<div class="col-md-8">
  					<sitemesh:write property='body'/>
  				</div>
  				<div class="col-md-2"></div>
  			</div>
		</div>
  	</body>
  	
  	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/dist/jquery.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/omega.min.js"></script>
</html>
