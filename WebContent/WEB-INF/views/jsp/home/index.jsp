<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Home</title>
    <meta name='description' content='A simple page'>
  </head>
  <body>
    <div class="row">
	  <div class="col-md-4">
	  	<div class="alert alert-success text-center" role="alert">
	  		<a href="${mailLinkText}" class="alert-link">${mailLinkText}</a>
	  	</div>
	  </div>
	  <div class="col-md-4">
	  	<div class="alert alert-info text-center" role="alert">
	  		<a href="${driveLinkText}" class="alert-link">${driveLinkText}</a>
	  	</div>
	  </div>
	  <div class="col-md-4">
	  	<div class="alert alert-warning text-center" role="alert">
	  		<a href="${booksPath}" class="alert-link" target="_blank">${booksName}</a>
	  	</div>
	  </div>
	</div>
	
	<div class="row">
	  <div class="col-md-4">
	  	<div class="alert alert-danger text-center" role="alert">
	  		<a href="${blogsLinkText}" class="alert-link">${blogsLinkText}</a>
	  	</div>
	  </div>
	  <div class="col-md-4">
	  	<div class="alert alert-success text-center" role="alert">
	  		<a href="${socialLink}" class="alert-link">${socialLinkText}</a>
	  	</div>
	  </div>
	  <div class="col-md-4">
	  	<div class="alert alert-info text-center" role="alert">
	  		<a href="${economicLink}" class="alert-link">${economicLinkText}</a>
	  	</div>
	  </div>
	</div>
  </body>
</html>